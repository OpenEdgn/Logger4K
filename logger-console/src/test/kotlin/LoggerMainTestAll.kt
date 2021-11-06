import com.github.openEdgn.logger4k.LoggerFactory
import com.github.openEdgn.logger4k.withLogDebug
import com.github.openEdgn.logger4k.withLogError
import com.github.openEdgn.logger4k.withLogInfo
import com.github.openEdgn.logger4k.withLogTrace
import com.github.openEdgn.logger4k.withLogWarn
import org.junit.jupiter.api.Test

/**
 * 此日志框架的使用方法
 */
class LoggerMainTestAll

@Test
fun main() {
    Thread.currentThread().name = "Main"
    System.setProperty("logger.level", "[*=trace]")
    System.setProperty("logger.level", "[*=debug]")
    System.setProperty("logger.level", "[*=info]")
    System.setProperty("logger.level", "[*=warn]")
    System.setProperty("logger.level", "[*=error]")
    System.setProperty("logger.level", "[*=off]")
    // 指定日志级别，可以未 DEBUG INFO WARN ERROR
    // val logger = getLogger()
    // 获取Logger 实例
    val logger = LoggerFactory.getLogger(LoggerMainTestAll::class)
    // 另一种方式获取Logger 实例
    System.setProperty("logger.level", "[*=trace]")
    logger.trace("trace.")
    logger.trace("日志级别: {}.", "trace")
    logger.traceOnly {
        trace("trace 代码块 {}", arrayListOf("A", "B", "C").toString())
        // 此代码块仅在trace级别时执行
    }
    logger.debugOnly {
        trace("debug 代码块 {}", arrayListOf("A", "B", "C").toString())
        // 此代码块仅在debug级别时执行
    }
    // 输出 TRACE 日志

    logger.debug("debug.")
    logger.debug("日志级别: {}.", "debug")
    logger.debugThrowable("输出异常信息", RuntimeException("Runtime Exception"))
    // 输出 DEBUG 日志

    logger.info("Info.")
    logger.info("My name is {}.", "John")
    // 输出 INFO 日志

    logger.warn("Warn.")
    logger.warn("My name is {}.", "John")
    logger.warnThrowable("输出异常信息", RuntimeException("Runtime Exception"))
    // 输出 WARN 日志

    logger.error("Error.")
    logger.error("My name is {}.", "John")
    logger.errorThrowable("输出异常信息", RuntimeException("Runtime Exception"))
    // 输出 ERROR 日志
    val logger2 = LoggerFactory.getLogger(Void::class)
    logger2.info("Hello World")
}

internal class Test {
    @Test
    fun test() {
        main()
        withLogTrace {
            println("level:{}", "trace")
        }
        withLogDebug {
            println("level:{}", "debug")
        }
        withLogInfo {
            println("level:{}", "info")
        }
        withLogError {
            println("level:{}", "error")
        }
        withLogWarn {
            println("level:{}", "warn")
        }
    }
}
