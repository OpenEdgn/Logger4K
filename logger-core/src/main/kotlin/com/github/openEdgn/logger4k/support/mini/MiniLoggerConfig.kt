package com.github.openEdgn.logger4k.support.mini

import com.github.openEdgn.logger4k.Logger4KConfig
import com.github.openEdgn.logger4k.LoggerFactory
import com.github.openEdgn.logger4k.LoggerLevel
import com.github.openEdgn.logger4k.utils.SystemEnvProperties

/**
 * 配置文件
 */
object MiniLoggerConfig {
    private val env = SystemEnvProperties("logger.mini")

    /**
     * 默认的日志等级
     */
    val defaultLevel = env.getEnumOrDefault("defaultLevel", LoggerLevel.INFO)

    /**
     * 自定义子路径日志输出等级
     */
    val packageLogRules: Map<String, LoggerLevel> =
        env.getCustomOrDefault(
            "rules",
            mapOf(
                Pair(LoggerFactory::class.java.packageName, Logger4KConfig.level)
            )
        ) {
            val linkedMapOf = linkedMapOf<String, LoggerLevel>(
                Pair(LoggerFactory::class.java.packageName, Logger4KConfig.level)
            )
            for (data in it.split(";")) {
                val split = data.split(":")
                if (split.size == 2) {
                    try {
                        linkedMapOf[split[0]] = LoggerLevel.valueOf(split[1])
                    } catch (_: Exception) {
                    }
                }
            }
            linkedMapOf
        }

    /**
     * 普通日志格式化样式
     */
    val messageFormat = env.getStringOrDefault(
        "format.line",
        "%{date->YY/MM/dd HH:mm:ss} - %{level->1} - %{package->20}:%{message} "
    )

    /**
     * 带错误的日志格式化样式
     */
    val throwFormat = env.getStringOrDefault(
        "format.throw",
        "%{date->YY/MM/dd HH:mm:ss} - %{level->1} - %{package->20}:%{message}\r\n%{throws->ALL}"
    )
}
