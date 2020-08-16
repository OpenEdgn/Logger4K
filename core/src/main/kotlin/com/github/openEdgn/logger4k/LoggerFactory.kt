package com.github.openEdgn.logger4k

import kotlin.reflect.KClass

object LoggerFactory {

    fun getLogger(clazz: Class<*>): ILogger {
        return getLogger(clazz.kotlin)
    }


    fun getLogger(clazz: KClass<*>): ILogger {

    }

}