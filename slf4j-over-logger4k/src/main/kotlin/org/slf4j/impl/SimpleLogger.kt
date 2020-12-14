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

package org.slf4j.impl

import com.github.openEdgn.logger4k.LoggerFactory
import com.github.openEdgn.logger4k.LoggerLevel
import org.slf4j.helpers.MarkerIgnoringBase

class SimpleLogger(private val internalName: String) : MarkerIgnoringBase() {
    private val nullMessage = "如果您看到此错误则表明日志接收到的的异常信息为 null."

    init {
        name = internalName
    }

    private val logger = LoggerFactory.getLogger(name)
    override fun getName(): String {
        return this.internalName
    }

    override fun isTraceEnabled() = logger.level.level <= LoggerLevel.TRACE.level

    override fun trace(msg: String?) {
        logger.trace("{}", msg)
    }

    override fun trace(format: String?, arg: Any?) {
        logger.trace(format ?: "{}", arg)
    }

    override fun trace(format: String?, arg1: Any?, arg2: Any?) {
        logger.trace(format ?: "var1:{}, var2: {}.", arg1, arg2)
    }

    override fun trace(format: String?, vararg arguments: Any?) {
        logger.trace(format ?: "{}", *arguments)
    }

    override fun trace(msg: String?, t: Throwable?) {
        logger.traceThrowable(msg ?: "", t ?: NullPointerException(nullMessage))
    }


    override fun isDebugEnabled() = logger.level.level <= LoggerLevel.DEBUG.level

    override fun debug(msg: String?) {
        logger.debug("{}", msg)
    }

    override fun debug(format: String?, arg: Any?) {
        logger.debug(format ?: "{}", arg)
    }

    override fun debug(format: String?, arg1: Any?, arg2: Any?) {
        logger.debug(format ?: "var1:{}, var2: {}.", arg1, arg2)
    }

    override fun debug(format: String?, vararg arguments: Any?) {
        logger.debug(format ?: "{}", *arguments)
    }

    override fun debug(msg: String?, t: Throwable?) {
        logger.debugThrowable(msg ?: "", t ?: NullPointerException(nullMessage))
    }


    override fun isInfoEnabled() = logger.level.level <= LoggerLevel.INFO.level

    override fun info(msg: String?) {
        logger.info("{}", msg)
    }

    override fun info(format: String?, arg: Any?) {
        logger.info(format ?: "{}", arg)
    }

    override fun info(format: String?, arg1: Any?, arg2: Any?) {
        logger.info(format ?: "var1:{}, var2: {}.", arg1, arg2)
    }

    override fun info(format: String?, vararg arguments: Any?) {
        logger.info(format ?: "{}", *arguments)
    }

    override fun info(msg: String?, t: Throwable?) {
        logger.infoThrowable(msg ?: "", t ?: NullPointerException(nullMessage))
    }


    override fun isWarnEnabled() = logger.level.level <= LoggerLevel.WARN.level

    override fun warn(msg: String?) {
        logger.warn("{}", msg)
    }

    override fun warn(format: String?, arg: Any?) {
        logger.warn(format ?: "{}", arg)
    }

    override fun warn(format: String?, arg1: Any?, arg2: Any?) {
        logger.warn(format ?: "var1:{}, var2: {}.", arg1, arg2)
    }

    override fun warn(format: String?, vararg arguments: Any?) {
        logger.warn(format ?: "{}", *arguments)
    }

    override fun warn(msg: String?, t: Throwable?) {
        logger.warnThrowable(msg ?: "", t ?: NullPointerException(nullMessage))
    }


    override fun isErrorEnabled() = logger.level.level <= LoggerLevel.ERROR.level

    override fun error(msg: String?) {
        logger.error("{}", msg)
    }

    override fun error(format: String?, arg: Any?) {
        logger.error(format ?: "{}", arg)
    }

    override fun error(format: String?, arg1: Any?, arg2: Any?) {
        logger.error(format ?: "var1:{}, var2: {}.", arg1, arg2)
    }

    override fun error(format: String?, vararg arguments: Any?) {
        logger.error(format ?: "{}", *arguments)
    }

    override fun error(msg: String?, t: Throwable?) {
        logger.errorThrowable(msg ?: "", t ?: NullPointerException(nullMessage))
    }


}