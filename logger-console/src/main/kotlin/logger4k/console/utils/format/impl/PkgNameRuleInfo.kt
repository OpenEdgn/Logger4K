package logger4k.console.utils.format.impl

import com.github.openEdgn.logger4k.utils.MaxLengthClassFormat
import logger4k.console.utils.format.BaseFormatRule
import logger4k.console.utils.format.IFormatRuleInfo
import logger4k.console.utils.toIntOrDefault

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
