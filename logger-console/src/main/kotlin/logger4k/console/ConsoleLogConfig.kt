package logger4k.console

import com.github.openEdgn.logger4k.LoggerLevel
import com.github.openEdgn.logger4k.utils.format.classes.ClassNameFormat
import com.github.openEdgn.logger4k.utils.format.classes.MaxLengthClassFormat
import java.io.PrintStream
import java.text.SimpleDateFormat
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * 内部日志配置
 */
object ConsoleLogConfig {
    @Volatile
    var loggerLevel = LoggerLevel.INFO

    @Volatile
    var dateFormat = SimpleDateFormat("yy/MM/dd HH:mm:ss")

    /**
     * console output
     */
    @Volatile
    var output: PrintStream = System.out

    /**
     * console error output
     */
    @Volatile
    var error: PrintStream = System.err

    internal val threadPool: ExecutorService = Executors.newCachedThreadPool()

    init {
        loggerLevel = try {
            LoggerLevel.valueOf(System.getProperty("logger.level", "INFO")!!.uppercase())
        } catch (_: Exception) {
            LoggerLevel.INFO
        }
    }

    internal val classNameFormat: ClassNameFormat = MaxLengthClassFormat()

    /**
     * logger level
     */
    internal val loggerLevelInt: Int
        get() = loggerLevel.level
}
