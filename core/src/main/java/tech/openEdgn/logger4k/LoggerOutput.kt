package tech.openEdgn.logger4k

import tech.openEdgn.logger4k.LoggerConfig.loggerOutputHook
import java.io.PrintStream
import java.util.concurrent.Executors

class LoggerOutput(private val loggerConfig: LoggerConfig) {
    private val threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())

    private val format = LineFormat(loggerConfig.outputFormat)

    init {
        Runtime.getRuntime().addShutdownHook(Thread(Runnable {
            for (runnable in threadPool.shutdownNow()) {
                runnable.run()
            }
            loggerConfig.logFileOutput.println("程序已经结束！")
        }))
        //注册自动销毁日志模块
    }

    data class LoggerOutputItem(val clazz: Class<out Any>, val loggerDate: Long, val threadName: String, val level: LoggerLevel, val message: String, val exception: Throwable?)

    @Synchronized
    fun writeLine(outputItem: LoggerOutputItem) {
        threadPool.execute {
            val outCommand: (PrintStream?) -> Unit = {
                val line = format.format(outputItem)
                loggerConfig.logFileOutput.println(line)
                if (loggerOutputHook(outputItem).not()){
                    it?.println(line)
                }
            }
            when (outputItem.level) {
                LoggerLevel.INFO -> {
                    outCommand(LoggerConfig.commandOutput)
                }
                LoggerLevel.DEBUG -> {
                    if (loggerConfig.isDebug) {
                        outCommand(LoggerConfig.commandOutput)
                    }
                }
                LoggerLevel.WARN -> {
                    outCommand(LoggerConfig.commandErrOutput)
                }
                LoggerLevel.ERROR -> {
                    outCommand(LoggerConfig.commandErrOutput)
                }
            }
        }
    }

}