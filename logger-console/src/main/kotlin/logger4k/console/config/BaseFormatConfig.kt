package logger4k.console.config

import logger4k.console.utils.Config
import logger4k.console.utils.format.BaseLogFormat
import logger4k.console.utils.format.LogFormat

abstract class BaseFormatConfig : Config.ConfigItem<BaseLogFormat> {

    override fun toString(data: BaseLogFormat): String {
        return data.rule
    }

    override fun parseString(data: String): BaseLogFormat {
        return LogFormat(data)
    }
}
