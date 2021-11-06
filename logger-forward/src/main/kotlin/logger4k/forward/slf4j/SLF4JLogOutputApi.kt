package logger4k.forward.slf4j

import com.github.openEdgn.logger4k.LoggerLevel
import com.github.openEdgn.logger4k.api.ILogOutputApi
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.LocalDateTime

class SLF4JLogOutputApi : ILogOutputApi {

    private fun getSLF4JLog(name: String): Logger {
        return LoggerFactory.getLogger(name)
    }

    override fun outputLevel(name: String): LoggerLevel {
        val jLog = getSLF4JLog(name)
        return if (jLog.isTraceEnabled) {
            LoggerLevel.TRACE
        } else if (jLog.isDebugEnabled) {
            LoggerLevel.DEBUG
        } else if (jLog.isInfoEnabled) {
            LoggerLevel.INFO
        } else if (jLog.isWarnEnabled) {
            LoggerLevel.WARN
        } else if (jLog.isErrorEnabled) {
            LoggerLevel.ERROR
        } else {
            LoggerLevel.OFF
        }
    }

    override fun outputLogger(name: String, thread: Thread, date: LocalDateTime, level: LoggerLevel, message: String) {
        getSLF4JLog(name).run {
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
        getSLF4JLog(name).run {
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
