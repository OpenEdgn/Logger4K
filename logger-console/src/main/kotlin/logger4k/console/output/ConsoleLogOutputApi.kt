package logger4k.console.output

import com.github.openEdgn.logger4k.LoggerLevel
import com.github.openEdgn.logger4k.api.ILogOutputApi
import logger4k.console.ConsoleLogConfig
import logger4k.console.config.LevelConfig
import logger4k.console.config.LineErrorFormatConfig
import logger4k.console.config.LineFormatConfig
import logger4k.console.config.LogOutputConfig
import logger4k.console.utils.Config
import java.time.LocalDateTime

object ConsoleLogOutputApi : ILogOutputApi {
    private val config: Config = ConsoleLogConfig.config
    private val packageLevel by lazy {
        config.get(LevelConfig).level
    }
    private val lineFormatRule by lazy {
        config.get(LineFormatConfig)
    }
    private val errorLineFormatRule by lazy {
        config.get(LineErrorFormatConfig)
    }
    private val output by lazy {
        config.get(LogOutputConfig)
    }

    override fun outputLevel(name: String): LoggerLevel {
        return packageLevel.getPackageLevel(name)
    }

    override fun outputLogger(
        name: String,
        thread: Thread,
        date: LocalDateTime,
        level: LoggerLevel,
        message: String
    ) {
        output.println(lineFormatRule.loggerToString(name, thread, date, level, message, null))
    }

    override fun outputLogger(
        name: String,
        thread: Thread,
        date: LocalDateTime,
        level: LoggerLevel,
        message: String,
        exception: Throwable
    ) {
        output.println(errorLineFormatRule.loggerToString(name, thread, date, level, message, exception))
    }
}
