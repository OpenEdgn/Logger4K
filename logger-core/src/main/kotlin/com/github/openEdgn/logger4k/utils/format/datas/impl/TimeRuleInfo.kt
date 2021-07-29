package com.github.openEdgn.logger4k.utils.format.datas.impl

import com.github.openEdgn.logger4k.utils.format.datas.BaseFormatRule
import com.github.openEdgn.logger4k.utils.format.datas.IFormatRuleInfo
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

/**
 * 日期格式化方案
 */
class TimeRuleInfo : IFormatRuleInfo<Long> {
    override val name: String = "date"

    override val defaultRule = "YY/MM/dd HH:mm:ss"

    override fun generateRule(rule: String): BaseFormatRule<Long> {
        return object : BaseFormatRule<Long>(rule) {
            override val name: String = this@TimeRuleInfo.name
            private val dateFormat = DateTimeFormatter.ofPattern(rule)
            private val systemDefault = ZoneId.systemDefault()
            override fun format(data: Long): String {
                return LocalDateTime.ofInstant(Instant.ofEpochMilli(data), systemDefault).format(dateFormat)
            }
        }
    }
}
