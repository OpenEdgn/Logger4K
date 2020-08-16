package com.github.openEdgn.logger4k

/**
 *  LOGGER LEVEL
 *
 * @property level Int level Int
 * @constructor
 */
enum class LoggerLevel(val level: Int) {
    /**
     * TRACE
     */
    TRACE(0),

    /**
     * DEBUG
     */
    DEBUG(1),

    /**
     * INFO
     */
    INFO(2),

    /**
     * WARN
     */
    WARN(3),

    /**
     * ERROR
     */
    ERROR(4),
}