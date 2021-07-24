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

import com.github.openEdgn.logger4k.ILogger
import com.github.openEdgn.logger4k.LoggerLevel
import kotlin.reflect.KClass

/**
 * plugin register info
 */
interface IPlugin {
    /**
     * plugin name
     */
    val name: String
        get() = javaClass.name

    /**
     * 略过优化
     */
    val ignoreOptimization: Boolean
        get() = false

    /**
     * 根据 `KClass` 来获取 Logger 实例
     * @param kClass KClass<*>
     * @return ILogger
     */
    fun getLogger(clazz: KClass<*>): ILogger

    /**
     * 根据名称获取 Logger 实例
     * @param name String
     * @return ILogger
     */
    fun getLogger(name: String): ILogger

    /**
     * 根据名称得到日志等级
     *
     * @param name String 名称
     * @return LoggerLevel 日志等级
     */
    fun getLoggerLevel(name: String): LoggerLevel

    /**
     * 销毁插件模块
     */
    fun shutdown() {
    }
}
