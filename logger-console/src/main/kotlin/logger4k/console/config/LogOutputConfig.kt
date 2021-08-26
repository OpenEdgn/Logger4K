package logger4k.console.config

import logger4k.console.utils.Config
import java.io.PrintStream

object LogOutputConfig : Config.ConfigItem<PrintStream> {
    override val key = "logger.output"
    override val defaultValue: PrintStream = System.out

    override fun toString(data: PrintStream): String {
        return "System.out"
    }

    override fun parseString(data: String): PrintStream {
        return if (data == "System.err") {
            System.err
        } else {
            System.out
        }
    }
}
