/*
 * Copyright (c) 2020, OpenEDGN. All rights reserved.
 * HOME Page: https://github.com/OpenEDGN
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.openEdgn.logger4k.plugin

import com.github.openEdgn.logger4k.LoggerConfig
import java.io.Closeable
import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.isSubclassOf

/**
 * 日志实现加载器
 */
object PluginManager : Closeable {

    @Volatile
    internal var loggerPlugin: IPlugin? = null


    private const val PLUGIN_IMPL_CLASS_KEY = "logger4k.plugin.implClass"

    init {
        val properties = Properties()
        try {
            properties.load(PluginManager::class.java.getResourceAsStream("/META-INF/logger4k.properties"))
        } catch (e: Exception) {
            LoggerConfig.internalError("未在 [classpath:/META-INF/logger4k.properties] 下找到配置文件 ,跳过此路径.", e)
        }
        try {
            properties.load(PluginManager::class.java.getResourceAsStream("/logger4k.properties"))
        } catch (e: Exception) {
            LoggerConfig.internalError("未在 [classpath:/logger4k.properties]  下找到配置文件 ,跳过此路径. .", e)
        }
        val property = properties.getProperty(PLUGIN_IMPL_CLASS_KEY)
        if (property != null) {
            try {
                val pluginImplClass = (Class.forName(property) ?: throw NullPointerException("未找到对应的Plugin 实现类")).kotlin
                if (pluginImplClass.isSubclassOf(IPlugin::class)) {
                    registerPlugin(pluginImplClass)
                } else {
                    throw RuntimeException(" [${pluginImplClass.qualifiedName}] 不是 IPlugin 的实现类。")
                }
            } catch (e: Exception) {
                LoggerConfig.internalError("通过配置文件加载时发生错误", e)
            }
        } else {
            LoggerConfig.internalError("未找到可用的实现类")
        }
    }


    /**
     * 注册 Plugin Manager 插件
     * @param pluginClass KClass<IPlugin>
     * @return Unit
     */
    @Synchronized
    fun registerPlugin(pluginClass: KClass<*>) {
        try {
            val pluginImpl = (pluginClass.objectInstance ?: pluginClass.createInstance()) as IPlugin
            if (loggerPlugin != null) {
                if (loggerPlugin!!.javaClass != pluginClass) {
                    loggerPlugin = pluginImpl
                }
            } else {
                loggerPlugin = pluginImpl
            }
        } catch (_: Exception) {
        }
    }


    override fun close() {

    }
}