package com.github.openEdgn.logger4k.internal.produce

import com.github.openEdgn.logger4k.LoggerLevel
import com.github.openEdgn.logger4k.api.ILogOutputApi
import java.time.LocalDateTime

/**
 * 空白的日志输出
 */
class EmptyLogOutputApi : ILogOutputApi {
    override fun outputLevel(name: String): LoggerLevel {
        return LoggerLevel.OFF
    }

    override fun outputLogger(
        name: String,
        thread: Thread,
        date: LocalDateTime,
        level: LoggerLevel,
        message: String
    ) {
    }

    override fun outputLogger(
        name: String,
        thread: Thread,
        date: LocalDateTime,
        level: LoggerLevel,
        message: String,
        exception: Throwable
    ) {
    }
}
