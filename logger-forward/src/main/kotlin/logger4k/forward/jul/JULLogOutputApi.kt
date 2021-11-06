package logger4k.forward.jul

import com.github.openEdgn.logger4k.LoggerLevel
import com.github.openEdgn.logger4k.api.ILogOutputApi
import com.github.openEdgn.logger4k.utils.ThrowableUtils
import java.time.LocalDateTime
import java.util.logging.Level as JULLevel
import java.util.logging.Logger as JULLogger

class JULLogOutputApi : ILogOutputApi {
    override fun outputLevel(name: String): LoggerLevel {
        return when (JULLogger.getLogger(name).level) {
            JULLevel.OFF -> LoggerLevel.OFF
            JULLevel.SEVERE -> LoggerLevel.ERROR
            JULLevel.WARNING -> LoggerLevel.WARN
            JULLevel.INFO -> LoggerLevel.INFO
            JULLevel.CONFIG -> LoggerLevel.DEBUG
            JULLevel.FINE,
            JULLevel.FINER,
            JULLevel.FINEST,
            JULLevel.ALL -> LoggerLevel.TRACE
            else -> LoggerLevel.OFF
        }
    }

    override fun outputLogger(name: String, thread: Thread, date: LocalDateTime, level: LoggerLevel, message: String) {
        JULLogger.getLogger(name).run {
            when (level) {
                LoggerLevel.TRACE -> finer(message)
                LoggerLevel.DEBUG -> config(message)
                LoggerLevel.INFO -> info(message)
                LoggerLevel.WARN -> warning(message)
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
        JULLogger.getLogger(name).run {
            when (level) {
                LoggerLevel.TRACE -> finer(msgFormat(message, exception))
                LoggerLevel.DEBUG -> config(msgFormat(message, exception))
                LoggerLevel.INFO -> info(msgFormat(message, exception))
                LoggerLevel.WARN -> warning(msgFormat(message, exception))
                LoggerLevel.ERROR -> error(msgFormat(message, exception))
                LoggerLevel.OFF -> return
            }
        }
    }

    private fun msgFormat(msg: String, error: Throwable): String {
        return "$msg\r\n" + ThrowableUtils.format(error)
    }
}
