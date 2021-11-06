package com.github.openEdgn.logger4k.utils

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class MaxLengthClassFormatTest {

    @Test
    fun format() {
        val format = MaxLengthClassFormat()
        assertEquals(
            "c.g.o.l.u.MaxLengthClassFormatTest      ",
            format.format("com.github.openEdgn.logger4k.utils.MaxLengthClassFormatTest")
        )
        assertEquals(
            "c.g.o.l.u.M.TestInternal                ",
            format.format("com.github.openEdgn.logger4k.utils.MaxLengthClassFormatTest.TestInternal")
        )
        assertEquals(
            "f.c.MaxLengthClassFormatTestTestInternal",
            format.format("com.github.openEdgn.logger4k.utils.format.classes.MaxLengthClassFormatTestTestInternal")
        )
        assertEquals(
            "dragon.Test                             ",
            format.format("dragon.Test")
        )
    }
}
