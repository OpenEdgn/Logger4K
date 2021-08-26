package logger4k.console

import com.github.openEdgn.logger4k.LoggerLevel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PackageLevelTest {

    @Test
    fun test() {
        val packageLevel = PackageLevel(LoggerLevel.INFO)
        assertEquals(LoggerLevel.INFO, packageLevel.getPackageLevel("com.example"))
        packageLevel.putPackageLevel("com.example.dragon", LoggerLevel.ERROR)
        assertEquals(LoggerLevel.INFO, packageLevel.getPackageLevel("com.example"))
        assertEquals(LoggerLevel.ERROR, packageLevel.getPackageLevel("com.example.dragon"))
        packageLevel.putPackageLevel("com", LoggerLevel.OFF)
        assertEquals(LoggerLevel.OFF, packageLevel.getPackageLevel("com.example"))
    }
}
