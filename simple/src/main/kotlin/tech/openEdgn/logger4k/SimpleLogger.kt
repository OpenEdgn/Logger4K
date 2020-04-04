package tech.openEdgn.logger4k

import java.io.IOException

class SimpleLogger {
    private val logger = getLogger()

    init {
        logger.info("info Init") // output Info message
        logger.debug("debug Init")// output debug message
        logger.warn("warn Init") // output warn message
        logger.error("error Init") // output error message
    }

    fun outputException() {
        try {
            throw IOException("IO EXCEPTION MESSAGE.")
        } catch (e: Exception) {
            logger.info("info message.", e)
            logger.debug("debug message.", e)
            logger.warn("warn message.", e)
            logger.error("error message.", e)
        }

    }
}

fun main() {
    SimpleLogger()
    //在控制台会输出3行日志
    LoggerConfig.enableDebug()
    //开启DEBUG 后再次运行
    SimpleLogger()
    //在控制台会输出4行日志
    SimpleLogger().outputException()
    // 输出异常信息


}