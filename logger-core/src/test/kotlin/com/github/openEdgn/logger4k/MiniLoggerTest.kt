package com.github.openEdgn.logger4k

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

internal class MiniLoggerTest {

    @Test
    fun test() {
        val logger = LoggerFactory.getLogger("asas")
        logger.info("Hello World")
        Thread.sleep(1000)
    }
}
