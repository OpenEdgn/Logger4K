package com.github.openEdgn.logger4k.utils.format.log

import com.github.openEdgn.logger4k.LoggerLevel

/**
 * 日志格式化方案接口
 *
 * @constructor
 */
abstract class BaseLogFormat(protected val rule: String) {

    abstract fun printLogger(
        packageName: String,
        threadName: String,
        date: Long,
        level: LoggerLevel,
        message: String,
        throws: Throwable?
    ): String
}
