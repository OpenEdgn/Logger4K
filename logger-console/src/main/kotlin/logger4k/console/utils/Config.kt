package logger4k.console.utils

import java.util.concurrent.ConcurrentHashMap

/**
 * 配置
 * @property source Map<String, String>
 * @constructor
 */
class Config(private val source: Map<String, String>) {
    private val data = ConcurrentHashMap<String, Any>()

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> get(item: ConfigItem<T>): T {
        return (
            data[item.key] ?: kotlin.run {
                val t = try {
                    item.parseString(source[item.key]!!)
                } catch (e: Exception) {
                    item.defaultValue
                }
                data.putIfAbsent(item.key, t) ?: t
            }
            ) as T
    }

    interface ConfigItem<T : Any> {
        val key: String
        val defaultValue: T
        fun toString(data: T): String
        fun parseString(data: String): T
    }
}
