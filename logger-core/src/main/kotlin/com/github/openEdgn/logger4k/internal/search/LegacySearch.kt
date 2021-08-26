package com.github.openEdgn.logger4k.internal.search

import com.github.openEdgn.logger4k.api.ILogApi
import java.util.Optional
import java.util.Properties
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf

/**
 * 旧的搜索方案
 */
class LegacySearch : ILoggerSearch {
    private var implClass: Optional<KClass<ILogApi>> = Optional.empty()

    init {
        val properties = Properties()
        val classLoader = LegacySearch::class.java
        try {
            properties.load(classLoader.getResourceAsStream("/META-INF/logger4k.properties"))
        } catch (e: Exception) {
        }
        val property = properties.getProperty(ILogApi::class.qualifiedName)
        if (property != null) {
            try {
                val pluginImplClass = (Class.forName(property) ?: throw NullPointerException("未找到对应的Plugin 实现类")).kotlin
                if (pluginImplClass.isSubclassOf(ILogApi::class)) {
                    @Suppress("UNCHECKED_CAST")
                    implClass = Optional.of(pluginImplClass as KClass<ILogApi>)
                }
            } catch (e: Exception) {
            }
        }
    }

    override fun search(): Set<KClass<ILogApi>> {
        return if (implClass.isEmpty) {
            emptySet()
        } else {
            setOf(implClass.get())
        }
    }
}
