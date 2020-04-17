group = "tech.open-Edgn"
version = "last"

buildscript {
    val kotlinVersion :String by extra("1.3.71")


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
    for (childProject in childProjects.values) {
        delete(childProject.buildDir)
    }
}

tasks.register("push"){
    dependsOn("clean")
    doLast {
        println("execute command :git add * && git commit -m '[Auto Save]' && git push")
        val process = Runtime.getRuntime().exec("git add * && git commit -m '[Auto Save]' && git push")
        println("result :${process.inputStream.readBytes().toString(Charsets.UTF_8)}")
        println("result error :${process.errorStream.readBytes().toString(Charsets.UTF_8)}")
    }
}