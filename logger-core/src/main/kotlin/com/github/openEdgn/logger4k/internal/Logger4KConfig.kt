package com.github.openEdgn.logger4k.internal

import com.github.openEdgn.logger4k.ILoggerFactory
import com.github.openEdgn.logger4k.api.ILoggerSearch
import com.github.openEdgn.logger4k.internal.debug.DebugLogOutput
import com.github.openEdgn.logger4k.internal.debug.DebugLoggerFactory
import com.github.openEdgn.logger4k.internal.produce.ProdLoggerFactory
import com.github.openEdgn.logger4k.internal.search.DefaultLogSearch
import com.github.openEdgn.logger4k.utils.IMessageFormat
import com.github.openEdgn.logger4k.utils.SimpleMessageFormat
import com.github.openEdgn.logger4k.utils.SpiSearch

/**
 * 内部配置记录
 */
object Logger4KConfig {
    /**
     * 日志消息格式化工具
     */
    val messageFormat = SpiSearch.search(IMessageFormat::class, SimpleMessageFormat()).get()

    /**
     * 内部日志
     */
    val debugLoggerFactory: ILoggerFactory by lazy {
        DebugLoggerFactory(DebugLogOutput())
    }

    /**
     * 外部日志
     */
    val produceLoggerFactory: ILoggerFactory by lazy {
        ProdLoggerFactory()
    }

    /**
     * 日志实现加载器
     */
    val loggerSearch = SpiSearch.search(ILoggerSearch::class, DefaultLogSearch())
}
