import com.android.tools.build.jetifier.plugin.gradle.JetifierExtension
import com.android.tools.build.jetifier.plugin.gradle.JetifierPlugin

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
}
apply<JetifierPlugin>()

android {
    compileSdkVersion(29)
    defaultConfig {
        applicationId = "com.esentsov.sample.jetifier"
        minSdkVersion(21)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.setGeneratedDensities(emptyList<String>())
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

val jetifier = the<JetifierExtension>().apply {
    setConfigFile(file("jetifier.config.json").path)
}

dependencies {
    implementation(kotlin("stdlib-jdk7"))
    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("androidx.core:core-ktx:1.1.0")
    implementation(jetifier.process("androidx.constraintlayout:constraintlayout:1.1.3"))
    implementation("androidx.constraintlayout:constraintlayout:2.0.0-beta2")
}

