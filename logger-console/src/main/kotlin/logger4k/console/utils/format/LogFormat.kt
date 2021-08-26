package logger4k.console.utils.format

import com.github.openEdgn.logger4k.LoggerLevel
import logger4k.console.utils.format.impl.LogLevelRuleInfo
import logger4k.console.utils.format.impl.MessageRuleInfo
import logger4k.console.utils.format.impl.NewLineRuleInfo
import logger4k.console.utils.format.impl.PkgNameRuleInfo
import logger4k.console.utils.format.impl.ThreadRuleInfo
import logger4k.console.utils.format.impl.ThrowsRuleInfo
import logger4k.console.utils.format.impl.TimeRuleInfo
import java.time.LocalDateTime
import java.util.LinkedList
import java.util.regex.Pattern

/**
 * 日志格式化
 */
class LogFormat(rule: String) : BaseLogFormat(rule) {
    private val rules = arrayOf(
        LogLevelRuleInfo(), MessageRuleInfo(), NewLineRuleInfo(),
        PkgNameRuleInfo(), ThreadRuleInfo(), ThrowsRuleInfo(),
        TimeRuleInfo()
    ).associateBy { it.name }
    private val ruleOutputList: List<InternalFormat>

    interface InternalFormat {
        fun format(map: Map<String, Any?>): String
    }

    class DynamicFormat<T : Any?>(private val formatRule: BaseFormatRule<T>) : InternalFormat {
        @Suppress("UNCHECKED_CAST")
        override fun format(map: Map<String, Any?>): String {
            return formatRule.format(map[formatRule.name] as T)
        }
    }

    class StaticFormat(private val data: String) : InternalFormat {
        override fun format(map: Map<String, Any?>): String {
            return data
        }
    }

    init {
        val msg = StringBuilder()
        msg.append(rule)
        val regex = Regex("%\\{.+?}")
        val compile = Pattern.compile(regex.pattern)
        val matcher = compile.matcher(rule)
        val ruleList = ArrayList<InternalFormat>()
        var lastFindIndex = 0
        while (matcher.find()) {
            val item = matcher.group()
            val group = item.substring(2, item.length - 1)
            val split = group.split(Regex("->"), 2)
            val childRule = rules[split[0]]
            if (childRule != null) {
                lastFindIndex = msg.indexOf(item, lastFindIndex)
                msg.replace(lastFindIndex, lastFindIndex + item.length, "{rule:split}")
                val formatRule = if (split.size > 1) {
                    childRule.generateRule(split[1])
                } else {
                    childRule.generateRule()
                }
                ruleList.add(DynamicFormat(formatRule))
            }
        }
        val staticRuleList = msg.split("{rule:split}").map { StaticFormat(it) }
        val internalExportRules = LinkedList<InternalFormat>()
        for (i in 0 until (staticRuleList.size + ruleList.size)) {
            if (i % 2 == 0) {
                internalExportRules.add(staticRuleList[i / 2])
            } else {
                internalExportRules.add(ruleList[i / 2])
            }
        }
        ruleOutputList = internalExportRules
    }

    private val localThread = object : ThreadLocal<HashMap<String, Any?>>() {
        override fun initialValue(): HashMap<String, Any?> {
            return HashMap(6, 1f)
        }
    }

    override fun loggerToString(
        name: String,
        thread: Thread,
        date: LocalDateTime,
        level: LoggerLevel,
        message: String,
        exception: Throwable?
    ): String {
        val data = localThread.get()
        data["level"] = level
        data["message"] = message
        data["package"] = name
        data["thread"] = thread
        data["throws"] = exception
        data["date"] = date
        return ruleOutputList.joinToString("") { it.format(data) }
    }
}
