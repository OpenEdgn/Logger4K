package tech.openEdgn.logger4k

import jdk.nashorn.internal.ir.annotations.Ignore
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class LoggerTest {
    @Ignore
    @Test
    fun testAll() {
        println("将输出日志示例！")
        val logger = getLogger() //创建一个Logger 对象

        LoggerConfig.enableDebug()
        //开启DEBUG 模式 (1)
        enableDebug()
        //开启DEBUG 模式 (2)
        disableDebug()
        // 关闭DEBUG 模式 (1)
        LoggerConfig.enableDebug()
        // 关闭DEBUG 模式 (2)

        logger.debug("DEBUG Message.")
        //  输出 DEBUG 类型的日志 ( 注意，DEBUG未开启则不会输出日志)

        logger.info("INFO  Message.")
        //  输出 INFO 类型的日志

        logger.warn("WARN  Message.")
        //  输出 WARN 类型的日志

        logger.error("ERROR  Message.")
        //  输出 ERROR 类型的日志

        logger.debugOnly {
            //此代码块仅在调试模式下运行
            info("INFO")
            warn("WARN")
            debug("DEBUG")
            error("ERROR")
        }
    }
}