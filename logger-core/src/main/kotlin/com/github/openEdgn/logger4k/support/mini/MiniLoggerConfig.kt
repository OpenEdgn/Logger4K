package com.github.openEdgn.logger4k.support.mini

import com.github.openEdgn.logger4k.ILogger
import com.github.openEdgn.logger4k.Logger4KConfig
import com.github.openEdgn.logger4k.LoggerLevel
import com.github.openEdgn.logger4k.support.mini.pkgFilter.PackageLevel
import com.github.openEdgn.logger4k.utils.SystemEnvProperties
import com.github.openEdgn.logger4k.utils.format.log.BaseLogFormat
import com.github.openEdgn.logger4k.utils.format.log.LogFormat
import java.io.PrintStream

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
    val packageLogRules: PackageLevel =
        env.getCustomOrDefault(
            "rules",
            PackageLevel(defaultLevel)
        ) {
            val packageLevel = PackageLevel(defaultLevel)
            for (data in it.split(";")) {
                val split = data.split("=")
                if (split.size == 2) {
                    try {
                        packageLevel.putPackageLevel(split[0], LoggerLevel.valueOf(split[1]))
                    } catch (_: Exception) {
                    }
                }
            }
            packageLevel
        }

    /**
     * 普通日志格式化样式
     */
    val messageFormat: BaseLogFormat = LogFormat(
        env.getStringOrDefault(
            "format.line",
            "%{date->YY/MM/dd HH:mm:ss} - %{level->5} " +
                "- %{package->30}:%{message}"
        )
    )

    /**
     * 带错误的日志格式化样式
     */
    val throwFormat: BaseLogFormat = LogFormat(
        env.getStringOrDefault(
            "format.throw",
            "%{date->YY/MM/dd HH:mm:ss} - %{level->5} " +
                "- %{package->30}:%{message}%{line}%{throws->ALL}"
        )
    )

    init {
        packageLogRules.putPackageLevel(ILogger::class.java.name, Logger4KConfig.level)
    }

    fun getPrintStream(level: LoggerLevel): PrintStream {
        if (level == LoggerLevel.OFF) {
            throw RuntimeException("STOP! 此时不应该输出日志")
        }
        return if (level.level < LoggerLevel.WARN.level) {
            System.out
        } else {
            System.err
        }
    }
}
