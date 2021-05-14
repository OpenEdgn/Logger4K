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
import com.github.openEdgn.logger4k.format.ClassNameFormat
import java.io.PrintStream
import java.text.SimpleDateFormat
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * 内部日志配置
 */
object ConsoleLogConfig {
    @Volatile
    var loggerLevel = LoggerLevel.INFO

    @Volatile
    var dateFormat = SimpleDateFormat("yy/MM/dd HH:mm:ss")

    /**
     * console output
     */
    @Volatile
    var output: PrintStream = System.out

    /**
     * console error output
     */
    @Volatile
    var error: PrintStream = System.err

    internal val threadPool: ExecutorService = Executors.newCachedThreadPool()

    init {
        loggerLevel = try {
            LoggerLevel.valueOf(System.getProperty("logger.level", "INFO")!!.toUpperCase())
        } catch (_: Exception) {
            LoggerLevel.INFO
        }

        Runtime.getRuntime().addShutdownHook(Thread {
            for (runnable in threadPool.shutdownNow()) {
                try {
                    runnable.run()
                } catch (_: Exception) {
                }
            }
            println("程序于 ${dateFormat.format(System.currentTimeMillis())} 退出.")
        })
    }


    internal val classNameFormat: ClassNameFormat = ClassNameFormat.DEFAULT_IMPL

    /**
     * logger level
     */
    internal val loggerLevelInt: Int
        get() = loggerLevel.level


}
