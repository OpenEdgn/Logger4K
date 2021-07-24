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

package com.github.openEdgn.logger4k.plugin

import com.github.openEdgn.logger4k.support.mini.MiniPluginLoader
import com.github.openEdgn.logger4k.support.official.OfficialPluginLoader
import java.io.Closeable
import java.util.Optional

/**
 * 日志实现加载器
 */
object PluginManager : Closeable {
    private val rules = arrayListOf(
        OfficialPluginLoader()
    )

    private val loggerPlugin: IPlugin

    init {
        var p: Optional<IPlugin> = Optional.empty()
        for (rule in rules) {
            if (rule.support) {
                rule.getPlugin()
                p = Optional.of(wrapper(rule.getPlugin()))
                break
            }
        }
        loggerPlugin = if (p.isEmpty) {
            wrapper(MiniPluginLoader().getPlugin())
        } else {
            p.get()
        }
        Runtime.getRuntime().addShutdownHook(
            Thread {
                this.close()
            }
        )
    }

    private fun wrapper(plugin: IPlugin): IPlugin {
        return if (plugin.ignoreOptimization) {
            plugin
        } else {
            CachePluginWrapper(plugin)
        }
    }

    internal fun implPlugin(): IPlugin {
        return loggerPlugin
    }

    override fun close() {
        loggerPlugin.shutdown()
    }
}
