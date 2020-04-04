package tech.openEdgn.logger4k

import kotlin.reflect.KClass


fun Any.getLogger(): ILogger = Logger(this.javaClass)
fun Any.getLogger(any:Any): ILogger = Logger(any.javaClass)
fun Class<out Any>.getLogger(): ILogger = Logger(this)
fun KClass<out Any>.getLogger(): ILogger = Logger(this.java)

fun Any.enableDebug() {
    LoggerConfig.enableDebug()
}

fun Any.disableDebug() {
    LoggerConfig.disableDebug()
}