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

import com.github.openEdgn.logger4k.LoggerFactory
import org.junit.jupiter.api.Test
import java.io.IOException

internal class SLF4JLoggerTest {

    private val logger = LoggerFactory.getLogger(SLF4JLoggerTest::class)

    @Test
    fun testInfo() {
        logger.info("Hello World.")
        logger.info("Hello World, {} .", "dragon")
        logger.infoThrowable("Exception", IOException())
    }

    @Test
    fun testDebug() {
        logger.debug("Hello World.")
        logger.debug("Hello World, {} .", "dragon")
        logger.debugThrowable("Exception", IOException())
    }

    @Test
    fun testTrace() {
        logger.trace("Hello World.")
        logger.trace("Hello World, {} .", "dragon")
        logger.traceThrowable("Exception", IOException())
    }

    @Test
    fun testWarn() {
        logger.warn("Hello World.")
        logger.warn("Hello World, {} .", "dragon")
        logger.warnThrowable("Exception", IOException())
    }

    @Test
    fun testError() {
        logger.error("Hello World.")
        logger.error("Hello World, {} .", "dragon")
        logger.errorThrowable("Exception", IOException())
    }

}
