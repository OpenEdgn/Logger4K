package logger4k.console.output

import com.github.openEdgn.logger4k.LoggerLevel
import com.github.openEdgn.logger4k.api.ILogOutputApi
import java.time.LocalDateTime
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicBoolean

/**
 *  带缓存和异步的输出
 */
class CacheLogOutputApi(private val loggerApi: ILogOutputApi) : ILogOutputApi {
    private val closed = AtomicBoolean(false)
    private val threadPool = Executors.newCachedThreadPool()

    init {
        Runtime.getRuntime().addShutdownHook(Thread { this.close() })
    }

    private fun close() {
        if (closed.get().not()) {
            synchronized(closed) {
                if (closed.get().not()) {
                    closed.set(true)
                    threadPool.shutdownNow().forEach {
                        try {
                            it.run()
                        } catch (e: Exception) {
                        }
                    }
                }
            }
        }
    }

    private val cacheLevel = ConcurrentHashMap<String, LoggerLevel>()

    override fun outputLevel(name: String): LoggerLevel {
        return cacheLevel[name] ?: kotlin.run {
            val outputLevel = loggerApi.outputLevel(name)
            cacheLevel.putIfAbsent(name, outputLevel) ?: outputLevel
        }
    }

    override fun outputLogger(
        name: String,
        thread: Thread,
        date: LocalDateTime,
        level: LoggerLevel,
        message: String
    ) {
        if (closed.get()) {
            loggerApi.outputLogger(name, thread, date, level, message)
        } else {
            threadPool.submit {
                loggerApi.outputLogger(name, thread, date, level, message)
            }
        }
    }

    override fun outputLogger(
        name: String,
        thread: Thread,
        date: LocalDateTime,
        level: LoggerLevel,
        message: String,
        exception: Throwable
    ) {
        if (closed.get()) {
            loggerApi.outputLogger(name, thread, date, level, message, exception)
        } else {
            threadPool.submit {
                loggerApi.outputLogger(name, thread, date, level, message, exception)
            }
        }
    }
}
