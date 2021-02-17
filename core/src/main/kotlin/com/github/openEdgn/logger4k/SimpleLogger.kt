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

package com.github.openEdgn.logger4k

import com.github.openEdgn.logger4k.plugin.PluginManager


/**
 * 默认的 Logger 实现类
 */
abstract class SimpleLogger : ILogger {


    abstract val name: String
    abstract fun printLogger(date: Long, level: LoggerLevel, message: String)
    abstract fun printLogger(date: Long, level: LoggerLevel, message: String, exception: Throwable)

    override fun trace(message: String, vararg param: Any?): ILogger {
        val array = Array<Any?>(param.size) { null }
        System.arraycopy(param, 0, array, 0, array.size)
        printLogger(System.currentTimeMillis(), LoggerLevel.TRACE, getMessageFormat().format(message, array))
        return this
    }

    override fun debug(message: String, vararg param: Any?): ILogger {
        val array = Array<Any?>(param.size) { null }
        System.arraycopy(param, 0, array, 0, array.size)
        printLogger(System.currentTimeMillis(), LoggerLevel.DEBUG, getMessageFormat().format(message, array))
        return this
    }

    override fun info(message: String, vararg param: Any?): ILogger {
        val array = Array<Any?>(param.size) { null }
        System.arraycopy(param, 0, array, 0, array.size)
        printLogger(System.currentTimeMillis(), LoggerLevel.INFO, getMessageFormat().format(message, array))
        return this
    }

    override fun warn(message: String, vararg param: Any?): ILogger {
        val array = Array<Any?>(param.size) { null }
        System.arraycopy(param, 0, array, 0, array.size)
        printLogger(System.currentTimeMillis(), LoggerLevel.WARN, getMessageFormat().format(message, array))
        return this
    }

    override fun error(message: String, vararg param: Any?): ILogger {
        val array = Array<Any?>(param.size) { null }
        System.arraycopy(param, 0, array, 0, array.size)
        printLogger(System.currentTimeMillis(), LoggerLevel.ERROR, getMessageFormat().format(message, array))
        return this
    }

    override fun debugThrowable(message: Any, exception: Throwable): ILogger {
        printLogger(System.currentTimeMillis(), LoggerLevel.DEBUG, message.toString(), exception)
        return this
    }

    override fun infoThrowable(message: Any, exception: Throwable): ILogger {
        printLogger(System.currentTimeMillis(), LoggerLevel.INFO, message.toString(), exception)
        return this
    }

    override fun traceThrowable(message: Any, exception: Throwable): ILogger {
        printLogger(System.currentTimeMillis(), LoggerLevel.TRACE, message.toString(), exception)
        return this
    }

    override fun warnThrowable(message: Any, exception: Throwable): ILogger {
        printLogger(System.currentTimeMillis(), LoggerLevel.WARN, message.toString(), exception)
        return this
    }

    override fun errorThrowable(message: Any, exception: Throwable): ILogger {
        printLogger(System.currentTimeMillis(), LoggerLevel.ERROR, message.toString(), exception)
        return this
    }

    override val level: LoggerLevel
        get() = PluginManager.implPlugin().getLoggerLevel(name)
}
