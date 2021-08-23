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

package com.github.openEdgn.logger4k.utils.format.message

import java.util.LinkedList

/**
 * 消息格式化类
 *
 * 将符合格式的字符串类自动格式化成对应类型 ，例如：
 *
 * ```text
 *  Hello World , {}.
 * ```
 *
 * 传入 `dragon`, 则输出 `Hello World , dragon.`,对于想原样输出大括号时，则可以用 `\\{}` 替代 .
 *
 */
object MessageFormatImpl : IMessageFormat {

    override fun format(message: String, data: Array<out Any?>): String {
        if (data.isEmpty()) {
            return message
        }
        var lastIndex = 0
        val list = LinkedList<Int>()
        while (true) {
            val index = message.indexOf("{}", lastIndex)
            if (index == -1) {
                break
            }
            if (index != 0) {
                if (message[index - 1] != '\\') {
                    list.add(index)
                    lastIndex = index + 2
                    list.add(lastIndex)
                } else {
                    lastIndex = index + 3
                }
            } else {
                list.add(index)
                lastIndex = index + 2
                list.add(lastIndex)
            }
        }
        if (list.size == 0) {
            return message
        }
        list.addFirst(0)
        list.addLast(message.length)
        val res = ArrayList<String>(list.size + data.size)
        for (i in list.windowed(2, 2)) {
            res.add(message.substring(i[0], i[1]))
        }
        val result = StringBuilder()
        for ((i, v) in res.withIndex()) {
            result.append(v)
            val last = res.size - 1
            if (i == last) {
                break
            }
            if (i < data.size) {
                val any = data[i]
                result.append(any.toString())
            } else if (i < last) {
                result.append("{}")
            }
        }
        return result.toString().replace(regex = Regex("(\\\\\\{}|\\\\\\{\\\\})"), replacement = "{}")
    }
}
