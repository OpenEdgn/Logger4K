package com.github.openEdgn.logger4k.internal

import com.github.openEdgn.logger4k.ILoggerFactory
import com.github.openEdgn.logger4k.internal.debug.DebugLogOutput
import com.github.openEdgn.logger4k.internal.debug.DebugLoggerFactory
import com.github.openEdgn.logger4k.internal.produce.ProdLoggerFactory
import com.github.openEdgn.logger4k.utils.SimpleMessageFormat

object Logger4KConfig {
    val messageFormat = SimpleMessageFormat()
    val debugLoggerFactory: ILoggerFactory by lazy {
        DebugLoggerFactory(DebugLogOutput())
    }
    val produceLoggerFactory: ILoggerFactory by lazy {
        ProdLoggerFactory()
    }
}
