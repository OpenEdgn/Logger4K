package logger4k.console.utils.format

/**
 *
 */
abstract class BaseFormatRule<T : Any?>(protected val rule: String) {
    abstract val name: String
    abstract fun format(data: T): String
}
