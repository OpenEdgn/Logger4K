/**
 * 程序引入的版本
 */
object Versions {
    object Project {
        /**
         * 工程版本
         */
        const val core = "1.0"
    }

    // Kotlin 版本
    object Kotlin {
        private const val version = "1.4.0"
        const val gradlePlugin = version
        const val reflect = version
        const val stdlib = version
        const val jvmPlugin = version
    }

    object Junit {
        // JUnit 测试版本
        const val junitJupiter = "5.6.2"
    }
}

/**
 * INCLUDE OpenEDGN Project
 */
fun openEDGN(name: String, version: String? = null): String = "com.github.OpenEdgn:$name${if (version == null) "" else ":$version"}"
