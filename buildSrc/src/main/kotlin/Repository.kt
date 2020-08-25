import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.RepositoryHandler

object Repository {

    fun loadMirrors(handler: RepositoryHandler, project: Project) {
        handler.run {
            mavenLocal()
            maven { url = project.uri("https://maven.aliyun.com/repository/public/") }
            jcenter()
            mavenCentral()
            maven { url = project.uri("https://jitpack.io") }
        }

    }
}


