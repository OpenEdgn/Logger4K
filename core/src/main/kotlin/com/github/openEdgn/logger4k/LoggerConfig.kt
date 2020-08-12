/*
 * Copyright (c) 2020, Open EDGN. All rights reserved.
 *
 * HOME Page: https://github.com/Open-EDGN
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.openEdgn.logger4k

import com.github.openEdgn.logger4k.console.ConsolePlugin
import java.io.Closeable
import java.io.PrintStream
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

/**
 * 日志配置类
 */
object LoggerConfig {
    private val internalConf = InternalLoggerConfig()
    private val logger = getLogger()

    init {
        Runtime.getRuntime().run {
            addShutdownHook(Thread {
                internalConf.close()
            })
        }
        registerExtra(ConsolePlugin::class)
    }

    /**
     * 标准控制台输出
     */
    val consoleOutputStream: PrintStream
        get() = internalConf.output

    /**
     * 标准控制台错误输出
     */
    val consoleErrorOutputStream: PrintStream
        get() = internalConf.errorOutput


    val isDebug
        get() = internalConf.debugMode


    /**
     * 开启 DEBUG 模式
     */
    fun enableDebug() {
        internalConf.debugMode = true

    }


    /**
     * 关闭 DEBUG 模式
     */
    fun disableDebug() {
        internalConf.debugMode = false
    }

    /**
     * 日志输出类
     */
    val output: IOutput
        get() = internalConf.loggerOutput

    /**
     *   自定义单个日志转化成字符串方法
     */
    val itemFormat: (LoggerLine) -> String
        get() = internalConf.lineFormat


    /**
     *  注册插件
     * @param pluginClazz  KClass<out IExtra>
     */
    fun registerExtra(pluginClazz: KClass<out IPlugin>) = internalConf.registerExtra(pluginClazz)


    class InternalLoggerConfig : Closeable {
        @Volatile
        lateinit var loggerOutput: IOutput

        @Volatile
        lateinit var output: PrintStream

        @Volatile
        lateinit var errorOutput: PrintStream


        var debugMode: Boolean
            get() = debug
            @Synchronized
            set(value) {
                debug = value
                if (value) {
                    logger.warn("注意!DEBUG 模式已打开!")
                } else {
                    logger.info("DEBUG 模式已关闭!")
                }
            }

        @Volatile
        private var debug: Boolean = false

        @Volatile
        private var plugin: IPlugin? = null

        @Volatile
        lateinit var lineFormat: (LoggerLine) -> String

        @Synchronized
        fun registerExtra(pluginClazz: KClass<out IPlugin>): Boolean {
            plugin?.run {
                try {
                    unregister()
                } catch (_: UninitializedPropertyAccessException) {
                } catch (e: Exception) {
                    System.err.println("取消扩展注册时发生错误！[${e.message}]")
                    return false
                }
            }
            plugin = pluginClazz.createInstance()
            plugin?.register(this)
            logger.info("已注册来自 [${plugin!!.javaClass.name}] 的扩展.")
            return true
        }

        override fun close() {
            plugin?.run {
                try {
                    unregister()
                } catch (_: Exception) {
                }
            }
            loggerOutput.close()
        }
    }
}