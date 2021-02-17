group = "com.github.OpenEDGN"
version = "last"

buildscript {
    repositories {
        mavenLocal()
        maven { url = project.uri("https://maven.aliyun.com/repository/public/") }
        jcenter()
        mavenCentral()
        maven { url = project.uri("https://jitpack.io") }
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.21")
    }
}

allprojects {
    repositories {
        mavenLocal()
        maven { url = project.uri("https://maven.aliyun.com/repository/public/") }
        jcenter()
        mavenCentral()
        maven { url = project.uri("https://jitpack.io") }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
    for (childProject in childProjects.values) {
        delete(childProject.buildDir)
    }
}
