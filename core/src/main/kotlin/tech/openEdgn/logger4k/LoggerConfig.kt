package tech.openEdgn.logger4k

import java.io.File
import java.io.FileWriter
import java.io.PrintStream
import java.io.PrintWriter
import java.lang.Exception
import java.text.SimpleDateFormat
import kotlin.reflect.KClass


object LoggerConfig {
    private val logger = getLogger()

    /**
     * 标准控制台输出
     */
    @Volatile
    var commandOutput: PrintStream = System.out

    /**
     * 标准控制台错误输出
     */
    @Volatile
    var commandErrOutput: PrintStream = System.err

    val isDebug
        get() = debugMode

    @Volatile
    private var debugMode: Boolean = false

    /**
     * 开启 DEBUG 模式
     * @return LoggerConfig 当前实例
     */
    fun enableDebug() = run {
        debugMode = true
        logger.debug("注意!DEBUG 模式已打开!")
        this
    }

    /**
     * 关闭 DEBUG 模式
     * @return LoggerConfig 当前实例
     */
    fun disableDebug() = run {
        debugMode = false
        this
    }

    /**
     * 日志输出类
     */
    @Volatile
    var output: IOutput = SysOutput()

    private val shutdownHook = Thread {
        output.close()
    }

    init {
        Runtime.getRuntime().run {
            addShutdownHook(shutdownHook)
        }
    }

}