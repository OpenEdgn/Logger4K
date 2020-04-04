package tech.openEdgn.logger4k

import java.io.ByteArrayOutputStream
import java.io.OutputStreamWriter
import java.io.PrintWriter
import java.text.SimpleDateFormat

class SysOutput : IOutput {
    private val simpleDateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")

    override fun writeLine(item: LoggerItem) {
        val output = StringBuilder()
        if (LoggerConfig.isDebug){
            output.append("[DEBUG MODE]")
            output.append(" - ")
        }
        output.append(simpleDateFormat.format(System.currentTimeMillis()))
                .append(" - ")
                .append(String.format("%-5s", item.level.name))
                .append(" - ")
                .append(item.clazz.simpleName)
                .append(":")
                .append(item.message.replace(Regex("\\p{C}"), "#"))
        if (item.exception != null) {
            output.append("\r\n")
                    .append(throwableFormat(item.exception))
        }
        if ((item.level == LoggerLevel.DEBUG && LoggerConfig.isDebug) || item.level != LoggerLevel.DEBUG) {
            if (item.level.levelInt < LoggerLevel.WARN.levelInt){
                LoggerConfig.commandOutput.println(output)
            }else{
                LoggerConfig.commandErrOutput.println(output)
            }
        }
    }

    override fun close() {
        LoggerConfig.commandOutput.println("LOGGER CLOSED!")
    }
    private fun throwableFormat(throws: Throwable): String {
        val outputStream = ByteArrayOutputStream()
        val printWriter = PrintWriter(OutputStreamWriter(outputStream, Charsets.UTF_8), true)
        throws.printStackTrace(printWriter)
        printWriter.flush()
        val array = outputStream.toByteArray()
        printWriter.close()
        outputStream.close()
        return array.toString(Charsets.UTF_8).trim()
    }
}  