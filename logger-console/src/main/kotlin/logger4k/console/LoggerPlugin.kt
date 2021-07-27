package logger4k.console

import com.github.openEdgn.logger4k.ILogger
import com.github.openEdgn.logger4k.LoggerLevel
import com.github.openEdgn.logger4k.plugin.IPlugin
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass

object LoggerPlugin : IPlugin {
    private val map = ConcurrentHashMap<String, ILogger>(100)

    override fun getLogger(name: String): ILogger {
        return map[name] ?: kotlin.run {
            val consoleLogger = ConsoleLogger(ConsoleLogConfig.classNameFormat.format(name)) as ILogger
            map[name] = consoleLogger
            consoleLogger
        }
    }

    override fun getLoggerLevel(name: String): LoggerLevel {
        return ConsoleLogConfig.loggerLevel
    }

    override val ignoreOptimization = true

    override val name: String = "ConsoleLogger"

    override fun getLogger(clazz: KClass<*>): ILogger {
        return getLogger(ConsoleLogConfig.classNameFormat.format(clazz))
    }

    override fun shutdown() {
        for (runnable in ConsoleLogConfig.threadPool.shutdownNow()) {
            try {
                runnable.run()
            } catch (_: Exception) {
            }
        }
        println("程序于 ${ConsoleLogConfig.dateFormat.format(System.currentTimeMillis())} 退出.")
    }
}
