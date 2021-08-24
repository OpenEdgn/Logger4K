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

package com.github.openEdgn.logger4k.internal

import com.github.openEdgn.logger4k.ILogger
import com.github.openEdgn.logger4k.LoggerLevel
import com.github.openEdgn.logger4k.api.ILogOutputApi
import com.github.openEdgn.logger4k.utils.IMessageFormat
import java.time.LocalDateTime

/**
 * 默认的 Logger 实现类
 *
 */
internal class SimpleLogger(
    private val name: String,
    private val api: ILogOutputApi,
    private val messageFormat: IMessageFormat
) : ILogger {

    override fun trace(message: String, vararg param: Any?): ILogger {
        if (level.level > LoggerLevel.TRACE.level) {
            return this
        }
        val array = Array<Any?>(param.size) { null }
        System.arraycopy(param, 0, array, 0, array.size)
        api.outputLogger(
            name, Thread.currentThread(), LocalDateTime.now(),
            LoggerLevel.TRACE, messageFormat.format(message, array)
        )
        return this
    }

    override fun debug(message: String, vararg param: Any?): ILogger {
        if (level.level > LoggerLevel.DEBUG.level) {
            return this
        }
        val array = Array<Any?>(param.size) { null }
        System.arraycopy(param, 0, array, 0, array.size)
        api.outputLogger(
            name, Thread.currentThread(), LocalDateTime.now(),
            LoggerLevel.DEBUG, messageFormat.format(message, array)
        )
        return this
    }

    override fun info(message: String, vararg param: Any?): ILogger {
        if (level.level > LoggerLevel.INFO.level) {
            return this
        }
        val array = Array<Any?>(param.size) { null }
        System.arraycopy(param, 0, array, 0, array.size)
        api.outputLogger(
            name, Thread.currentThread(), LocalDateTime.now(),
            LoggerLevel.INFO, messageFormat.format(message, array)
        )
        return this
    }

    override fun warn(message: String, vararg param: Any?): ILogger {
        if (level.level > LoggerLevel.WARN.level) {
            return this
        }
        val array = Array<Any?>(param.size) { null }
        System.arraycopy(param, 0, array, 0, array.size)
        api.outputLogger(
            name, Thread.currentThread(), LocalDateTime.now(),
            LoggerLevel.WARN, messageFormat.format(message, array)
        )
        return this
    }

    override fun error(message: String, vararg param: Any?): ILogger {
        if (level.level > LoggerLevel.ERROR.level) {
            return this
        }
        val array = Array<Any?>(param.size) { null }
        System.arraycopy(param, 0, array, 0, array.size)
        api.outputLogger(
            name, Thread.currentThread(), LocalDateTime.now(),
            LoggerLevel.ERROR, messageFormat.format(message, array)
        )
        return this
    }

    override fun debugThrowable(message: Any, exception: Throwable): ILogger {
        if (level.level > LoggerLevel.DEBUG.level) {
            return this
        }
        api.outputLogger(
            name, Thread.currentThread(), LocalDateTime.now(),
            LoggerLevel.DEBUG, message.toString(), exception
        )
        return this
    }

    override fun infoThrowable(message: Any, exception: Throwable): ILogger {
        if (level.level > LoggerLevel.INFO.level) {
            return this
        }
        api.outputLogger(
            name, Thread.currentThread(), LocalDateTime.now(),
            LoggerLevel.INFO, message.toString(), exception
        )
        return this
    }

    override fun traceThrowable(message: Any, exception: Throwable): ILogger {
        if (level.level > LoggerLevel.TRACE.level) {
            return this
        }
        api.outputLogger(
            name, Thread.currentThread(), LocalDateTime.now(),
            LoggerLevel.TRACE, message.toString(), exception
        )
        return this
    }

    override fun warnThrowable(message: Any, exception: Throwable): ILogger {
        if (level.level > LoggerLevel.WARN.level) {
            return this
        }
        api.outputLogger(
            name, Thread.currentThread(), LocalDateTime.now(),
            LoggerLevel.WARN, message.toString(), exception
        )
        return this
    }

    override fun errorThrowable(message: Any, exception: Throwable): ILogger {
        if (level.level > LoggerLevel.ERROR.level) {
            return this
        }
        api.outputLogger(
            name, Thread.currentThread(), LocalDateTime.now(),
            LoggerLevel.ERROR, message.toString(), exception
        )
        return this
    }

    override fun traceOnly(function: ILogger.() -> Unit): ILogger {
        if (level == LoggerLevel.TRACE) {
            function(this)
        }
        return this
    }

    override fun debugOnly(function: ILogger.() -> Unit): ILogger {
        if (level == LoggerLevel.DEBUG) {
            function(this)
        }
        return this
    }

    override val level: LoggerLevel
        get() = api.outputLevel(name)

    override val isDebug: Boolean
        get() = level == LoggerLevel.DEBUG
}
