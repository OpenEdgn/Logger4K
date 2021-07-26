package com.github.openEdgn.logger4k.utils.format.datas

/**
 *
 */
abstract class BaseFormatRule<T : Any?>(protected val rule: String) {
    abstract val name: String
    abstract fun format(data: T): String
}
