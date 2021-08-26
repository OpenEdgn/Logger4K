package logger4k.console.utils.format.impl

import logger4k.console.utils.format.BaseFormatRule
import logger4k.console.utils.format.IFormatRuleInfo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * 日期格式化方案
 */
class TimeRuleInfo : IFormatRuleInfo<LocalDateTime> {
    override val name: String = "date"

    override val defaultRule = "YY/MM/dd HH:mm:ss"

    override fun generateRule(rule: String): BaseFormatRule<LocalDateTime> {
        return object : BaseFormatRule<LocalDateTime>(rule) {
            override val name: String = this@TimeRuleInfo.name
            private val dateFormat = DateTimeFormatter.ofPattern(rule)
            override fun format(data: LocalDateTime): String {
                return dateFormat.format(data)
            }
        }
    }
}
