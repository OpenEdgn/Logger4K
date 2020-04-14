package tech.openEdgn.logger4k.extras.utils

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File


class FileUtilsTest {

    @Tag("io")
    @Test
    fun checkDirectory(@TempDir tempDir: File) {
        val file = File(tempDir, "FILE")
        val dir = File(tempDir, "DIR")
        file.createNewFile()
        dir.mkdir()
        assertEquals(FileUtils.checkDirectory(file),false)
        assertEquals(FileUtils.checkDirectory(dir),true)

    }
}