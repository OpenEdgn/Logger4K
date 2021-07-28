package com.github.openEdgn.logger4k.utils.format.classes

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class MaxLengthClassFormatTest {

    @Test
    fun format() {
        val maxLengthClassFormat = MaxLengthClassFormat(10)
        assertEquals("c.example ", maxLengthClassFormat.format("com.example"))
        assertEquals("c.e.dragon", maxLengthClassFormat.format("com.example.dragon"))
    }
}
