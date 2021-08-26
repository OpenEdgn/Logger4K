package logger4k.console.utils.format.impl

import logger4k.console.utils.format.BaseFormatRule
import logger4k.console.utils.format.IFormatRuleInfo

class MessageRuleInfo : IFormatRuleInfo<String> {
    override val name = "message"
    override fun generateRule(rule: String) = object : BaseFormatRule<String>(rule) {
        override val name: String = this@MessageRuleInfo.name
        override fun format(data: String): String {
            return data
        }
    }
}
