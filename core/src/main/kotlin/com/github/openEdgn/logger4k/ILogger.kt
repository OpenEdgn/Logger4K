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


/**
 *  # Logger 日志框架核心
 *
 */
interface ILogger {

    /**
     *  # 仅在跟踪模式下执行代码块
     *
     * @return Log logger 实例
     */
    fun traceOnly(function: ILogger.() -> Unit): ILogger

    /**
     *
     * # 输出跟踪日志
     *
     * @param message Any 消息
     * @param param Array<out Any?> 填充信息
     * @return ILogger 当前实例
     */
    fun trace(message: String, vararg param: Any? = arrayOf()): ILogger

    /**
     *  # 仅在调试模式下执行代码块
     *
     * @return Log logger 实例
     */
    fun debugOnly(function: ILogger.() -> Unit): ILogger

    /**
     *
     * # 输出调试日志
     *
     * @param message Any 消息
     * @param param Array<out Any?> 填充信息
     * @return ILogger 当前实例
     */
    fun debug(message: String, vararg param: Any? = arrayOf()): ILogger

    /**
     * # 输出调试日志
     *
     * @param message String 日志信息
     * @param exception Throwable 抛出异常
     * @return Log logger 实例
     */
    fun debug(message: Any, exception: Throwable): ILogger

    /**
     *
     * # 输出标准日志
     *
     * @param message Any 消息
     * @param param Array<out Any?> 填充信息
     * @return ILogger 当前实例
     */
    fun info(message: String, vararg param: Any? = arrayOf()): ILogger

    /**
     *
     * # 输出警告日志
     *
     * @param message Any 消息
     * @param param Array<out Any?> 填充信息
     * @return ILogger 当前实例
     */
    fun warn(message: String, vararg param: Any? = arrayOf()): ILogger

    /**
     * # 输出警告日志
     *
     * @param message String 日志信息
     * @param exception Throwable 抛出异常
     * @return Log logger 实例
     */
    fun warn(message: Any, exception: Throwable): ILogger

    /**
     *
     * # 输出错误日志
     *
     * @param message Any 消息
     * @param param Array<out Any?> 填充信息
     * @return ILogger 当前实例
     */
    fun error(message: String, vararg param: Any? = arrayOf()): ILogger

    /**
     * # 输出错误日志
     *
     * @param message String 日志信息
     * @param exception Throwable 抛出异常
     * @return Log logger 实例
     */
    fun error(message: Any, exception: Throwable): ILogger

    /**
     * # 是否处于调试模式
     */
    val isDebug: Boolean


}