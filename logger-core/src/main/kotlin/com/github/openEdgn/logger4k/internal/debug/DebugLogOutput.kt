package com.github.openEdgn.logger4k.internal.debug

import com.github.openEdgn.logger4k.LoggerLevel
import com.github.openEdgn.logger4k.api.ILogOutputApi
import com.github.openEdgn.logger4k.utils.ThrowableUtils
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * 内部DEBUG下使用的输出
 */
internal class DebugLogOutput : ILogOutputApi {

    private val level = if (System.getProperty("log.internal.debug", "false").trim().lowercase() == "true") {
        LoggerLevel.TRACE
    } else {
        LoggerLevel.OFF
    }
    private val dateTimeFormatter = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm:ss.SSS")

    override fun outputLevel(name: String) = level

    private fun output(
        id: String,
        thread: Thread,
        date: LocalDateTime,
        level: LoggerLevel,
        message: String,
        exception: Throwable?
    ) {
        if (level.level < LoggerLevel.WARN.level) {
            System.out
        } else {
            System.err
        }.run {
            StringBuilder()
                .append(date.format(dateTimeFormatter))
                .append(" - ")
                .append(level.name)
                .append(" - ")
                .append(thread.name ?: "unknown thread")
                .append(" - ")
                .append(id)
                .append(":")
                .append(message)
                .let {
                    if (exception != null) {
                        it.append("\r\n")
                        it.append(ThrowableUtils.format(exception))
                    }
                    println(it)
                }
        }
    }

    override fun outputLogger(
        name: String,
        thread: Thread,
        date: LocalDateTime,
        level: LoggerLevel,
        message: String
    ) {
        output(name, thread, date, level, message, null)
    }

    override fun outputLogger(
        name: String,
        thread: Thread,
        date: LocalDateTime,
        level: LoggerLevel,
        message: String,
        exception: Throwable
    ) {
        output(name, thread, date, level, message, exception)
    }
}
