package tech.openEdgn.logger4k.extras

import tech.openEdgn.logger4k.LoggerConfig.loggerOutputHook
import tech.openEdgn.logger4k.LoggerItem
import tech.openEdgn.logger4k.LoggerLevel
import java.io.PrintStream
import java.util.concurrent.Executors

class LoggerOutput() {
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


    @Synchronized
    fun writeLine(outputItem: LoggerItem) {
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