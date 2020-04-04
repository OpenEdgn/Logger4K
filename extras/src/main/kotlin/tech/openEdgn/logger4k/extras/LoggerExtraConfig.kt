package tech.openEdgn.logger4k.extras

import tech.openEdgn.logger4k.extras.utils.FileUtils
import tech.openEdgn.logger4k.getLogger
import java.io.File
import java.io.IOException

object LoggerExtraConfig {
    private val logger = getLogger()

    /**
     * 日志输出目录
     */
    var logOutputDirectory:File
    get() = loggerFolder
    set(value) {
        if (FileUtils.checkDirectory(value).not()) {
            throw IOException("目录${value.absolutePath} 无法写入日志文件.")
        }
        loggerFolder = value
    }
    @Volatile
    private var loggerFolder :File = File(System.getProperty("java.io.tmpdir"),"LOGGER4K")

    /**
     * 导入Logger 扩展选项
     * 将接管原始Logger 配置
     * @receiver Any
     */
    fun enableExtra() {
        logger.debug("logger 扩展已启用.")
    }

}