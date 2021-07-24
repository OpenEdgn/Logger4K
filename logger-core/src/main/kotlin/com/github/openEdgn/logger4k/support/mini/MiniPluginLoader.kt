package com.github.openEdgn.logger4k.support.mini

import com.github.openEdgn.logger4k.plugin.IPlugin
import com.github.openEdgn.logger4k.support.IChildPluginLoader

class MiniPluginLoader : IChildPluginLoader {
    private val impl by lazy { MiniLoggerPlugin }
    override val support: Boolean
        get() = true

    override fun getPlugin(): IPlugin {
        return impl
    }
}
