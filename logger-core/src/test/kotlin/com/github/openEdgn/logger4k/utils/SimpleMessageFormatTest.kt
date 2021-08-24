package com.github.openEdgn.logger4k.utils

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class SimpleMessageFormatTest {

    @Test
    fun format() {
        val format = SimpleMessageFormat()
        assertEquals(
            format.format(
                "Hello World, {}.",
                arrayOf("dragon")
            ),
            "Hello World, dragon."
        )

        assertEquals(
            format.format(
                "Hello World, {}.",
                arrayOf()
            ),
            "Hello World, {}."
        )

        assertEquals(
            format.format(
                "Hello World, \\{},{}.",
                arrayOf("dragon")
            ),
            "Hello World, {},dragon."
        )

        assertEquals(
            format.format(
                "{}, Hello World.",
                arrayOf("dragon")
            ),
            "dragon, Hello World."
        )

        assertEquals(
            format.format("{}, Hello World, {}.", arrayOf("dragon", "bye")),
            "dragon, Hello World, bye."
        )

        assertEquals(
            format.format(
                "Hello World, {}.", arrayOf("dragon", "Alice", "Bob")
            ),
            "Hello World, dragon."
        )
    }
}
