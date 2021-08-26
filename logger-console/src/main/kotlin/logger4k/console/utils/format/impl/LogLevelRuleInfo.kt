package logger4k.console.utils.format.impl

import com.github.openEdgn.logger4k.LoggerLevel
import logger4k.console.utils.format.BaseFormatRule
import logger4k.console.utils.format.IFormatRuleInfo
import logger4k.console.utils.formatLength
import logger4k.console.utils.toIntOrDefault

class LogLevelRuleInfo : IFormatRuleInfo<LoggerLevel> {
    override val name: String = "level"
    override fun generateRule(rule: String) = object : BaseFormatRule<LoggerLevel>(rule) {
        override val name: String = this@LogLevelRuleInfo.name
        val length = rule.toIntOrDefault(5)
        override fun format(data: LoggerLevel): String {
            return data.toString().formatLength(length)
        }
    }
}
