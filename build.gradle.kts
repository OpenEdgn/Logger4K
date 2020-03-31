group = "tech.open-Edgn"
version = "last"

buildscript {
    val kotlinVersion :String by extra("1.3.50")


    repositories {
        maven { url = uri("https://maven.aliyun.com/repository/public/") }
        jcenter()
        maven { url = uri("https://jitpack.io") }

    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")

    }
}

allprojects {
    repositories {
        maven { url = uri("https://maven.aliyun.com/repository/public/") }
        jcenter()
        maven { url = uri( "https://jitpack.io") }
    }
}



tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}
