package logger4k.console

import com.github.openEdgn.logger4k.getLogger
import org.junit.jupiter.api.Test

class LoggerTest {
    @Test
    fun test() {
        System.setProperty("log.internal.debug", "true")
        val logger = getLogger()
        logger.info("Hello World .{}", "dragon")
    }
}
