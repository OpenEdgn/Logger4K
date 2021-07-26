package com.github.openEdgn.logger4k.utils.format.datas.impl

import com.github.openEdgn.logger4k.utils.format.datas.BaseFormatRule
import com.github.openEdgn.logger4k.utils.format.datas.IFormatRuleInfo

class MessageRuleInfo : IFormatRuleInfo<String> {
    override val name = "message"
    override fun generateRule(rule: String) = object : BaseFormatRule<String>(rule) {
        override val name: String = this@MessageRuleInfo.name
        override fun format(data: String): String {
            return data
        }
    }
}
