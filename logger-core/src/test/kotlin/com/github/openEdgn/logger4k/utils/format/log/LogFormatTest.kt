package com.github.openEdgn.logger4k.utils.format.log

import com.github.openEdgn.logger4k.LoggerLevel
import org.junit.jupiter.api.Test

internal class LogFormatTest {
    @Test
    fun test() {
        val rule = "%{date->YY/MM/dd HH:mm:ss} - %{level->5} " +
            "- %{package->30}:%{message}%{line}%{throws->ALL}"
//        val rule = "%{date} - %{level} " +
//            "- %{package}:%{message}%{line}%{throws->ALL}"
        val logFormat = LogFormat(rule)
        println(
            logFormat.loggerToString(
                javaClass.name, "Main", System.currentTimeMillis(),
                LoggerLevel.INFO, "sasa", RuntimeException("Nothing")
            )
        )
    }
}
