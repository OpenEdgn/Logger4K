package tech.openEdgn.logger4k

import java.io.File
import java.io.FileWriter
import java.io.PrintStream
import java.io.PrintWriter
import java.lang.Exception
import java.text.SimpleDateFormat
import kotlin.reflect.KClass


object LoggerConfig {

    /**
     * 标准控制台输出
     */
    @Volatile
    var commandOutput: PrintStream? = System.out

    /**
     * 标准控制台错误输出
     */
    @Volatile
    var commandErrOutput: PrintStream? = System.err


    @Volatile
    var isDebug = false

    /**
     * 开启 DEBUG 模式
     * @return LoggerConfig 当前实例
     */
    fun enableDebug() = run {
        isDebug = true
        this
    }
    /**
     * 关闭 DEBUG 模式
     * @return LoggerConfig 当前实例
     */
    fun disableDebug() = run {
        isDebug = false
        this
    }

    /**
     * 日志输出类
     */
    @Volatile
    var output:IOutput = TODO()

}