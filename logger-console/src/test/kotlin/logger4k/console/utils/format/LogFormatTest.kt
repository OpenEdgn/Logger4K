package logger4k.console.utils.format

import com.github.openEdgn.logger4k.LoggerLevel
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

internal class LogFormatTest {
    @Test
    fun test() {
        val rule = "%{date->YY/MM/dd HH:mm:ss} - %{level->5} - %{thread->10}" +
            "- %{package->40}:%{message}%{line}%{throws->ALL}"
        val logFormat = LogFormat(rule)
        println(
            logFormat.loggerToString(
                javaClass.name, Thread.currentThread(), LocalDateTime.now(),
                LoggerLevel.INFO, "sasa", RuntimeException("Nothing")
            )
        )
    }
}
