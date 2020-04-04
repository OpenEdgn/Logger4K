package tech.openEdgn.logger4k


data class LoggerItem(
        val clazz: Class<out Any>,
        val loggerDate: Long,
        val threadName: String,
        val level: LoggerLevel,
        val message: String,
        val exception: Throwable?)
