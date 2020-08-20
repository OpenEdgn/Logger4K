object Versions {
    object Project {
        const val core = "1.0"
    }

    object Kotlin {
        //1.3.72
        const val gradlePlugin = "1.3.72"
    }

    object Junit {
        //5.6.2
        const val junitJupiter = "5.6.2"
    }
}

/**
 * INCLUDE OpenEDGN Project
 */
fun openEDGN(name: String, version: String? = null): String = "com.github.OpenEdgn:$name${if (version == null) "" else ":$version"}"
