package com.github.openEdgn.logger4k.support

import com.github.openEdgn.logger4k.plugin.IPlugin

/**
 * 日志实现模块扫描器
 */
interface IChildPluginLoader {
    /**
     * 日志实现加载器是否找到可用实现
     */
    val support: Boolean

    /**
     * 得到此日志实现
     */
    fun getPlugin(): IPlugin
}
