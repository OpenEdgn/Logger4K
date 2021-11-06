package com.github.openEdgn.logger4k.utils

import java.util.Optional
import java.util.ServiceLoader
import kotlin.reflect.KClass

object SpiSearch {
    fun <T : Any> search(clazz: KClass<T>, default: T? = null): Optional<T> {
        return try {
            ServiceLoader.load(clazz.java).findFirst().or { Optional.ofNullable(default) }
        } catch (e: Exception) {
            Optional.ofNullable(default)
        }
    }
}
