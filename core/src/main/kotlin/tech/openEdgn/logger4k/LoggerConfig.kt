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

import java.io.PrintStream

object LoggerConfig {
    private val logger = getLogger()
    /**
     * 标准控制台输出
     */
    @Volatile
    var commandOutput: PrintStream = System.out

    /**
     * 标准控制台错误输出
     */
    @Volatile
    var commandErrOutput: PrintStream = System.err

    val isDebug
        get() = debugMode

    @Volatile
    private var debugMode: Boolean = false

    /**
     * 开启 DEBUG 模式
     * @return LoggerConfig 当前实例
     */
    fun enableDebug() = run {
        debugMode = true
        logger.warn("注意!DEBUG 模式已打开!")
        this
    }


    /**
     * 关闭 DEBUG 模式
     * @return LoggerConfig 当前实例
     */
    fun disableDebug() = run {
        debugMode = false
        logger.info("DEBUG 模式已关闭!")
        this
    }

    /**
     * 日志输出类
     */
    @Volatile
    var output: IOutput = ConsoleOutput()

    private val shutdownHook = Thread {
        output.close()
    }

    /**
     *   自定义单个日志转化成字符串方法
     */
    @Volatile
    var lineFormat: (LoggerItem) -> String = ConsoleOutput.LOG_TO_LINE

    /**
     *  注册
     * @param extra IExtra
     */
    @Synchronized
    fun registerExtra(extra: IExtra){

    }

    init {
        Runtime.getRuntime().run {
            addShutdownHook(shutdownHook)
        }
    }


}