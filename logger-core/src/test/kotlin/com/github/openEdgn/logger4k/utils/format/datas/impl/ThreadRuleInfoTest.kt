package com.github.openEdgn.logger4k.utils.format.datas.impl

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class ThreadRuleInfoTest {

    @Test
    fun test() {
        val threadRuleInfo = ThreadRuleInfo()
        val generateRule = threadRuleInfo.generateRule("10")
        assertEquals("t1        ", generateRule.format("t1"))
        assertEquals("t2--------", generateRule.format("t2----------"))
    }
}
