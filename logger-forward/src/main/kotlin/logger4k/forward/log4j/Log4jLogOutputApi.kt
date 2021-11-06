package logger4k.forward.log4j

import com.github.openEdgn.logger4k.LoggerLevel
import com.github.openEdgn.logger4k.api.ILogOutputApi
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.spi.StandardLevel
import java.time.LocalDateTime

class Log4jLogOutputApi : ILogOutputApi {
    override fun outputLevel(name: String): LoggerLevel {
        val logger = LogManager.getLogger(name)
        return when (logger.level.standardLevel) {
            StandardLevel.ALL -> LoggerLevel.TRACE
            StandardLevel.OFF -> LoggerLevel.OFF
            StandardLevel.FATAL -> LoggerLevel.ERROR
            StandardLevel.ERROR -> LoggerLevel.ERROR
            StandardLevel.WARN -> LoggerLevel.WARN
            StandardLevel.INFO -> LoggerLevel.INFO
            StandardLevel.DEBUG -> LoggerLevel.DEBUG
            StandardLevel.TRACE -> LoggerLevel.TRACE
            else -> LoggerLevel.OFF
        }
    }

    override fun outputLogger(name: String, thread: Thread, date: LocalDateTime, level: LoggerLevel, message: String) {
        LogManager.getLogger(name).run {
            when (level) {
                LoggerLevel.TRACE -> trace(message)
                LoggerLevel.DEBUG -> debug(message)
                LoggerLevel.INFO -> info(message)
                LoggerLevel.WARN -> warn(message)
                LoggerLevel.ERROR -> error(message)
                LoggerLevel.OFF -> return
            }
        }
    }

    override fun outputLogger(
        name: String,
        thread: Thread,
        date: LocalDateTime,
        level: LoggerLevel,
        message: String,
        exception: Throwable
    ) {
        LogManager.getLogger(name).run {
            when (level) {
                LoggerLevel.TRACE -> trace(message, exception)
                LoggerLevel.DEBUG -> debug(message, exception)
                LoggerLevel.INFO -> info(message, exception)
                LoggerLevel.WARN -> warn(message, exception)
                LoggerLevel.ERROR -> error(message, exception)
                LoggerLevel.OFF -> return
            }
        }
    }
}
