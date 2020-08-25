group = "com.github.openEDGN"
version = Versions.Project.core

buildscript {
    Repository.loadMirrors(repositories, rootProject)
    dependencies {
        classpath("${kotlin("gradle-plugin")}:${Versions.Kotlin.gradlePlugin}")
    }
}

plugins {
    kotlin("jvm") version Versions.Kotlin.jvmPlugin apply false
}

allprojects {
    Repository.loadMirrors(repositories, rootProject)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
    for (childProject in childProjects.values) {
        delete(childProject.buildDir)
    }
}
