package com.github.openEdgn.logger4k.internal.produce

import com.github.openEdgn.logger4k.ILogger
import com.github.openEdgn.logger4k.ILoggerFactory
import com.github.openEdgn.logger4k.LoggerFactory
import com.github.openEdgn.logger4k.api.ILogOutputApi
import com.github.openEdgn.logger4k.internal.Logger4KConfig
import com.github.openEdgn.logger4k.internal.SimpleLogger
import com.github.openEdgn.logger4k.internal.search.LogSearch
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

/**
 * 实际使用的日志初始化类
 */
internal class ProdLoggerFactory : ILoggerFactory {
    private val logOutput: ILogOutputApi
    private val logger = LoggerFactory.internal.getLogger(ProdLoggerFactory::class)

    init {
        val search = LogSearch.search()
        logOutput = if (search.isEmpty()) {
            EmptyLogOutputApi()
        } else {
            if (search.size > 1) {
                logger.warn(
                    "注意，发现多个日志实现类 [{}]，请检查是否存在重复引用问题.",
                    search.map { it.qualifiedName }.joinToString()
                )
            }
            val first = search.first()
            try {
                val call = first.primaryConstructor!!.call()
                logger.debug("此进程使用实现模块 ：{}.", call.name)
                call.init()
            } catch (e: Exception) {
                EmptyLogOutputApi()
            }
        }
    }

    override fun getLogger(clazz: Class<*>): ILogger {
        return getLogger(clazz.name ?: "UnknownClass")
    }

    override fun getLogger(clazz: KClass<*>): ILogger {
        return getLogger(clazz.java)
    }

    private val map = ConcurrentHashMap<String, ILogger>()
    override fun getLogger(name: String): ILogger {
        return map[name] ?: kotlin.run {
            val simpleLogger = SimpleLogger(name, logOutput, Logger4KConfig.messageFormat)
            map.putIfAbsent(name, simpleLogger) ?: simpleLogger
        }
    }
}
