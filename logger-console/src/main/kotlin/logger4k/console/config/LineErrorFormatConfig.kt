package logger4k.console.config

import logger4k.console.utils.format.LogFormat

object LineErrorFormatConfig : BaseFormatConfig() {
    private const val rule = "%{date->YY/MM/dd HH:mm:ss} - %{level->5} - %{thread->10}" +
        " - %{package->40}:%{message}%{line}%{throws->ALL}"
    override val key = "logger.format.error"
    override val defaultValue = LogFormat(rule)
}
