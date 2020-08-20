plugins {
    `kotlin-dsl`
}

repositories {
    mavenLocal()
    jcenter()
    mavenCentral()
}

kotlin {
    sourceSets.all {
        languageSettings.useExperimentalAnnotation("kotlin.Experimental")
        languageSettings.useExperimentalAnnotation("kotlin.RequiresOptIn")
    }
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

dependencies {
    compileOnly(gradleApi())
}




