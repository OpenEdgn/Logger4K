package com.github.openEdgn.logger4k.utils.format.datas.impl

import com.github.openEdgn.logger4k.LoggerLevel
import com.github.openEdgn.logger4k.utils.format.datas.BaseFormatRule
import com.github.openEdgn.logger4k.utils.format.datas.IFormatRuleInfo
import com.github.openEdgn.logger4k.utils.format.formatLength
import com.github.openEdgn.logger4k.utils.format.toIntOrDefault

class LevelRuleInfo : IFormatRuleInfo<LoggerLevel> {
    override val name: String = "level"
    override fun generateRule(rule: String) = object : BaseFormatRule<LoggerLevel>(rule) {
        override val name: String = this@LevelRuleInfo.name
        val length = rule.toIntOrDefault(5)
        override fun format(data: LoggerLevel): String {
            return data.toString().formatLength(length)
        }
    }
}
