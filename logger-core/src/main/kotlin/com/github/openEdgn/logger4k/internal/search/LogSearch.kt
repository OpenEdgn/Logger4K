package com.github.openEdgn.logger4k.internal.search

import com.github.openEdgn.logger4k.api.ILogApi
import kotlin.reflect.KClass

object LogSearch : ILoggerSearch {
    private val search by lazy { setOf(SpiSearch(), LegacySearch()) }

    override fun search(): Set<KClass<ILogApi>> {
        val hashSet = HashSet<KClass<ILogApi>>()
        search.forEach { hashSet.addAll(it.search()) }
        return hashSet
    }
}
