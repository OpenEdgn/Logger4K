package com.github.openEdgn.logger4k.utils.format.datas.impl

import com.github.openEdgn.logger4k.utils.ThrowableUtils
import com.github.openEdgn.logger4k.utils.format.datas.BaseFormatRule
import com.github.openEdgn.logger4k.utils.format.datas.IFormatRuleInfo
import com.github.openEdgn.logger4k.utils.format.toEnumOrDefault

class ThrowsRuleInfo : IFormatRuleInfo<Throwable?> {
    override val name: String = "throws"

    enum class ThrowsType {
        ALL,
        MESSAGE,
        NONE
    }

    override fun generateRule(rule: String) = object : BaseFormatRule<Throwable?>(rule) {
        override val name: String = this@ThrowsRuleInfo.name
        private val level = rule.toEnumOrDefault(true, ThrowsType.NONE)
        override fun format(data: Throwable?): String {
            if (data == null || level == ThrowsType.NONE) {
                return ""
            }
            return when (level) {
                ThrowsType.ALL -> ThrowableUtils.format(data)
                ThrowsType.MESSAGE -> data.message ?: ""
                else -> ""
            }
        }
    }
}
