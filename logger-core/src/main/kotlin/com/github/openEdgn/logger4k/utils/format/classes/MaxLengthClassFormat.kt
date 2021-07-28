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

package com.github.openEdgn.logger4k.utils.format.classes

import com.github.openEdgn.logger4k.utils.format.formatLength

/**
 * 类名称格式化工具
 * 如果 maxLine 为 -1 则表示不格式化
 * @property maxLine Int
 */
class MaxLengthClassFormat(private val maxLine: Int = 30) : ClassNameFormat() {

    override fun format(name: String): String {
        if (maxLine == -1) {
            return name
        }
        return if (name.length > maxLine) {
            val list = name.split(".")
            val stringBuilder = StringBuilder()
            for (i in 0 until list.size - 1) {
                stringBuilder.append(list[i].first()).append(".")
            }
            if (maxLine - stringBuilder.length < list.last().length) {
                val replace = list.last().replace(Regex("[a-z]"), "")
                if (replace.isBlank()) {
                    stringBuilder.append("${replace.first()}<->${replace.last()}")
                } else {
                    stringBuilder.append(replace)
                }
            } else {
                stringBuilder.append(list.last())
            }
            stringBuilder.toString().formatLength(maxLine)
        } else {
            name.formatLength(maxLine)
        }
    }
}
