import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.RepositoryHandler



fun RepositoryHandler.loadMirrors(project:Project) {
    mavenLocal()
    maven { url = project.uri("https://maven.aliyun.com/repository/public/") }
    jcenter()
    mavenCentral()
    maven { url = project.uri("https://jitpack.io") }
}

