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

package com.github.openEdgn.logger4k.extras

import jdk.nashorn.internal.ir.annotations.Ignore
import org.junit.jupiter.api.Test
import com.github.openEdgn.logger4k.LoggerConfig
import com.github.openEdgn.logger4k.enableDebug
import com.github.openEdgn.logger4k.getLogger
import java.io.File
internal class ELoggerConfigTest {
    @Ignore
    @Test
    fun testAll() {
        ELoggerConfig.logOutputDirectory = File(System.getProperty("java.io.tmpdir"), "LOGGER4K2")
        ELoggerConfig.enableExtra()
        //  注册扩展模块（ 方法 1）
        LoggerConfig.registerExtra(ELoggerConfig.FileExtra::class)
        //  注册扩展模块（方法 2）
        enableLoggerExtra()
        //  注册扩展模块（方法 3）

        val logger = getLogger()
        logger.info("Hello World!")
        //  变更日志位置
        logger.info("Hello World!")
        enableDebug()
    }
}

