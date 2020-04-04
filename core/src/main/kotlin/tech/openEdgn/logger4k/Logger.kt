package tech.openEdgn.logger4k

import java.util.*

/**
 * 日志实现类
 */
class Logger constructor(private val clazz: Class<out Any>) : ILogger {

    override fun debugOnly(t: (ILogger) -> Unit): ILogger {
        if (isDebug) {
            t(this)
        }
        return this
    }


    override fun debugOnly(debugOnly: DebugOnly): ILogger {
        if (isDebug) {
            debugOnly.run(this)
        }
        return this
    }

    override fun info(message: String, exception: Throwable?): ILogger {
        outputLogger(System.currentTimeMillis(), LoggerLevel.INFO, message, exception)
        return this
    }

    override fun debug(message: String, exception: Throwable?): ILogger {
        outputLogger(System.currentTimeMillis(), LoggerLevel.DEBUG, message, exception)
        return this
    }

    override fun warn(message: String, exception: Throwable?): ILogger {
        outputLogger(System.currentTimeMillis(), LoggerLevel.WARN, message, exception)
        return this
    }

    override fun error(message: String, exception: Throwable?): ILogger {
        outputLogger(System.currentTimeMillis(), LoggerLevel.ERROR, message, exception)
        return this
    }

    private fun outputLogger(loggerDate: Long, level: LoggerLevel, message: String, exception: Throwable?) {
        LoggerConfig.output.writeLine(LoggerItem(clazz, loggerDate,
                Thread.currentThread().name.toUpperCase(Locale.ENGLISH), level, message, exception))
    }


    override val isDebug: Boolean = LoggerConfig.isDebug

}