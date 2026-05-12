import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.google.hilt)
    id("kotlin-kapt")
}

android {
    namespace = "com.muh.arifandi.dicoding.core.network"
    compileSdk = 35

    defaultConfig {
        minSdk = 23
        
        val baseUrl = gradleLocalProperties(rootDir, providers).getProperty("BASE_URL") ?: ""
        val apiKey = gradleLocalProperties(rootDir, providers).getProperty("NEWS_API_KEY") ?: ""
        
        buildConfigField("String", "BASE_URL", "\"$baseUrl\"")
        buildConfigField("String", "NEWS_API_KEY", "\"$apiKey\"")
    }

    buildFeatures {
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(libs.squareup.retrofit)
    implementation(libs.squareup.retrofit.gson)
    implementation(libs.squareup.okhttp.logging)
    implementation(libs.kotlinx.serialization.json)
    
    implementation(libs.google.hilt.android)
    kapt(libs.google.hilt.compiler)
}
