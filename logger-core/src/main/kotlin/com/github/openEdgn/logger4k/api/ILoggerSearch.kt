package com.github.openEdgn.logger4k.api

/**
 * 日志实现查找类
 */
interface ILoggerSearch {
    /**
     * 搜索可用的实现
     */
    fun search(): Set<ILogApi>
}
