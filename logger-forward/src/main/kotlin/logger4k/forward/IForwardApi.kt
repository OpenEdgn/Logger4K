package logger4k.forward

import com.github.openEdgn.logger4k.api.ILogApi

interface IForwardApi : ILogApi {
    val checkClassName: String
}
