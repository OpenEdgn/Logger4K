package com.github.openEdgn.logger4k.utils

import java.util.Optional
import java.util.ServiceLoader
import kotlin.reflect.KClass

object SpiSearch {
    fun <T : Any> search(clazz: KClass<T>, default: T? = null): Optional<T> {
        val loader = ServiceLoader.load(clazz.java)
        return loader.findFirst().or { Optional.ofNullable(default) }
    }
}
