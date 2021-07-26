package com.github.openEdgn.logger4k.support.mini

import com.github.openEdgn.logger4k.LoggerLevel
import com.github.openEdgn.logger4k.SimpleLogger

class MiniLogger(override val name: String, private val loggerPlugin: MiniLoggerPlugin) : SimpleLogger() {

    override fun printLogger(date: Long, level: LoggerLevel, message: String) {
        loggerPlugin.submitTask(level) {
            loggerPlugin.loggerConfig.getPrintStream(level).println(
                loggerPlugin.loggerConfig.messageFormat.loggerToString(
                    name,
                    Thread.currentThread().name,
                    date,
                    level,
                    message,
                    null
                )
            )
        }
    }

    override fun printLogger(date: Long, level: LoggerLevel, message: String, exception: Throwable) {
        loggerPlugin.submitTask(level) {
            loggerPlugin.loggerConfig.getPrintStream(level).println(
                loggerPlugin.loggerConfig.throwFormat.loggerToString(
                    name,
                    Thread.currentThread().name,
                    date,
                    level,
                    message,
                    exception
                )
            )
        }
    }
}
