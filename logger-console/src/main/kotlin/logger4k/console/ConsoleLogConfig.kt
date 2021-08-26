package logger4k.console

import logger4k.console.utils.Config

object ConsoleLogConfig {
    /**
     * 项目配置
     */
    val config by lazy {
        val data = System.getenv().toMutableMap()
        data.putAll(System.getProperties().map { Pair(it.key.toString(), it.value.toString()) })
        Config(data)
    }
}
