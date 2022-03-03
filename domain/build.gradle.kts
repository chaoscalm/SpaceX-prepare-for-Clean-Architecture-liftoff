import prieto.fernando.dependencies.Dependencies
import prieto.fernando.dependencies.ProjectModules

plugins {
    id("com.android.library")
    id("prieto.fernando.android.plugin")
    id("com.dicedmelon.gradle.jacoco-android")
}

dependencies {
    implementation(Dependencies.Dagger.daggerAndroid)
    testImplementation(project(ProjectModules.coreAndroidTest))

    implementation(Dependencies.Hilt.hiltAndroid)
    implementation(Dependencies.Hilt.hiltAndroidCompiler)

    implementation(Dependencies.jodaTime)
    testImplementation(Dependencies.jodaTime)
}