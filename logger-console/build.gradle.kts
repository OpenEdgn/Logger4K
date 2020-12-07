import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    `maven-publish`
}
java.sourceCompatibility = JavaVersion.VERSION_11

java {
    modularity.inferModulePath.set(true)
}

val compileKotlin: KotlinCompile by tasks
val compileJava: JavaCompile by tasks
compileKotlin.destinationDir = compileJava.destinationDir




dependencies {
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")
    testImplementation("org.junit.platform:junit-platform-launcher:1.6.2")
    implementation(project(":core"))
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.withType<Zip> {
    duplicatesStrategy = DuplicatesStrategy.INCLUDE // allow duplicates
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "11"
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = group.toString()
            artifactId = project.name
            version = rootProject.version.toString()
            from(components["java"])
        }
    }
    repositories {
        mavenLocal()
    }
}

