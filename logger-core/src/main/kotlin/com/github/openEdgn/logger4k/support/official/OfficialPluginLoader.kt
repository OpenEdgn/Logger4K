package com.github.openEdgn.logger4k.support.official

import com.github.openEdgn.logger4k.plugin.IPlugin
import com.github.openEdgn.logger4k.support.IChildPluginLoader
import java.util.Optional
import java.util.Properties
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.primaryConstructor

class OfficialPluginLoader : IChildPluginLoader {
    private var implClass: Optional<KClass<IPlugin>> = Optional.empty()

    init {
        val properties = Properties()
        val classLoader = OfficialPluginLoader::class.java
        try {
            properties.load(classLoader.getResourceAsStream("/META-INF/logger4k.properties"))
        } catch (e: Exception) {
        }
        val property = properties.getProperty(PLUGIN_IMPL_CLASS_KEY)
        if (property != null) {
            try {
                val pluginImplClass = (Class.forName(property) ?: throw NullPointerException("未找到对应的Plugin 实现类")).kotlin
                if (pluginImplClass.isSubclassOf(IPlugin::class)) {
                    @Suppress("UNCHECKED_CAST")
                    implClass = Optional.of(pluginImplClass as KClass<IPlugin>)
                }
            } catch (e: Exception) {
            }
        }
    }

    override val support: Boolean
        get() = implClass.isPresent

    override fun getPlugin(): IPlugin {
        return implClass.get().objectInstance ?: implClass.get().primaryConstructor!!.call()
    }

    companion object {
        private const val PLUGIN_IMPL_CLASS_KEY = "logger4k.plugin.implClass"
    }
}
