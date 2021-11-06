package logger4k.forward.aul

import android.util.Log
import com.github.openEdgn.logger4k.LoggerLevel
import com.github.openEdgn.logger4k.api.ILogOutputApi
import java.time.LocalDateTime

class AULLogOutputApi : ILogOutputApi {
    override fun outputLevel(name: String): LoggerLevel {
        return when {
            Log.isLoggable(name, Log.VERBOSE) -> LoggerLevel.TRACE
            Log.isLoggable(name, Log.DEBUG) -> LoggerLevel.DEBUG
            Log.isLoggable(name, Log.INFO) -> LoggerLevel.INFO
            Log.isLoggable(name, Log.WARN) -> LoggerLevel.WARN
            Log.isLoggable(name, Log.ERROR) -> LoggerLevel.ERROR
            Log.isLoggable(name, Log.ASSERT) -> LoggerLevel.OFF
            else -> LoggerLevel.OFF
        }
    }

    override fun outputLogger(name: String, thread: Thread, date: LocalDateTime, level: LoggerLevel, message: String) {
        when (level) {
            LoggerLevel.TRACE -> Log.v(name, message)
            LoggerLevel.DEBUG -> Log.d(name, message)
            LoggerLevel.INFO -> Log.i(name, message)
            LoggerLevel.WARN -> Log.w(name, message)
            LoggerLevel.ERROR -> Log.e(name, message)
            LoggerLevel.OFF -> {
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
        when (level) {
            LoggerLevel.TRACE -> Log.v(name, message, exception)
            LoggerLevel.DEBUG -> Log.d(name, message, exception)
            LoggerLevel.INFO -> Log.i(name, message, exception)
            LoggerLevel.WARN -> Log.w(name, message, exception)
            LoggerLevel.ERROR -> Log.e(name, message, exception)
            LoggerLevel.OFF -> {
            }
        }
    }
}
