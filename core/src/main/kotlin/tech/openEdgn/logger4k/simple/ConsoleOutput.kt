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

package tech.openEdgn.logger4k.simple

import tech.openEdgn.logger4k.IOutput
import tech.openEdgn.logger4k.LoggerConfig
import tech.openEdgn.logger4k.LoggerItem
import tech.openEdgn.logger4k.LoggerLevel
import java.io.ByteArrayOutputStream
import java.io.OutputStreamWriter
import java.io.PrintWriter
import java.text.SimpleDateFormat

/**
 *  默认的控制台输出
 */
class ConsoleOutput : IOutput {

    override fun writeLine(item: LoggerItem) {
        if ((item.level == LoggerLevel.DEBUG && LoggerConfig.isDebug) || item.level != LoggerLevel.DEBUG) {
            if (item.level.levelInt < LoggerLevel.WARN.levelInt){
                LoggerConfig.consoleOutputStream
            }else{
                LoggerConfig.consoleErrorOutputStream
            }.println(LoggerConfig.itemFormat(item))
        }
    }

    override fun close() {
        LoggerConfig.consoleOutputStream.println("LOGGER CLOSED!")
    }
    companion object{
        private val simpleDateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")

        private fun throwableFormat(throws: Throwable): String {
            val outputStream = ByteArrayOutputStream()
            val printWriter = PrintWriter(OutputStreamWriter(outputStream, Charsets.UTF_8), true)
            throws.printStackTrace(printWriter)
            printWriter.flush()
            val array = outputStream.toByteArray()
            printWriter.close()
            outputStream.close()
            return array.toString(Charsets.UTF_8).trim()
        }
        val LOG_TO_LINE :(LoggerItem)->String = {
            val output = StringBuilder()
            if (LoggerConfig.isDebug){
                output.append("[DEBUG MODE] ")
            }
            output.append(simpleDateFormat.format(System.currentTimeMillis()))
                    .append(" - ")
                    .append(String.format("%-5s", it.level.name))
                    .append(" - #(${it.threadName})")
                    .append(" - ")
                    .append(it.clazz.simpleName)
                    .append(":")
                    .append(it.message.toString().replace(Regex("\\p{C}"), "#"))
            it.exception?.run {
                output.append("\r\n")
                        .append(throwableFormat(this))
            }
            output.toString()
        }
    }


}  