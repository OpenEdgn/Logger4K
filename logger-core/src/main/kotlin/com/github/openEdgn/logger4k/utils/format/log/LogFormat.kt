package com.github.openEdgn.logger4k.utils.format.log

import com.github.openEdgn.logger4k.LoggerLevel
import com.github.openEdgn.logger4k.utils.format.datas.BaseFormatRule
import com.github.openEdgn.logger4k.utils.format.datas.impl.LevelRuleInfo
import com.github.openEdgn.logger4k.utils.format.datas.impl.MessageRuleInfo
import com.github.openEdgn.logger4k.utils.format.datas.impl.NewLineRuleInfo
import com.github.openEdgn.logger4k.utils.format.datas.impl.PkgNameRuleInfo
import com.github.openEdgn.logger4k.utils.format.datas.impl.ThreadRuleInfo
import com.github.openEdgn.logger4k.utils.format.datas.impl.ThrowsRuleInfo
import com.github.openEdgn.logger4k.utils.format.datas.impl.TimeRuleInfo
import java.lang.StringBuilder
import java.util.LinkedList
import java.util.regex.Pattern

/**
 *
 *         "%{date->YY/MM/dd HH:mm:ss} - %{level->1} - %{package->20}:%{message}%{line}%{throws->ALL}"
 */
class LogFormat(rule: String) : BaseLogFormat(rule) {
    private val rules = arrayOf(
        LevelRuleInfo(), MessageRuleInfo(), NewLineRuleInfo(),
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

    override fun printLogger(
        packageName: String,
        threadName: String,
        date: Long,
        level: LoggerLevel,
        message: String,
        throws: Throwable?
    ): String {
        val data = localThread.get()
        data["level"] = level
        data["message"] = message
        data["package"] = packageName
        data["thread"] = threadName
        data["throws"] = throws
        data["date"] = date
        return ruleOutputList.joinToString("") { it.format(data) }
    }
}
