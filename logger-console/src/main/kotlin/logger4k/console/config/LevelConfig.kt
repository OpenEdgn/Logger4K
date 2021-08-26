package logger4k.console.config

import com.github.openEdgn.logger4k.LoggerLevel
import logger4k.console.PackageLevel
import logger4k.console.utils.Config

/**
 * 日志等级
 */
object LevelConfig : Config.ConfigItem<LevelConfig.PackageLevelData> {
    override val key = "logger.level"
    override val defaultValue = PackageLevelData(mapOf(Pair("", LoggerLevel.INFO)), PackageLevel(LoggerLevel.INFO))
    override fun toString(data: PackageLevelData): String {
        val rule = data.rule.map { "$it.key=${it.value}" }.toMutableList()
        rule.add("*=${data.level.superLevel}")
        return rule.joinToString(separator = ",", prefix = "[", postfix = "]")
    }

    override fun parseString(data: String): PackageLevelData {
        if ((data.startsWith("[") && data.endsWith("]")).not()) {
            throw RuntimeException("格式化失败！")
        }
        val childData = data.substring(1, data.length - 1)
        val item = childData.split(",").associate {
            val split = it.split("=")
            if (split.size < 2) {
                throw RuntimeException("无法切割字段：$it")
            }
            Pair(split.first(), LoggerLevel.valueOf(split[1].uppercase()))
        }.toMutableMap()
        val packageLevel = PackageLevel(item["*"] ?: LoggerLevel.INFO)
        item.remove("*")
        item.forEach { (t, u) ->
            packageLevel.putPackageLevel(t, u)
        }
        return PackageLevelData(item, packageLevel)
    }

    class PackageLevelData(val rule: Map<String, LoggerLevel>, val level: PackageLevel)
}
