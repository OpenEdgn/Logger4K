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

package logger4k.impl.console

import com.github.openEdgn.logger4k.LoggerLevel
import java.io.PrintStream
import java.text.SimpleDateFormat

/**
 * 内部日志配置
 */
object ConsoleConfig {
    private val dateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
    fun now(): String = dateFormat.format(System.currentTimeMillis())

    /**
     * logger level
     */
    val loggerLevel: Int by lazy {
        try {
            LoggerLevel.valueOf(System.getProperty("logger.level", "INFO")!!.toUpperCase()).level
        } catch (_: Exception) {
            LoggerLevel.INFO.level
        }
    }


    /**
     * console output
     */
    val output: PrintStream = System.out

    /**
     * console error output
     */
    val error: PrintStream = System.err
}