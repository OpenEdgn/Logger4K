package logger4k.console

import com.github.openEdgn.logger4k.api.ILogApi
import com.github.openEdgn.logger4k.api.ILogOutputApi
import logger4k.console.config.CacheConfig

class ConsoleLogApi : ILogApi {

    override val name = "ConsoleLog"

    override fun init(): ILogOutputApi {
        return ConsoleLogConfig.config.get(CacheConfig)
    }
}
