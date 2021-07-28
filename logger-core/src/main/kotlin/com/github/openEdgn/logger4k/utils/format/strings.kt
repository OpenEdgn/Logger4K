package com.github.openEdgn.logger4k.utils.format

fun String.toIntOrDefault(default: Int): Int {
    return try {
        toInt()
    } catch (e: Exception) {
        default
    }
}

fun String.formatLength(len: Int): String {
    if (len <= 0) {
        return ""
    }
    val item = substring(
        0,
        if (length > len) {
            len
        } else {
            length
        }
    )
    return String.format("%${-len}s", item)
}

@Suppress("UNCHECKED_CAST")
inline fun <reified T : Enum<T>> String.toEnumOrDefault(ignoreCase: Boolean, default: T): T {
    val enums = T::class.java.enumConstants as Array<Enum<*>>
    val its = if (ignoreCase) {
        this.uppercase()
    } else {
        this
    }
    return try {
        enums.first {
            if (ignoreCase) {
                it.name.uppercase()
            } else {
                it.name
            } == its
        } as T
    } catch (_: Exception) {
        default
    }
}
