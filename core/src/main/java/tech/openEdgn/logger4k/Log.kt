package tech.openEdgn.logger4k

import java.lang.RuntimeException
import kotlin.reflect.KClass
import kotlin.reflect.KFunction

/**
 * # 日志类型划分
 *
 * 1. DEBUG: 调试
 * 2. INFO: 标准
 * 3. WARN: 警告
 * 4. ERROR: 错误
 * @constructor 数字等级
 */
enum class LoggerLevel(val levelInt: Int) {
    DEBUG(0),
    INFO(1),
    WARN(2),
    ERROR(3);
companion object{
    fun level2String(level: Int): String {
        for (value in LoggerLevel.values()) {
            if (value.levelInt == level) {
                return value.toString()
            }
        }
        throw RuntimeException("Stub!")
    }
}
}


interface DebugOnly{
    fun run(log: Log)
}

/**
 *  # Logger 日志框架核心
 *
 *  > by ExplodingFKL
 *
 */
interface Log {
    /**
     *  # 仅在调试模式下执行代码块
     *
     * @param t Function1<Logger, Unit>
     * @return Log logger 实例
     */
    fun debugOnly(t:(Log)->Unit):Log

    /**
     *  # 仅在调试模式下执行代码块 (Java Lambda 适配)
     *
     * @param debugOnly DebugOnly
     * @return Log logger 实例
     */
    fun debugOnly(debugOnly: DebugOnly):Log

    /**
     * # 输出标准日志
     *
     * @param message String 日志信息
     * @param exception Throwable? 抛出异常
     * @return Log logger 实例
     */
    fun info(message: String, exception: Throwable? = null):Log
    /**
     * # 输出调试日志
     *
     * @param message String 日志信息
     * @param exception Throwable? 抛出异常
     * @return Log logger 实例
     */
    fun debug(message: String, exception: Throwable? = null) :Log

    /**
     * # 输出警告日志
     *
     * @param message String 日志信息
     * @param exception Throwable? 抛出异常
     * @return Log logger 实例
     */
    fun warn(message: String, exception: Throwable? = null) :Log

    /**
     * # 输出错误日志
     *
     * @param message String 日志信息
     * @param exception Throwable? 抛出异常
     * @return Log logger 实例
     */
    fun error(message: String, exception: Throwable? = null) :Log

    /**
     * # 是否处于调试模式
     */
    val isDebug: Boolean


    companion object{
        private val defaultImplClass: KClass<out Log> = LoggerConfig.implClass

        fun getLogger(clazz: Class<out Any>):Log{
            return Logger(clazz)
        }
        fun getLogger(clazz: KClass<out Any>) = getLogger(clazz.java)

        fun getLogger(clazz: Any) = getLogger(clazz.javaClass)

    }
}