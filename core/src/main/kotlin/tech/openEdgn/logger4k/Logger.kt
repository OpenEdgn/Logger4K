package tech.openEdgn.logger4k

import java.util.*

/**
 * 日志实现类
 */
class Logger constructor(private val clazz: Class<out Any>) : ILogger {

    override fun debugOnly(function: ILogger.() -> Unit): ILogger {
        if (isDebug) {
            function(this)
        }
        return this
    }


    override fun info(message: Any, exception: Throwable?): ILogger {
        outputLogger(System.currentTimeMillis(), LoggerLevel.INFO, message, exception)
        return this
    }

    override fun debug(message: Any, exception: Throwable?): ILogger {
        outputLogger(System.currentTimeMillis(), LoggerLevel.DEBUG, message, exception)
        return this
    }

    override fun warn(message: Any, exception: Throwable?): ILogger {
        outputLogger(System.currentTimeMillis(), LoggerLevel.WARN, message, exception)
        return this
    }

    override fun error(message: Any, exception: Throwable?): ILogger {
        outputLogger(System.currentTimeMillis(), LoggerLevel.ERROR, message, exception)
        return this
    }

    private fun outputLogger(loggerDate: Long, level: LoggerLevel, message: Any, exception: Throwable?) {
        LoggerConfig.output.writeLine(LoggerItem(clazz, loggerDate,
                Thread.currentThread().name.toUpperCase(Locale.ENGLISH), level, message, exception))
    }


    override val isDebug: Boolean = LoggerConfig.isDebug

}