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

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class SimpleLoggerTest {
    @Test
    fun testAll() {
        System.setProperty("logger.level", "DEBUG")
        val slF4JLogger = getSLF4JLogger()
        assertFalse(slF4JLogger.isTraceEnabled)
        slF4JLogger.trace("hello")
        slF4JLogger.trace("{}", "Hello")
        slF4JLogger.trace("{},{}.", "Hello", "world")
        slF4JLogger.trace("error", IndexOutOfBoundsException())
        assertTrue(slF4JLogger.isDebugEnabled)
        slF4JLogger.debug("hello")
        slF4JLogger.debug("{}", "Hello")
        slF4JLogger.debug("{},{}.", "Hello", "world")
        slF4JLogger.debug("error", IndexOutOfBoundsException())
        assertTrue(slF4JLogger.isInfoEnabled)
        slF4JLogger.info("hello")
        slF4JLogger.info("{}", "Hello")
        slF4JLogger.info("{},{}.", "Hello", "world")
        slF4JLogger.info("error", IndexOutOfBoundsException())
        assertTrue(slF4JLogger.isWarnEnabled)
        slF4JLogger.warn("hello")
        slF4JLogger.warn("{}", "Hello")
        slF4JLogger.warn("{},{}.", "Hello", "world")
        slF4JLogger.warn("error", IndexOutOfBoundsException())
        assertTrue(slF4JLogger.isErrorEnabled)
        slF4JLogger.error("hello")
        slF4JLogger.error(null)
        slF4JLogger.error("{}", "Hello")
        slF4JLogger.error("{},{}.", "Hello", "world")
        slF4JLogger.error("{},{}.", "Hello", null)
        slF4JLogger.error("error", IndexOutOfBoundsException())
        slF4JLogger.error("error", null)

    }
}