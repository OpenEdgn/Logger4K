package com.github.openEdgn.logger4k.plugin

import com.github.openEdgn.logger4k.ILogger
import com.github.openEdgn.logger4k.LoggerLevel
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass

/**
 * 日志中间件对象缓存
 *
 */
internal class CachePluginWrapper(private val plugin: IPlugin) : IPlugin {
    override val name: String
        get() = plugin.name

    private val strCacheList = ConcurrentHashMap<String, ILogger>()
    private val clsCacheList = ConcurrentHashMap<String, ILogger>()
    private val levelCacheList = ConcurrentHashMap<String, LoggerLevel>()

    override fun getLogger(clazz: KClass<*>): ILogger {
        return if (clazz.java.isAnonymousClass.not()) {
            val key = clazz.java.name
            clsCacheList[key] ?: kotlin.run {
                val logger = plugin.getLogger(clazz)
                clsCacheList.putIfAbsent(key, logger) ?: logger
            }
        } else {
            plugin.getLogger(clazz)
        }
    }

    override fun getLogger(name: String): ILogger {
        return strCacheList[name] ?: kotlin.run {
            val logger = plugin.getLogger(name)
            strCacheList.putIfAbsent(name, logger) ?: logger
        }
    }

    override fun getLoggerLevel(name: String): LoggerLevel {
        return levelCacheList[name] ?: kotlin.run {
            val level = plugin.getLoggerLevel(name)
            levelCacheList.putIfAbsent(name, level) ?: level
        }
    }

    override fun shutdown() {
        plugin.shutdown()
        strCacheList.clear()
        clsCacheList.clear()
        levelCacheList.clear()
    }
}
