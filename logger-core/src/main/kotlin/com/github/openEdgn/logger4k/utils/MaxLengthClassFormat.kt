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

package com.github.openEdgn.logger4k.utils

/**
 * 类名称格式化工具
 * 如果 maxLine 为 -1 则表示不格式化,
 *  注意，如果极度精简后还是超出长度时将原样输出
 */
class MaxLengthClassFormat(private val maxLength: Int = 40) : IClassNameFormat {

    override fun format(name: String): String {
        if (maxLength == -1) {
            return name
        }
        return if (name.length > maxLength) {
            var length = name.length
            var accept = false
            val builder = StringBuilder()
            val split = name.split(".")
            if (split.size == 1) {
                return formatLength(name, maxLength)
            }
            for (item in 0 until split.size - 1) {
                if (accept) {
                    builder.append(split[item]).append(".")
                } else {
                    if ((length + 2 - split[item].length) <= maxLength) {
                        accept = true
                    }
                    builder.append(split[item].first()).append(".")
                    length -= split[item].length - 2
                }
            }

            val lastName = split.last()
            if (lastName.length > maxLength - builder.length) {
                builder.append(lastName)
                builder.toString()
            } else {
                builder.append(lastName)
                formatLength(builder.toString(), maxLength)
            }
        } else {
            formatLength(name, maxLength)
        }
    }

    private fun formatLength(data: String, len: Int): String {
        if (len <= 0) {
            return ""
        }
        val item = data.substring(
            0,
            if (data.length > len) {
                len
            } else {
                data.length
            }
        )
        return String.format("%${-len}s", item)
    }
}
