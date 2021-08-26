package com.github.openEdgn.logger4k.internal.search

import com.github.openEdgn.logger4k.api.ILogApi
import kotlin.reflect.KClass

/**
 * 日志实现查找类
 */
interface ILoggerSearch {
    /**
     * 搜索可用的实现
     */
    fun search(): Set<KClass<ILogApi>>
}
