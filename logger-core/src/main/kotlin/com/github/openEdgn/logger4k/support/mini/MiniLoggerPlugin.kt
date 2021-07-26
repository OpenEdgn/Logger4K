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

package com.github.openEdgn.logger4k.support.mini

import com.github.openEdgn.logger4k.ILogger
import com.github.openEdgn.logger4k.LoggerLevel
import com.github.openEdgn.logger4k.plugin.IPlugin
import java.util.concurrent.SynchronousQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import kotlin.reflect.KClass

/**
 * 默认的日志实现
 */
internal object MiniLoggerPlugin : IPlugin {

    private val threadPool = ThreadPoolExecutor(
        0, Integer.MAX_VALUE,
        10L, TimeUnit.SECONDS,
        SynchronousQueue()
    )

    fun submitTask(func: () -> Unit) {
        threadPool.submit(func)
    }

    override fun getLogger(name: String): ILogger {
        return MiniLogger(name)
    }

    override fun getLoggerLevel(name: String): LoggerLevel {
        return MiniLoggerConfig.packageLogRules.getPackageLevel(name)
    }

    override val name: String = "ProjectLogger"

    override fun getLogger(clazz: KClass<*>): ILogger {
        return getLogger(clazz.java.name)
    }

    override fun shutdown() {
        getLogger("JVM HOOK").info("程序即将退出！")
        threadPool.shutdownNow().forEach {
            try {
                it.run()
            } catch (_: Exception) {
            }
        }
    }
}
