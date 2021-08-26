package logger4k.console.utils.format

import com.github.openEdgn.logger4k.LoggerLevel
import java.time.LocalDateTime

/**
 * 日志格式化方案接口
 *
 * @constructor
 */
abstract class BaseLogFormat(val rule: String) {

    abstract fun loggerToString(
        name: String,
        thread: Thread,
        date: LocalDateTime,
        level: LoggerLevel,
        message: String,
        exception: Throwable?
    ): String
}
