package com.github.openEdgn.logger4k.utils

import org.junit.jupiter.api.Test

internal class ThrowableUtilsTest {

    @Test
    fun format() {
        println(ThrowableUtils.format(RuntimeException()))
    }
}
