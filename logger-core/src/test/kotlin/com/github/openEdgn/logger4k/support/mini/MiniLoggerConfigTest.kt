package com.github.openEdgn.logger4k.support.mini

import com.github.openEdgn.logger4k.LoggerLevel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.text.SimpleDateFormat

internal class MiniLoggerConfigTest {

    private fun setProp(name: String, value: String) {
        System.setProperty("logger.mini.$name", value)
    }

    @Test
    fun getDefaultLevel() {
        setProp("level", "ERROR")
        assertEquals(LoggerLevel.ERROR, MiniLoggerConfig().defaultLevel)
        setProp("level", "")
        assertEquals(LoggerLevel.INFO, MiniLoggerConfig().defaultLevel)
    }

    @Test
    fun getAsyncLoggerOutput() {
        setProp("async", "false")
        assertEquals(false, MiniLoggerConfig().asyncLoggerOutput)
        setProp("async", "true")
        assertEquals(true, MiniLoggerConfig().asyncLoggerOutput)
        setProp("async", "")
        assertEquals(true, MiniLoggerConfig().asyncLoggerOutput)
    }

    @Test
    fun getPackageLogRules() {
        setProp("level", "OFF")
        setProp("rules", "com.example=ERROR;i.logger=INFO")
        val packageLogRules = MiniLoggerConfig().packageLogRules
        assertEquals(LoggerLevel.ERROR, packageLogRules.getPackageLevel("com.example.error"))
        assertEquals(LoggerLevel.INFO, packageLogRules.getPackageLevel("i.logger"))
        assertEquals(LoggerLevel.OFF, packageLogRules.getPackageLevel("i.test"))
    }

    @Test
    fun getMessageFormat() {
        val simpleDateFormat = SimpleDateFormat("YY/MM/dd HH:mm:ss")
        val rule = "%{date->YY/MM/dd HH:mm:ss} - %{level->5} " +
            "- %{package->10}:%{message}"
        setProp("format.line", rule)
        val messageFormat = MiniLoggerConfig().messageFormat
        val currentTimeMillis = System.currentTimeMillis()
        val loggerToString = messageFormat.loggerToString(
            "package",
            "main",
            currentTimeMillis,
            LoggerLevel.ERROR,
            "data", null
        )
        assertEquals("${simpleDateFormat.format(currentTimeMillis)} - ERROR - package   :data", loggerToString)
    }
}
