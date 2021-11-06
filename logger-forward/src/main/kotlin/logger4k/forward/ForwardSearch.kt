package logger4k.forward

import com.github.openEdgn.logger4k.api.ILogApi
import com.github.openEdgn.logger4k.api.ILoggerSearch
import logger4k.forward.aul.AULLogApi
import logger4k.forward.jul.JULLogApi
import logger4k.forward.log4j.Log4jLogApi
import logger4k.forward.slf4j.SLF4JLogApi
import java.util.Optional
import java.util.ServiceLoader

class ForwardSearch : ILoggerSearch {
    private val rules = setOf(
        AULLogApi(),
        SLF4JLogApi(),
        Log4jLogApi(),
        JULLogApi(),
    )

    override fun search(): Set<ILogApi> {
        return ServiceLoader.load(ILogApi::class.java)
            .findFirst()
            .or {
                rules.firstOrNull {
                    try {
                        Class.forName(it.checkClassName)
                        true
                    } catch (e: Exception) {
                        false
                    }
                }.let { Optional.ofNullable(it) }
            }
            .map { setOf(it) }
            .orElse(emptySet())
    }
}
