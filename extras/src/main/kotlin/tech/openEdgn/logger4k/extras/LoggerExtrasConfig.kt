package tech.openEdgn.logger4k.extras

import tech.openEdgn.logger4k.LoggerConfig
import tech.openEdgn.logger4k.LoggerItem
import java.io.File
import java.io.FileWriter
import java.io.PrintWriter
import java.lang.Exception
import java.text.SimpleDateFormat

/**
 * Logger 扩展设置
 */
object LoggerExtrasConfig {

    val output: LoggerOutput by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        if (loggerOutputDir.isFile) {
            loggerOutputDir.delete()
        }
        if (!loggerOutputDir.exists()) {
            loggerOutputDir.mkdirs()
        }
        LoggerOutput()
    }
    /**
     * 输出格式化
     */
    @Volatile
    var outputFormat: String = "[Thread/#@{thread}] @{date:yyyy/MM/dd HH:mm:ss} - @{level:%-5s} - @{classPath:path} : @{message} @{throwable:all}"

    /**
     * 控制台输出 HOOK
     */
    @Volatile
    var loggerOutputHook: (LoggerItem) -> Boolean = { false }

    /**
     * 指定日志文件夹的输出位置
     */
    @Volatile
    var loggerOutputDir: File = run {
        val file = File(System.getProperty("java.io.tmpdir"), "Log")
        if (file.isFile) {
            file.delete()
        }
        if (!file.exists()) {
            file.mkdirs()
        }
        file
    }


    private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
    private val headerFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    private val logTempFileOutput: Array<PrintWriter?> = Array(2) { null }
    private val pid: Int by lazy {
        try {
            //Android 获取 PID
            val process = Class.forName("android.os.Process")
            process.getMethod("myPid").invoke(null) as Int
        } catch (e: Exception) {
            try {
                // Java SE 获取 PID
                val process = Class.forName("java.lang.management.ManagementFactory")
                val invoke = process.getMethod("getRuntimeMXBean").invoke(null)
                (Class.forName("java.lang.management.RuntimeMXBean").getDeclaredMethod("getName").invoke(invoke) as String).split("@")[0].toInt()
            } catch (e: Exception) {
                -1
            }
        }
    }


    /**
     * 得到今天的日志位置
     */
    val loggerFile
        @Synchronized
        get() = getLoggerFilePath(System.currentTimeMillis())

    private fun getLoggerFilePath(date: Long): File = File(loggerOutputDir,
            "log-${simpleDateFormat.format(date)}.log")

    private fun getOutputFile(date: Long = System.currentTimeMillis()) =
            getLoggerFilePath(date).let {
                if (!it.isFile) {
                    it.createNewFile()
                    it.writeText("此日志开始于 ${headerFormat.format(System.currentTimeMillis())} ,PID: $pid !\r\n")
                } else {
                    it.appendText("\r\n新的日志开始于 ${headerFormat.format(System.currentTimeMillis())}  ,PID: $pid !\r\n")
                }
                it
            }

    private var startDate = simpleDateFormat.parse(simpleDateFormat.format(System.currentTimeMillis())).time

    private const val oneDay = 24 * 60 * 60 * 1000

    val logFileOutput: PrintWriter
        @Synchronized get() {
            if (logTempFileOutput[1] == null) {
                logTempFileOutput[1] = PrintWriter(FileWriter(getOutputFile(), true), true)
            }
            if (System.currentTimeMillis() > startDate + oneDay) {
                try {
                    logTempFileOutput[0]?.close()
                } catch (e: Exception) {
                }
                logTempFileOutput[0] = logTempFileOutput[1]
                logTempFileOutput[1] = PrintWriter(FileWriter(getOutputFile(), true), true)
            }
            return logTempFileOutput[1]!!
        }



}