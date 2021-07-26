package com.github.openEdgn.logger4k.utils.format.datas.impl

import com.github.openEdgn.logger4k.utils.format.datas.BaseFormatRule
import com.github.openEdgn.logger4k.utils.format.datas.IFormatRuleInfo

class NewLineRuleInfo : IFormatRuleInfo<String?> {
    override val name: String = "line"

    override fun generateRule(rule: String) = object : BaseFormatRule<String?>(rule) {
        override val name: String = this@NewLineRuleInfo.name
        override fun format(data: String?): String {
            return "\r\n"
        }
    }
}
