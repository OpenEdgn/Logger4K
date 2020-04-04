import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kotlinVersion: String by rootProject.extra

plugins {
    kotlin("jvm")
    maven

}
java.sourceCompatibility = JavaVersion.VERSION_1_8


dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation(kotlin("stdlib"))
    testCompile("junit:junit:4.12")
}


