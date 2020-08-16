package com.github.openEdgn.logger4k.plugin

import com.github.openEdgn.logger4k.ILogger
import kotlin.reflect.KClass

/**
 * plugin register info
 */
interface IPlugin {
    /**
     * plugin name
     */
    val name:String
    get() = javaClass.name

    /**
     * 根据 `KClass` 来获取 Logger 实例
     * @param kClass KClass<*>
     * @return ILogger
     */
    fun getLogger(kClass: KClass<*>):ILogger


}