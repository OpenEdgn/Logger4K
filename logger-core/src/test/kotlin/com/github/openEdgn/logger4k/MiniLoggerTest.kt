package com.github.openEdgn.logger4k

import org.junit.jupiter.api.Test

internal class MiniLoggerTest {

    @Test
    fun test() {
        System.setProperty("logger4k.internal.level", "INFO")
        val logger = getLogger()
        logger.error("Hello World")
    }
}
