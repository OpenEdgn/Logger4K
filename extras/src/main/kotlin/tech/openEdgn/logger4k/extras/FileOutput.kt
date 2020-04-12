package tech.openEdgn.logger4k.extras

import tech.openEdgn.logger4k.IOutput
import tech.openEdgn.logger4k.LoggerConfig
import tech.openEdgn.logger4k.LoggerItem
import tech.openEdgn.logger4k.extras.utils.FileUtils
import java.io.File
import java.io.PrintStream
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.concurrent.Executors

class FileOutput : IOutput {
    @Volatile
    var skipLoggerFile = false

    private val threadPool = Executors.newCachedThreadPool()

    private val dateFormat = SimpleDateFormat("yyyy-MM-DD")

    private val loggerFileName: String
        get() = "log-${dateFormat.format(System.currentTimeMillis())}.log"

    private val loggerFile: File
        get() = File(ELoggerConfig.logOutputDirectory, loggerFileName)


    @Volatile
    private var printStream: PrintStream? = null

    @Volatile
    private var fileHashCode = -1
    private val loggerFilePrintStream: PrintStream?
        get() {
            if (skipLoggerFile || FileUtils.checkDirectory(ELoggerConfig.logOutputDirectory).not() || loggerFile.isDirectory) {
                if (loggerFile.isDirectory) {
                    LoggerConfig.commandErrOutput.println("path :[${loggerFile.absolutePath}] is directory.")
                }
                return null
            }
            if (loggerFile.exists().not()) {
                loggerFile.createNewFile()
            }
            synchronized(this) {
                if (printStream == null || loggerFile.hashCode() != fileHashCode) {
                    printStream?.let {
                        it.println("日志已经结束.")
                        it.println("EOF")
                        try {
                            it.close()
                        }catch (e:Exception){
                            LoggerConfig.commandErrOutput.println("关闭日志文件时出现问题. [${e.message}]")
                        }
                    }
                    printStream = PrintStream(loggerFile.outputStream(), true, Charsets.UTF_8.name())
                    printStream?.let {
                        if (loggerFile.length() == 0L) {
                            it.println("日志记录开始 >  ${loggerFile.name} <<EOF ")
                            //新日志文件
                        } else {
                            it.println()
                            it.println("继续记录")
                            //日志文件已存在
                        }
                    }
                }
            }
            return printStream
        }

    override fun writeLine(item: LoggerItem) {
        threadPool.submit(LoggerItemRunnable(item))
    }

    override fun close() {
        for (runnable in threadPool.shutdownNow()) {
            runnable.run()
        }
    }

    inner class LoggerItemRunnable(private val item: LoggerItem) : Runnable {
        override fun run() {

        }
    }
}