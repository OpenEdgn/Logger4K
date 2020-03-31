package tech.openEdgn.logger4k

import java.io.ByteArrayOutputStream
import java.io.OutputStreamWriter
import java.io.PrintWriter
import java.lang.Exception
import java.lang.RuntimeException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

data class RawFormat(val id: Int, val other: LoggerItemOutputFormat<out Any?>)

class LineFormat(format: String) {
    private val loggerFormatMap: Map<String, RawFormat>
    private val newFormat: String

    init {
        val list = ArrayList<LoggerItemOutputFormat<out Any?>>()
        listOf<LoggerItemOutputFormat<out Any?>>(DateFormat(format).initVal(),
                LevelFormat(format),
                MessageFormat(format),
                ClassFormat(format),
                ThrowableFormat(format),
                ThreadNameFormat(format)).forEach {
            try {
                it.initVal()
                list.add(it)
            } catch (e: Exception) {

            }
        }
        list.sortWith(Comparator { o1, o2 ->
            o1.compareTo(o2)
        })
        var temp = format
        val listOf = HashMap<String, RawFormat>()
        for ((index, value) in list.withIndex()) {
            temp = temp.replace(value.toPropString(), "%s")
            listOf[value.javaClass.simpleName] = RawFormat(index, value)
        }
        newFormat = temp
        loggerFormatMap = listOf.toMap()
    }

    fun format(data: LoggerOutput.LoggerOutputItem): String {
        val array = arrayOfNulls<String>(loggerFormatMap.size)
        loggerFormatMap[ClassFormat::class.simpleName]?.let {
            array[it.id] = it.other.format(data.clazz)
        }
        loggerFormatMap[LevelFormat::class.simpleName]?.let {
            array[it.id] = it.other.format(data.level)
        }
        loggerFormatMap[DateFormat::class.simpleName]?.let {
            array[it.id] = it.other.format(data.loggerDate)
        }
        loggerFormatMap[MessageFormat::class.simpleName]?.let {
            array[it.id] = it.other.format(data.message)
        }
        loggerFormatMap[ThreadNameFormat::class.simpleName]?.let {
            array[it.id] = it.other.format(data.threadName)
        }
        loggerFormatMap[ThrowableFormat::class.simpleName]?.let {
            array[it.id] = if (data.exception != null) {
                it.other.format(data.exception)
            } else {
                ""
            }
        }
        return String.format(newFormat, *array)
    }


}

abstract class LoggerItemOutputFormat<T>(private val format: String) : Comparable<LoggerItemOutputFormat<out Any?>> {
    abstract val formatHeader: String
    private val formatFoot: String = "}"
    abstract val testData: T
    protected abstract val defaultParams: String
    private var index: Int = -1
    private var rawParams: String = ""
    protected lateinit var params: String
    @Synchronized
    fun initVal(): LoggerItemOutputFormat<T> {
        val rawHeader = "@{$formatHeader"
        index = format.indexOf(rawHeader)
        if (index == -1) {
            throw RuntimeException("Item[$rawHeader$formatFoot] not found.")
        }
        val endIndex = format.substring(index + rawHeader.length)
                .indexOf(formatFoot) + rawHeader.length + index
        if (endIndex == -1) {
            throw RuntimeException("Item[$rawHeader$formatFoot] not closed.")
        }
        rawParams = format.substring(index + rawHeader.length, endIndex)
        params = rawParams.let {
            if (it.trim().isEmpty() || (it.trim() == ":")) {
                defaultParams
            } else {
                it.substring(1)
            }
        }
        try {
            tryFormat(testData)
        } catch (e: Exception) {
            throw RuntimeException("Item[$rawHeader$formatFoot]=> unknown set [$params]. ", e)
        }
        return this
    }

    fun toPropString() = "@{$formatHeader$rawParams$formatFoot"

    @Throws(Exception::class)
    abstract fun tryFormat(data: T): String

    protected abstract fun formatInclude(data: Any): T

    fun format(data: Any) = tryFormat(formatInclude(data))

    override fun compareTo(other: LoggerItemOutputFormat<out Any?>): Int {
        return index - other.index
    }

}

//[@{date:YYYY-mm-dd}]-@{level:%5s}-@{classPath:name}-@{message}\n@{throwable:all}

/**
 *  时间格式化工具
 */
class DateFormat(format: String) : LoggerItemOutputFormat<Long>(format) {
    private val simpleDateFormat by lazy { SimpleDateFormat(params) }
    override fun tryFormat(data: Long): String = simpleDateFormat.format(data)
    override val formatHeader = "date"
    override val defaultParams = "yyyy-MM-dd HH:mm:ss"
    override val testData: Long = System.currentTimeMillis()
    override fun formatInclude(data: Any) = if (data is Long) {
        data
    } else {
        throw RuntimeException("stub!")
    }
}

/**
 *  等级格式化工具
 */
class LevelFormat(format: String) : LoggerItemOutputFormat<LoggerLevel>(format) {
    override fun tryFormat(data: LoggerLevel): String = String.format(params, data.toString())
    override val formatHeader = "level"
    override val defaultParams = "%s"
    override val testData = LoggerLevel.DEBUG
    override fun formatInclude(data: Any) = if (data is LoggerLevel) {
        data
    } else {
        LoggerLevel.valueOf(data.toString())
    }

}

/**
 *  对象格式化工具
 */
class ClassFormat(format: String) : LoggerItemOutputFormat<Class<out Any>>(format) {
    override fun tryFormat(data: Class<out Any>): String = when (params) {
        "name" -> data.simpleName
        else -> data.name
    }

    override val formatHeader = "classPath"
    override val defaultParams = "name"
    override val testData = javaClass
    override fun formatInclude(data: Any) = kotlin.run {
        if (data is Class<*>) {
            data
        } else {
            throw RuntimeException("stub!")
        }
    }

}

/**
 *  线程格式化工具
 */
class ThreadNameFormat(format: String) : LoggerItemOutputFormat<String>(format) {
    override fun tryFormat(data: String): String = data
            .let {
                if (it.length > params.toInt()) {
                    it.substring(0, params.toInt())
                } else {
                    it
                }
            }

    override val formatHeader = "thread"
    override val defaultParams = Integer.MAX_VALUE.toString()
    override val testData: String = Thread.currentThread().name!!

    override fun formatInclude(data: Any) = if (data is String) {
        data
    } else {
        throw RuntimeException("stub!")
    }

}

/**
 *  提示信息格式化工具
 */
class MessageFormat(format: String) : LoggerItemOutputFormat<String>(format) {
    override fun tryFormat(data: String): String = data
            .let {
                if (it.length > params.toInt()) {
                    it.substring(0, params.toInt())
                } else {
                    it
                }
            }

    override val formatHeader = "message"
    override val defaultParams = Integer.MAX_VALUE.toString()
    override val testData = "message"

    override fun formatInclude(data: Any) = if (data is String) {
        data
    } else {
        throw RuntimeException("stub!")
    }

}

/**
 *  异常信息格式化工具
 */
class ThrowableFormat(format: String) : LoggerItemOutputFormat<Throwable?>(format) {
    override fun tryFormat(data: Throwable?): String = if (data != null) {
        when (params) {
            "all" -> "\r\n${throwableFormat(data)}"
            else -> if (data.message == null) {
                "non message."
            } else {
                data.message!!.replace("\r", "")
                        .replace("\n", "")
            }
        }
    } else {
        ""
    }

    private fun throwableFormat(throws: Throwable): String {
        val outputStream = ByteArrayOutputStream()
        val printWriter = PrintWriter(OutputStreamWriter(outputStream, Charsets.UTF_8), true)
        throws.printStackTrace(printWriter)
        printWriter.flush()
        val array = outputStream.toByteArray()
        printWriter.close()
        outputStream.close()
        return array.toString(Charsets.UTF_8).trim()
    }

    override val formatHeader = "throwable"
    override val defaultParams = "all"
    override val testData = RuntimeException("test")
    override fun formatInclude(data: Any) = if (data is Throwable) {
        data
    } else {
        throw RuntimeException("stub!")
    }

}