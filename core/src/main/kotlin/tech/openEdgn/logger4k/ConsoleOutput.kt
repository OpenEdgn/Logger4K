package tech.openEdgn.logger4k

import java.io.ByteArrayOutputStream
import java.io.OutputStreamWriter
import java.io.PrintWriter
import java.text.SimpleDateFormat

class ConsoleOutput : IOutput {

    override fun writeLine(item: LoggerItem) {
        if ((item.level == LoggerLevel.DEBUG && LoggerConfig.isDebug) || item.level != LoggerLevel.DEBUG) {
            if (item.level.levelInt < LoggerLevel.WARN.levelInt){
                LoggerConfig.commandOutput
            }else{
                LoggerConfig.commandErrOutput
            }.println(LoggerConfig.lineFormat(item))
        }
    }

    override fun close() {
        LoggerConfig.commandOutput.println("LOGGER CLOSED!")
    }
    companion object{
        private val simpleDateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")

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
        val LOG_TO_LINE :(LoggerItem)->String = {
            val output = StringBuilder()
            if (LoggerConfig.isDebug){
                output.append("[DEBUG MODE] ")
            }
            output.append(simpleDateFormat.format(System.currentTimeMillis()))
                    .append(" - ")
                    .append(String.format("%-5s", it.level.name))

                    .append("- #(${it.threadName})")
                    .append(" - ")
                    .append(it.clazz.simpleName)
                    .append(":")
                    .append(it.message.toString().replace(Regex("\\p{C}"), "#"))
            if (it.exception != null) {
                output.append("\r\n")
                        .append(throwableFormat(it.exception))
            }
            output.toString()
        }
    }


}  