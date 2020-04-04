package tech.openEdgn.logger4k


/**
 *  # Logger 日志框架核心
 *
 *  > by ExplodingFKL
 *
 */
interface ILogger {
    /**
     *  # 仅在调试模式下执行代码块
     *
     * @return Log logger 实例
     */
    fun debugOnly(function: ILogger.() -> Unit): ILogger

    /**
     * # 输出标准日志
     *
     * @param message String 日志信息
     * @param exception Throwable? 抛出异常
     * @return Log logger 实例
     */
    fun info(message: String, exception: Throwable? = null): ILogger

    /**
     * # 输出调试日志
     *
     * @param message String 日志信息
     * @param exception Throwable? 抛出异常
     * @return Log logger 实例
     */
    fun debug(message: String, exception: Throwable? = null): ILogger

    /**
     * # 输出警告日志
     *
     * @param message String 日志信息
     * @param exception Throwable? 抛出异常
     * @return Log logger 实例
     */
    fun warn(message: String, exception: Throwable? = null): ILogger

    /**
     * # 输出错误日志
     *
     * @param message String 日志信息
     * @param exception Throwable? 抛出异常
     * @return Log logger 实例
     */
    fun error(message: String, exception: Throwable? = null): ILogger

    /**
     * # 是否处于调试模式
     */
    val isDebug: Boolean


}