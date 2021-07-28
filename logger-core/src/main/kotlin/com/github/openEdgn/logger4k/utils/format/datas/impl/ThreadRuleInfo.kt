package com.github.openEdgn.logger4k.utils.format.datas.impl

import com.github.openEdgn.logger4k.utils.format.datas.BaseFormatRule
import com.github.openEdgn.logger4k.utils.format.datas.IFormatRuleInfo
import com.github.openEdgn.logger4k.utils.format.formatLength
import com.github.openEdgn.logger4k.utils.format.toIntOrDefault

class ThreadRuleInfo : IFormatRuleInfo<String> {
    override val name = "thread"

    override fun generateRule(rule: String) = object : BaseFormatRule<String>(rule) {
        override val name: String = this@ThreadRuleInfo.name
        private val length = rule.toIntOrDefault(10)
        override fun format(data: String) = data.formatLength(length)
    }
}
