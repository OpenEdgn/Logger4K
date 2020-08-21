import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    `maven-publish`
}
java.sourceCompatibility = JavaVersion.VERSION_11

dependencies {
    implementation(kotlin("reflect",Versions.Kotlin.reflect))
    implementation(kotlin("stdlib",Versions.Kotlin.stdlib))
    implementation(project(":core"))
    testImplementation("org.junit.jupiter:junit-jupiter:${Versions.Junit.junitJupiter}")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "11"
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = group.toString()
            artifactId = project.name
            version = Versions.Project.core
            from(components["java"])
        }
    }
    repositories {
        mavenLocal()
    }
}

