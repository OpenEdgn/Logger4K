package logger4k.console.config

import com.github.openEdgn.logger4k.api.ILogOutputApi
import logger4k.console.output.CacheLogOutputApi
import logger4k.console.output.ConsoleLogOutputApi
import logger4k.console.utils.Config

object CacheConfig : Config.ConfigItem<ILogOutputApi> {
    override val key = "logger.output.cache"
    override val defaultValue = ConsoleLogOutputApi

    override fun toString(data: ILogOutputApi): String {
        return (data == defaultValue).toString()
    }

    override fun parseString(data: String): ILogOutputApi {
        return if (data == "true") {
            CacheLogOutputApi(ConsoleLogOutputApi)
        } else {
            defaultValue
        }
    }
}
