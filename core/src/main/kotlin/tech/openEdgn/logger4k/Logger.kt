/*
 * Copyright (c) 2020, Open EDGN. All rights reserved.
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
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package tech.openEdgn.logger4k

import java.util.*

/**
 * 日志实现类
 */
class Logger constructor(private val clazz: Class<out Any>) : ILogger {

    override fun debugOnly(function: ILogger.() -> Unit): ILogger {
        if (isDebug) {
            function(this)
        }
        return this
    }


    override fun info(message: Any, exception: Throwable?): ILogger {
        outputLogger(System.currentTimeMillis(), LoggerLevel.INFO, message, exception)
        return this
    }

    override fun debug(message: Any, exception: Throwable?): ILogger {
        outputLogger(System.currentTimeMillis(), LoggerLevel.DEBUG, message, exception)
        return this
    }

    override fun warn(message: Any, exception: Throwable?): ILogger {
        outputLogger(System.currentTimeMillis(), LoggerLevel.WARN, message, exception)
        return this
    }

    override fun error(message: Any, exception: Throwable?): ILogger {
        outputLogger(System.currentTimeMillis(), LoggerLevel.ERROR, message, exception)
        return this
    }

    private fun outputLogger(loggerDate: Long, level: LoggerLevel, message: Any, exception: Throwable?) {
        LoggerConfig.output.writeLine(LoggerItem(clazz, loggerDate,
                Thread.currentThread().name.toUpperCase(Locale.ENGLISH), level, message, exception))
    }


    override val isDebug: Boolean = LoggerConfig.isDebug

}