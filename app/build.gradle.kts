import prieto.fernando.dependencies.Dependencies
import prieto.fernando.dependencies.ProjectModules
import prieto.fernando.dependencies.TestDependencies

plugins {
    id("com.android.application")
    kotlin("android")
    id("prieto.fernando.android.plugin")
    id("dagger.hilt.android.plugin")
    id("org.jlleitschuh.gradle.ktlint") version "10.2.1"
    id("com.dicedmelon.gradle.jacoco-android")
}

androidPlugin {
    buildType = prieto.fernando.android.plugin.BuildType.App
}

jacoco {
    toolVersion = "0.8.2"

}

jacocoAndroidUnitTestReport {
    csv.enabled(false)
    html.enabled(true)
    xml.enabled(true)
}

android {
    defaultConfig {
        applicationId = "prieto.fernando.spacex"
        minSdk = prieto.fernando.dependencies.AndroidSettings.minSdk
        targetSdk = prieto.fernando.dependencies.AndroidSettings.targetSdk
        testInstrumentationRunner = "prieto.fernando.spacex.webmock.MockTestRunner"
    }

    buildFeatures {
        compose = true
        viewBinding = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.5"
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
            isTestCoverageEnabled = true
            buildConfigField("Integer", "PORT", "8080")
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                file("proguard-rules.pro")
            )
        }
    }
}

dependencies {
    implementation(project(ProjectModules.api))
    implementation(project(ProjectModules.domain))
    implementation(project(ProjectModules.data))

    implementation(Dependencies.AndroidX.fragmentKtx)
    implementation(Dependencies.AndroidX.lifecycleLivedataKtx)
    implementation(Dependencies.AndroidX.Compose.viewModel)
    kapt(Dependencies.AndroidX.lifecycleCompiler)
    implementation(Dependencies.AndroidX.archComponents)
    implementation(Dependencies.AndroidX.browser)

    implementation(Dependencies.AndroidX.Compose.ui)
    implementation(Dependencies.AndroidX.Compose.systemUiController)
    implementation(Dependencies.AndroidX.Compose.material)
    implementation(Dependencies.AndroidX.Compose.uiTooling)
    implementation(Dependencies.AndroidX.Compose.runtime)
    implementation(Dependencies.AndroidX.Compose.runtimeLiveData)
    implementation(Dependencies.AndroidX.Compose.navigation)

    implementation(Dependencies.Hilt.hiltAndroid)
    implementation(Dependencies.Hilt.hiltAndroidCompiler)
    implementation(Dependencies.Hilt.hiltCompiler)
    implementation(Dependencies.Hilt.hiltNavigationCompose)

    implementation(Dependencies.coilCompose)
    implementation(Dependencies.lottie)
    implementation(Dependencies.lottieCompose)

    implementation(Dependencies.AndroidX.constraintlayout)
    implementation(Dependencies.AndroidX.legacySupport)
    implementation(Dependencies.AndroidX.Navigation.fragmentKtx)
    implementation(Dependencies.AndroidX.Navigation.uiKtx)
    implementation(Dependencies.jodaTime)

    testImplementation(Dependencies.jodaTime)
    testImplementation(project(ProjectModules.coreAndroidTest))
    testImplementation(project(ProjectModules.domain))

    androidTestImplementation(project(ProjectModules.coreAndroidTest))
    androidTestImplementation(TestDependencies.AndroidX.core)
    androidTestImplementation(TestDependencies.AndroidX.coreKtx)
    androidTestImplementation(TestDependencies.AndroidX.runner)
    androidTestImplementation(TestDependencies.AndroidX.rules)
    androidTestImplementation(TestDependencies.AndroidX.composeUiTest)
    androidTestImplementation(TestDependencies.AndroidX.composeUiTestJUnit4)
    debugImplementation(TestDependencies.AndroidX.uiTestManifest)

    androidTestImplementation(TestDependencies.mockWebServer)

    androidTestImplementation(TestDependencies.Hilt.androidTesting)
    kaptAndroidTest(TestDependencies.Hilt.androidCompiler)
    androidTestAnnotationProcessor(TestDependencies.Hilt.androidCompiler)
}
