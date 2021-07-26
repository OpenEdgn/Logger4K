package com.github.openEdgn.logger4k.utils.format

import com.github.openEdgn.logger4k.LoggerLevel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class StringsKtTest {
    @Test
    fun formatTest() {
        assertEquals(10, "data".format(10).length)
        assertEquals(1, "data".format(1).length)
    }

    @Test
    fun toIntOrDefaultTest() {
        assertEquals(12, "12".toIntOrDefault(10))
        assertEquals(-12, "-12".toIntOrDefault(10))
        assertEquals(10, "-12d".toIntOrDefault(10))
    }

    @Test
    fun toEnumOrDefaultTest() {
        assertEquals(LoggerLevel.INFO, "INFO".toEnumOrDefault(true, LoggerLevel.ERROR))
        assertEquals(LoggerLevel.INFO, "info".toEnumOrDefault(true, LoggerLevel.ERROR))
        assertEquals(LoggerLevel.ERROR, "warn".toEnumOrDefault(false, LoggerLevel.ERROR))
        assertEquals(LoggerLevel.ERROR, "warn1".toEnumOrDefault(false, LoggerLevel.ERROR))
    }
}
