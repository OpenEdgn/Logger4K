package com.github.openEdgn.logger4k.utils.format.datas

interface IFormatRuleInfo<T : Any?> {
    val name: String
    val defaultRule: String
        get() = ""

    fun generateRule(rule: String = defaultRule): BaseFormatRule<T>
}
