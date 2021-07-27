package com.github.openEdgn.logger4k.utils

internal class SystemEnvProperties(
    private val header: String = "",
    extras: Map<String, String> = mapOf()
) {
    private val items = mutableMapOf<String, String>()

    init {
        items.putAll(System.getenv())
        items.putAll(System.getProperties().map { Pair(it.key.toString(), it.value.toString()) })
        items.putAll(extras)
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Enum<T>> getEnumOrDefault(key: String, defaultValue: T): T {
        val value = (items[keyWrap(key)] ?: return defaultValue).uppercase()
        val enums = defaultValue::class.java.enumConstants as Array<Enum<*>>
        return try {
            enums.first { it.name.uppercase() == value } as T
        } catch (e: Exception) {
            defaultValue
        }
    }

    private fun keyWrap(key: String): String {
        return if (header.endsWith(".")) {
            "$header$key"
        } else {
            "$header.$key"
        }
    }

    fun <T> getCustomOrDefault(key: String, defaultValue: T, customFun: (String) -> T): T {
        val value = items[keyWrap(key)] ?: return defaultValue
        return try {
            customFun(value)
        } catch (e: Exception) {
            defaultValue
        }
    }

    fun getStringOrDefault(key: String, defaultValue: String): String {
        return items[keyWrap(key)] ?: return defaultValue
    }

    fun getBooleanOrDefault(key: String, defaultValue: Boolean): Boolean {
        val value = items[keyWrap(key)] ?: return defaultValue
        return try {
            when (value.lowercase()) {
                "true" -> true

                "false" -> false

                else -> defaultValue
            }
        } catch (e: Exception) {
            defaultValue
        }
    }
}
