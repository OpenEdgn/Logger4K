package tech.openEdgn.logger4k.extras

import tech.openEdgn.logger4k.IOutput
import tech.openEdgn.logger4k.LoggerConfig
import tech.openEdgn.logger4k.LoggerItem
import tech.openEdgn.logger4k.extras.utils.FileUtils
import java.io.File
import java.io.PrintStream
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FileOutput : IOutput {
    @Volatile
    private var close = false
    private val threadPool = Executors.newCachedThreadPool()

    @Volatile
    private var loggerDirectory = LoggerExtraConfig.logOutputDirectory
    private val dateFormat = SimpleDateFormat("yyyy-MM-DD")

    /**
     *  日志文件创建方法
     */
    var logNameCreateFun: () -> String = {
        "log-${dateFormat.format(System.currentTimeMillis())}.log"
    }

    /**
     * 跳过文件日志
     */
    @Volatile
    var skip = false
    private val printStream: PrintStream?
        get() {
            if (checkLogFile().not()) {
                return null
            }

        }

    private var startTimeOfDay: Long = TODO()


    /**
     *  检测日志文件是否可用
     */
    private fun checkLogFile(): Boolean {
        if (skip) return false
        if (loggerDirectory != LoggerExtraConfig.logOutputDirectory || loggerDirectory.isDirectory.not()) {
            if (!FileUtils.checkDirectory(LoggerExtraConfig.logOutputDirectory)) {
                LoggerConfig.commandErrOutput.println("注意！日志目录不存在且无法创建/写入！")
                skip = true
                return false
            } else {
                loggerDirectory = LoggerExtraConfig.logOutputDirectory
            }
        }
        // 校验输出目录
        // 校验输出文件
    }

    override fun writeLine(item: LoggerItem) {
        threadPool.execute {

        }
    }

    override fun close() {
        close = true
        for (runnable in threadPool.shutdownNow()) {
            try {
                runnable.run()
            } catch (e: Exception) {
            }
        }
    }

    companion object{
        const val DAY_OF_MILLISECONDS = 24 * 60 * 60 * 1000


    }
}