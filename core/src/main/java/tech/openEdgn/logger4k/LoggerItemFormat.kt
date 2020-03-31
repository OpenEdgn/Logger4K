package tech.openEdgn.logger4k

class LoggerItemFormat(private val name: String,
                       private val defaultParams: Any,
                       private val format: (Any, Any) -> String):Comparable<LoggerItemFormat> {
    //排序的索引
    var index = -1
    var lastIndex = -1
    lateinit var configParams: Any
    lateinit var rawConfigParams:String
    val startText = "@{$name"
    val LastText = "}"
    fun initFormatLine(line: String): Boolean {
        index = line.indexOf(startText)
        if (index == -1) {
            return false
        }
        lastIndex = line.indexOf(LastText, index)
        if (lastIndex == -1) {
            return false
        }
        val rawConfigValue = line.substring(index + startText.length, lastIndex)
        configParams = if (rawConfigValue.isEmpty()) {
            defaultParams
        } else {
            if (rawConfigValue.startsWith(":")) {
                rawConfigValue.substring(1)
            } else {
                defaultParams
            }
        }
        return true
    }

    fun anyToString(data: Any): String {
        return format(data, configParams)
    }

    override fun compareTo(other: LoggerItemFormat): Int {
        return index - other.index
    }
    fun toPropString() = "$startText$rawConfigParams$lastIndex"

}