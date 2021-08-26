package logger4k.console.utils.format.impl

import logger4k.console.utils.format.BaseFormatRule
import logger4k.console.utils.format.IFormatRuleInfo

class NewLineRuleInfo : IFormatRuleInfo<String?> {
    override val name: String = "line"

    override fun generateRule(rule: String) = object : BaseFormatRule<String?>(rule) {
        override val name: String = this@NewLineRuleInfo.name
        override fun format(data: String?): String {
            return "\r\n"
        }
    }
}
