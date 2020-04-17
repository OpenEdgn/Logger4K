
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    kotlin("jvm")
}
java.sourceCompatibility = JavaVersion.VERSION_1_8


dependencies {
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

// config JVM target to 1.8 for kotlin compilation tasks
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.register("sayHelloWorld"){
    dependsOn("build")
    doLast {
        logger.info("Hello World!")
        logger.debug("Hello World!")
        logger.warn("Hello World!")
        logger.error("Hello World!")
        println("Hello World!")
    }
}

