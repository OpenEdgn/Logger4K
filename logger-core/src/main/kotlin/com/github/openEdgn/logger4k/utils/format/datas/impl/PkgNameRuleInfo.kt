package com.github.openEdgn.logger4k.utils.format.datas.impl

import com.github.openEdgn.logger4k.utils.format.classes.MaxLengthClassFormat
import com.github.openEdgn.logger4k.utils.format.datas.BaseFormatRule
import com.github.openEdgn.logger4k.utils.format.datas.IFormatRuleInfo
import com.github.openEdgn.logger4k.utils.format.toIntOrDefault

class PkgNameRuleInfo : IFormatRuleInfo<String> {
    override val name = "package"
    override val defaultRule = "30"
    override fun generateRule(rule: String) = object : BaseFormatRule<String>(rule) {
        override val name: String = this@PkgNameRuleInfo.name
        private val formatRule = MaxLengthClassFormat(rule.toIntOrDefault(30))
        override fun format(data: String): String {
            return formatRule.format(data)
        }
    }
}
