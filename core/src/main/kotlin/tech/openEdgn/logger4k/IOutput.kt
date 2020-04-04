package tech.openEdgn.logger4k

import java.io.Closeable

interface IOutput:Closeable {
    fun writeLine(item:LoggerItem)
}