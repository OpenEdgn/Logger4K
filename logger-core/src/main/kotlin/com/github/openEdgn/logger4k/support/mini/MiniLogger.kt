package com.github.openEdgn.logger4k.support.mini

import com.github.openEdgn.logger4k.LoggerLevel
import com.github.openEdgn.logger4k.SimpleLogger

class MiniLogger(override val name: String) : SimpleLogger() {

    override fun printLogger(date: Long, level: LoggerLevel, message: String) {
        TODO("Not yet implemented")
    }

    override fun printLogger(date: Long, level: LoggerLevel, message: String, exception: Throwable) {
        TODO("Not yet implemented")
    }
}
