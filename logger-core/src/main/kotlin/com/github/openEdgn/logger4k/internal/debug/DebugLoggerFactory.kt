package com.github.openEdgn.logger4k.internal.debug

import com.github.openEdgn.logger4k.ILogger
import com.github.openEdgn.logger4k.ILoggerFactory
import com.github.openEdgn.logger4k.api.ILogOutputApi
import com.github.openEdgn.logger4k.internal.Logger4KConfig
import com.github.openEdgn.logger4k.internal.SimpleLogger
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass

/**
 * 程序内部测试日志，任何项目都不应持有此对象
 */
internal class DebugLoggerFactory(private val outputApi: ILogOutputApi) : ILoggerFactory {
    override fun getLogger(clazz: Class<*>): ILogger {
        return getLogger(clazz.name ?: "UnknownClass")
    }

    override fun getLogger(clazz: KClass<*>): ILogger {
        return getLogger(clazz.java)
    }

    private val map = ConcurrentHashMap<String, ILogger>()
    override fun getLogger(name: String): ILogger {
        return map[name] ?: kotlin.run {
            val simpleLogger = SimpleLogger(name, outputApi, Logger4KConfig.messageFormat)
            map.putIfAbsent(name, simpleLogger) ?: simpleLogger
        }
    }
}
