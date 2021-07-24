package com.github.openEdgn.logger4k.utils.format.log

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class LogFormatTest {
    @Test
    fun test() {
        val str = "%{date->YY/MM/dd HH:mm:ss}"
        val regex = Regex("%\\{.+}")
        println(str.contains(regex))
    }
}
