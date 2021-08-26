import com.github.openEdgn.logger4k.LoggerFactory
import org.junit.jupiter.api.Test
import java.text.SimpleDateFormat

internal class PrintLogger {
    /**
     * 默认情况下无日志抛出
     * @return Unit
     */
    @Test
    fun test1() {
        val logger = LoggerFactory.getLogger(javaClass)
        logger.trace("Hello World")
        logger.debug("Hello World")
        logger.info("Hello World")
        logger.warn("Hello World")
        logger.error("Hello World")
        val thread = Thread {
            logger.error("New Thread")
        }
        thread.name = "Test Thread"
        thread.start()
    }

    @Test
    fun test2() {
        System.setProperty("logger.level", "[*=debug]")
        // 开启 DEBUG 模式代码
        val logger = LoggerFactory.getLogger(javaClass)
        logger.debugOnly {
            debug(
                "isDebug : {} ,date: {}", isDebug,
                SimpleDateFormat("yyyy-MM-dd")
                    .format(System.currentTimeMillis())
            )
        }
        logger.trace("Hello World,trace")
        logger.debug("Hello World,debug")
        logger.info("Hello World,info")
        logger.warn("Hello World,warn")
        logger.error("Hello World,error")
    }
}
