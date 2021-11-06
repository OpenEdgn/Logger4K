package com.github.openEdgn.logger4k.internal.search

import com.github.openEdgn.logger4k.api.ILogApi
import com.github.openEdgn.logger4k.api.ILoggerSearch
import java.util.ServiceLoader

/**
 * 基于 Java SPI 搜索,
 */
class DefaultLogSearch : ILoggerSearch {
    override fun search(): Set<ILogApi> {
        val loader = ServiceLoader.load(ILogApi::class.java)
        val hashSet = HashSet<ILogApi>()
        loader.stream().forEach {
            hashSet.add(it.get() as ILogApi)
        }
        return hashSet
    }
}
