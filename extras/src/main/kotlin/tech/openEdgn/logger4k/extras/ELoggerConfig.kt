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

package tech.openEdgn.logger4k.extras

import tech.openEdgn.logger4k.LoggerConfig
import tech.openEdgn.logger4k.extras.utils.FileUtils
import tech.openEdgn.logger4k.getLogger
import java.io.File
import java.io.IOException

object ELoggerConfig {
    private val logger = getLogger()
    private val loggerOutput = LoggerFileOutput()

    /**
     * 日志输出目录
     */
    var logOutputDirectory: File
        get() = loggerFolder
        set(value) {
            if (FileUtils.checkDirectory(value).not()) {
                throw IOException("目录${value.absolutePath} 无法写入日志文件.")
            }
            loggerFolder = value
        }

    @Volatile
    private var loggerFolder: File = File(System.getProperty("java.io.tmpdir"), "LOGGER4K")

    /**
     * 导入Logger 扩展选项
     * 将接管原始Logger 配置
     * @receiver Any
     */
    fun enableExtra() {
        logger.debug("logger 扩展已启用.")
        LoggerConfig.output = loggerOutput
    }


}