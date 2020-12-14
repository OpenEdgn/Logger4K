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

import com.github.openEdgn.logger4k.ILogger
import com.github.openEdgn.logger4k.LoggerLevel
import com.github.openEdgn.logger4k.format.ClassNameFormat
import com.github.openEdgn.logger4k.plugin.IPlugin
import com.github.openEdgn.logger4k.plugin.PluginManager
import org.slf4j.LoggerFactory
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass

object SLF4JPlugin : IPlugin {
    private val map = ConcurrentHashMap<String, ILogger>(100)
    override fun getLogger(name: String): ILogger {
        return map[name] ?: kotlin.run {
            val consoleLogger = SLF4JLogger(ClassNameFormat.DEFAULT_IMPL.format(name)) as ILogger
            map[name] = consoleLogger
            consoleLogger
        }
    }

    override fun getLoggerLevel(name: String): LoggerLevel {
        val logger = LoggerFactory.getLogger(name)
        if (logger.isTraceEnabled) {
            return LoggerLevel.TRACE
        }
        if (logger.isDebugEnabled) {
            return LoggerLevel.DEBUG
        }
        if (logger.isWarnEnabled) {
            return LoggerLevel.WARN
        }
        if (logger.isErrorEnabled) {
            return LoggerLevel.ERROR
        }
        return LoggerLevel.ERROR

    }

    init {
        PluginManager.registerPlugin(SLF4JPlugin::class)
    }

    override val name: String = "SLF4JLogger"

    override fun getLogger(kClass: KClass<*>): ILogger {
        return getLogger(ClassNameFormat.DEFAULT_IMPL.format(kClass))
    }
}