package tech.openEdgn.logger4k

import java.io.PrintStream


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
    var output: IOutput = SimpleOutput()

    private val shutdownHook = Thread {
        output.close()
    }

    /**
     *   自定义单个日志转化成字符串方法
     */
    @Volatile
    var logger2Line:(LoggerItem)->String = SimpleOutput.LOG_TO_LINE

    init {
        Runtime.getRuntime().run {
            addShutdownHook(shutdownHook)
        }
    }

}