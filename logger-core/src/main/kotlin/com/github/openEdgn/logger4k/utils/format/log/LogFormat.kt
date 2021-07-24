package com.github.openEdgn.logger4k.utils.format.log

import com.github.openEdgn.logger4k.LoggerLevel

/**
 *
 *         "%{date->YY/MM/dd HH:mm:ss} - %{level->1} - %{package->20}:%{message}\r\n%{throws->ALL}"
 */
class LogFormat(rule: String) : BaseLogFormat(rule) {
    init {
        val regex = Regex("%\\{.+}")
        println(rule.contains(regex))
    }

    override fun printLogger(
        packageName: String,
        date: Long,
        level: LoggerLevel,
        message: String,
        throws: Throwable?
    ): String {
        TODO("Not yet implemented")
    }
}
