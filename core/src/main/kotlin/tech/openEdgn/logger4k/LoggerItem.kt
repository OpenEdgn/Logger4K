package tech.openEdgn.logger4k

import java.text.SimpleDateFormat


data class LoggerItem(
        val clazz: Class<out Any>,
        val loggerDate: Long,
        val threadName: String,
        val level: LoggerLevel,
        val message: String,
        val exception: Throwable?) {
    override fun toString() = "${dateFormat.format(loggerDate)} - ${clazz.simpleName} - $threadName - ${level.name}:$message -> ${exception?.message}"

    companion object {
        val dateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
    }
}
