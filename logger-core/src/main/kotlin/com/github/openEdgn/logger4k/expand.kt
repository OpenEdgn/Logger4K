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

import kotlin.reflect.KClass

fun Any.getLogger(): ILogger {
    return LoggerFactory.getLogger(javaClass)
}

fun KClass<*>.getLogger(): ILogger {
    return LoggerFactory.getLogger(this)
}

fun Class<*>.getLogger(): ILogger {
    return LoggerFactory.getLogger(this)
}

fun Any.withLogTrace(print: Print.() -> Unit) {
    val logger = this.getLogger()
    if (logger.level <= LoggerLevel.TRACE) {
        print(object : Print {
            override fun println(message: String, vararg data: Any?) {
                logger.trace(message, *data)
            }
        })
    }
}

fun Any.withLogDebug(print: Print.() -> Unit) {
    val logger = this.getLogger()
    if (logger.level <= LoggerLevel.DEBUG) {
        print(object : Print {
            override fun println(message: String, vararg data: Any?) {
                logger.debug(message, *data)
            }
        })
    }
}

fun Any.withLogInfo(print: Print.() -> Unit) {
    val logger = this.getLogger()
    if (logger.level <= LoggerLevel.INFO) {
        print(object : Print {
            override fun println(message: String, vararg data: Any?) {
                logger.info(message, *data)
            }
        })
    }
}

fun Any.withLogWarn(print: Print.() -> Unit) {
    val logger = this.getLogger()
    if (logger.level <= LoggerLevel.WARN) {
        print(object : Print {
            override fun println(message: String, vararg data: Any?) {
                logger.warn(message, *data)
            }
        })
    }
}

fun Any.withLogError(print: Print.() -> Unit) {
    val logger = this.getLogger()
    if (logger.level <= LoggerLevel.ERROR) {
        print(object : Print {
            override fun println(message: String, vararg data: Any?) {
                logger.error(message, *data)
            }
        })
    }
}

interface Print {
    fun println(message: String, vararg data: Any?)
}
