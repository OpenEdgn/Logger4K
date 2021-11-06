package logger4k.forward.aul

import com.github.openEdgn.logger4k.api.ILogOutputApi
import logger4k.forward.IForwardApi

class AULLogApi : IForwardApi {
    override val checkClassName = "android.util.Log"
    override val name = "Android Log"

    override fun init(): ILogOutputApi {
        return AULLogOutputApi()
    }
}
