package com.github.openEdgn.logger4k.api

/**
 * 日志实现接口
 */
interface ILogApi {

    /**
     * 实现的名称
     */
    val name: String

    /**
     * 初始化
     */
    fun init(): ILogOutputApi
}
