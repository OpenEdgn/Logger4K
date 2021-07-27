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
import com.github.openEdgn.logger4k.plugin.PluginManager
import java.text.SimpleDateFormat
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.reflect.KClass

/**
 * 默认的日志实现
 */
internal class MiniLoggerPlugin : IPlugin {
    val loggerConfig = MiniLoggerConfig()
    private val threadPool = Executors.newCachedThreadPool()
    fun submitTask(level: LoggerLevel, func: () -> Unit) {
        if (loggerConfig.asyncLoggerOutput) {
            if (level == LoggerLevel.TRACE ||
                level == LoggerLevel.ERROR ||
                level == LoggerLevel.DEBUG
            ) {
                func()
            } else {
                threadPool.execute(func)
            }
        } else {
            func()
        }
    }

    override fun getLogger(name: String): ILogger {
        return MiniLogger(name, this)
    }

    override fun getLoggerLevel(name: String): LoggerLevel {
        return loggerConfig.packageLogRules.getPackageLevel(name)
    }

    override val name: String = "ProjectLogger"

    override fun getLogger(clazz: KClass<*>): ILogger {
        return getLogger(clazz.java.name)
    }

    override fun shutdown() {
        if (PluginManager.implPlugin().name == name) {
            val dateTimeFormat = SimpleDateFormat("YY/MM/dd HH:mm:ss")
            loggerConfig.getPrintStream(LoggerLevel.INFO)
                .println("程序于 ${dateTimeFormat.format(System.currentTimeMillis())} 退出！")
        }
        threadPool.shutdown()
        if (!threadPool.awaitTermination(5, TimeUnit.SECONDS)) {
            threadPool.shutdownNow().forEach {
                it.run()
            }
        }
    }
}
