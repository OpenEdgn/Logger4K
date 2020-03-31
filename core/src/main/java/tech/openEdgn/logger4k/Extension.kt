package tech.openEdgn.logger4k

import kotlin.reflect.KClass


fun Any.getLogger() = Log.getLogger(this.javaClass)
fun Class<out Any>.getLogger() = Log.getLogger(this)
fun KClass<out Any>.getLogger() =    Log.getLogger(this)
fun Any.enableDebug(){
    LoggerConfig.enableDebug()
}
fun Any.disableDebug() {
    LoggerConfig.disableDebug()
}