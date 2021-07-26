package com.github.openEdgn.logger4k.support.mini

import com.github.openEdgn.logger4k.LoggerLevel
import com.github.openEdgn.logger4k.SimpleLogger

class MiniLogger(override val name: String) : SimpleLogger() {

    override fun printLogger(date: Long, level: LoggerLevel, message: String) {
        MiniLoggerPlugin.submitTask {
            MiniLoggerConfig.getPrintStream(level).println(
                MiniLoggerConfig.messageFormat.loggerToString(
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
        MiniLoggerConfig.getPrintStream(level).println(
            MiniLoggerConfig.throwFormat.loggerToString(
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
