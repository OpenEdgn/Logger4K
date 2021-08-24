package com.github.openEdgn.logger4k.internal.produce

import com.github.openEdgn.logger4k.ILogger
import com.github.openEdgn.logger4k.ILoggerFactory
import kotlin.reflect.KClass

/**
 * 实际使用的日志初始化类
 */
internal class ProdLoggerFactory : ILoggerFactory {
    override fun getLogger(clazz: Class<*>): ILogger {
        TODO("Not yet implemented")
    }

    override fun getLogger(clazz: KClass<*>): ILogger {
        TODO("Not yet implemented")
    }

    override fun getLogger(name: String): ILogger {
        TODO("Not yet implemented")
    }
}
