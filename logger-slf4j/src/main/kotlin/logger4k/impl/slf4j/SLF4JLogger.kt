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

package logger4k.impl.slf4j

import com.github.openEdgn.logger4k.ILogger
import com.github.openEdgn.logger4k.LoggerLevel
import com.github.openEdgn.logger4k.SimpleLogger
import org.slf4j.LoggerFactory

class SLF4JLogger(override var name: String) : SimpleLogger() {

    private val slf4jLogger = LoggerFactory.getLogger(name)

    override fun printLogger(level: LoggerLevel, message: String) {
        when (level) {
            LoggerLevel.TRACE -> {
                slf4jLogger.trace(message)
            }
            LoggerLevel.DEBUG -> {
                slf4jLogger.debug(message)
            }
            LoggerLevel.INFO -> {
                slf4jLogger.info(message)
            }
            LoggerLevel.WARN -> {
                slf4jLogger.warn(message)
            }
            LoggerLevel.ERROR -> {
                slf4jLogger.error(message)
            }
        }
    }

    override fun printLogger(level: LoggerLevel, message: String, exception: Throwable) {
        when (level) {
            LoggerLevel.TRACE -> {
                slf4jLogger.trace(message, exception)
            }
            LoggerLevel.DEBUG -> {
                slf4jLogger.debug(message, exception)
            }
            LoggerLevel.INFO -> {
                slf4jLogger.info(message, exception)
            }
            LoggerLevel.WARN -> {
                slf4jLogger.warn(message, exception)
            }
            LoggerLevel.ERROR -> {
                slf4jLogger.error(message, exception)
            }
        }
    }

    override fun traceOnly(function: ILogger.() -> Unit): ILogger {
        if (level.level <= LoggerLevel.TRACE.level) {
            function(this)
        }
        return this
    }

    override fun debugOnly(function: ILogger.() -> Unit): ILogger {
        if (level.level <= LoggerLevel.DEBUG.level) {
            function(this)
        }
        return this
    }

    override val isDebug: Boolean
        get() = level.level <= LoggerLevel.DEBUG.level
}