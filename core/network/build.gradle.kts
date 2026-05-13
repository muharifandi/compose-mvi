import java.util.Properties
import java.io.FileInputStream

plugins {
    id("myapp.android.library")
    id("myapp.android.hilt")
    alias(libs.plugins.kotlin.serialization)
}

val envProperties = Properties().apply {
    val envFile = rootProject.file("config.env")
    if (envFile.exists()) {
        load(FileInputStream(envFile))
    }
}

android {
    namespace = "com.muh.arifandi.dicoding.core.network"

    defaultConfig {
        val baseUrl = envProperties.getProperty("BASE_URL") ?: ""
        val apiKey = envProperties.getProperty("NEWS_API_KEY") ?: ""
        
        buildConfigField("String", "BASE_URL", "\"$baseUrl\"")
        buildConfigField("String", "NEWS_API_KEY", "\"$apiKey\"")
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(":core:common"))
    api(project(":core:model"))
    
    implementation(libs.squareup.retrofit)
    implementation(libs.squareup.retrofit.gson)
    implementation(libs.squareup.okhttp.logging)
    implementation(libs.kotlinx.serialization.json)
}
