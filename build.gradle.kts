group = "com.github.openEDGN"
version = Versions.Project.core

buildscript {
    repositories.loadMirrors(rootProject)
    dependencies {
        classpath("${kotlin("gradle-plugin")}:${Versions.Kotlin.gradlePlugin}")
    }
}

allprojects {
    repositories.loadMirrors(rootProject)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
    for (childProject in childProjects.values) {
        delete(childProject.buildDir)
    }
}
