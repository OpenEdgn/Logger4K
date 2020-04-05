package tech.openEdgn.logger4k.extras

import tech.openEdgn.logger4k.IOutput
import tech.openEdgn.logger4k.LoggerItem
import java.io.PrintStream
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FileOutput : IOutput {
    @Volatile
    private var close = false
    private val threadPool = Executors.newCachedThreadPool()
    private val loggerDirectory
        get() = LoggerExtraConfig.logOutputDirectory
    private val dateFormat = SimpleDateFormat("yyyy-MM-DD")
    var logNameCreateFun: () -> String = {
        "log-${dateFormat.format(System.currentTimeMillis())}.log"
    }
    private val printStream: PrintStream
        get() {

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

}