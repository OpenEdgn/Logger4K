package com.github.openEdgn.logger4k.internal.search

import com.github.openEdgn.logger4k.api.ILogApi
import java.util.ServiceLoader
import kotlin.reflect.KClass

/**
 * 基于 Java SPI 搜索,
 */
class SpiSearch : ILoggerSearch {
    override fun search(): Set<KClass<ILogApi>> {
        val loader = ServiceLoader.load(ILogApi::class.java)
        val hashSet = HashSet<KClass<ILogApi>>()
        @Suppress("UNCHECKED_CAST")
        loader.iterator().forEach { hashSet.add(it::class as KClass<ILogApi>) }
        return hashSet
    }
}
