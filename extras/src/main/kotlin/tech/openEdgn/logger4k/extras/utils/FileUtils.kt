package tech.openEdgn.logger4k.extras.utils

import tech.openEdgn.logger4k.getLogger
import java.io.File
import java.io.IOException

object FileUtils {
    private val logger =getLogger()
    /**
     * 测试此目录是否具有读写权限,不存在则会创建
     * @param directory File 目录
     * @return Boolean 如果目录只读或存在文件则返回False
     */
    fun checkDirectory(directory: File): Boolean {
        if (directory.exists()){
            directory.mkdirs()
        }
        if (directory.isFile){
            logger.debug("directory [${directory.absolutePath}] exists.")
            return false
        }
        if (directory.canWrite().not()){
            logger.debug("directory [${directory.absolutePath}] Read-Only.")
            return false
        }
        return true
    }
}