package logger4k.console.utils.format

interface IFormatRuleInfo<T : Any?> {
    val name: String
    val defaultRule: String
        get() = ""

    fun generateRule(rule: String = defaultRule): BaseFormatRule<T>
}
