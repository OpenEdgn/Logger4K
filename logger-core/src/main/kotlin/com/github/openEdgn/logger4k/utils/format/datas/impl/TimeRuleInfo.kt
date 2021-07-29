package com.github.openEdgn.logger4k.utils.format.datas.impl

import com.github.openEdgn.logger4k.utils.format.datas.BaseFormatRule
import com.github.openEdgn.logger4k.utils.format.datas.IFormatRuleInfo
import java.text.SimpleDateFormat

/**
 * 日期格式化方案
 */
class TimeRuleInfo : IFormatRuleInfo<Long> {
    override val name: String = "date"

    override val defaultRule = "YY/MM/dd HH:mm:ss"

    override fun generateRule(rule: String): BaseFormatRule<Long> {
        return object : BaseFormatRule<Long>(rule) {
            override val name: String = this@TimeRuleInfo.name
            private val dateFormat = object : ThreadLocal<SimpleDateFormat>() {
                override fun initialValue(): SimpleDateFormat {
                    return SimpleDateFormat(rule)
                }
            }

            override fun format(data: Long): String {
                return dateFormat.get().format(data)
            }
        }
    }
}
