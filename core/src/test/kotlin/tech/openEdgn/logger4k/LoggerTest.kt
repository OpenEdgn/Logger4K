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

package tech.openEdgn.logger4k

import jdk.nashorn.internal.ir.annotations.Ignore
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

/**
 * 关于`core`模块的**所有**方法均在此 `TEST` 下
 */
internal class LoggerTest {
    @Ignore
    @Test
    fun testAll() {
        println("将输出日志示例！")
        val logger = getLogger()
        //创建一个Logger 对象
        LoggerConfig.enableDebug()
        //开启DEBUG 模式 (1)
        enableDebug()
        //开启DEBUG 模式 (2)
        disableDebug()
        // 关闭DEBUG 模式 (1)
        LoggerConfig.enableDebug()
        // 关闭DEBUG 模式 (2)

        logger.debug("DEBUG Message.")
        //  输出 DEBUG 类型的日志 ( 注意，DEBUG未开启则不会输出日志)

        logger.info("INFO  Message.")
        //  输出 INFO 类型的日志

        logger.warn("WARN  Message.")
        //  输出 WARN 类型的日志

        logger.error("ERROR  Message.")
        //  输出 ERROR 类型的日志

        logger.debugOnly {
            //此代码块仅在调试模式下运行
            info("INFO")
            warn("WARN")
            debug("DEBUG")
            error("ERROR")
        }
    }
}