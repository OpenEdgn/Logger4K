package logger4k.forward.jul

import com.github.openEdgn.logger4k.api.ILogOutputApi
import logger4k.forward.IForwardApi

class JULLogApi : IForwardApi {
    override val checkClassName = "java.util.logging.Level"
    override val name = "Java Utils Logging"

    override fun init(): ILogOutputApi {
        return JULLogOutputApi()
    }
}
