package logger4k.console.utils.format.impl

import logger4k.console.utils.format.BaseFormatRule
import logger4k.console.utils.format.IFormatRuleInfo
import logger4k.console.utils.formatLength
import logger4k.console.utils.toIntOrDefault

class ThreadRuleInfo : IFormatRuleInfo<Thread> {
    override val name = "thread"

    override fun generateRule(rule: String) = object : BaseFormatRule<Thread>(rule) {
        override val name: String = this@ThreadRuleInfo.name
        private val length = rule.toIntOrDefault(10)
        override fun format(data: Thread) = data.name.formatLength(length)
    }
}
