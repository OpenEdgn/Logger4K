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

package com.github.openEdgn.logger4k

import com.github.openEdgn.logger4k.utils.SystemEnvProperties
import java.io.File
import java.util.Properties

/**
 * 内部日志配置工具,通过定义环境变量来更改日志输出样式
 */
internal object Logger4KConfig {
    private val internalSoftEnv = SystemEnvProperties("logger4k.internal.")
    private val softEnv = SystemEnvProperties("logger4k.core.")

    /**
     * 默认内部调试日志等级
     * 定义变量 `logger4k.internal.level` 为 `LoggerLevel` 枚举
     */
    val level: LoggerLevel = internalSoftEnv.getEnumOrDefault("level", LoggerLevel.OFF)

    val extraConfig: Map<String, String> =
        softEnv.getCustomOrDefault("properties", mapOf()) {
            val prop = Properties()
            val split = it.split(",").toMutableList()
            split.add("classpath:///logger4k.properties")
            split.forEach { item ->
                try {
                    val classpath = "classpath://"
                    val file = "file://"
                    if (item.startsWith(classpath)) {
                        prop.load(
                            Logger4KConfig::class.java
                                .getResourceAsStream(item.substring(classpath.length))!!
                                .bufferedReader(Charsets.UTF_8)
                        )
                    } else if (item.startsWith(file)) {
                        val path = File(item.substring(file.length))
                        if (path.isFile && path.canRead() && path.length() < 1024 * 1024 * 512) {
                            path.inputStream().use { ins ->
                                prop.load(
                                    ins
                                        .bufferedReader(Charsets.UTF_8)
                                )
                            }
                        }
                    }
                } catch (e: Exception) {
                }
            }
            prop.map { m -> Pair(m.key.toString(), m.value.toString()) }.toMap()
        }
}
