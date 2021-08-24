package com.github.openEdgn.logger4k

import kotlin.reflect.KClass

/**
 * 日志工厂
 */
interface ILoggerFactory {
    /**
     * 根据 java class 得到日志实例
     */
    fun getLogger(clazz: Class<*>): ILogger

    /**
     * 根据 kotlin class 得到日志实例
     */
    fun getLogger(clazz: KClass<*>): ILogger

    /**
     *  根据名称得到日志实例
     */
    fun getLogger(name: String): ILogger
}
