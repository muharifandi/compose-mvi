import java.util.Properties
import java.io.FileInputStream

plugins {
    id("myapp.android.application")
    id("myapp.android.compose")
    id("myapp.android.hilt")
    id("myapp.android.room")
    id("myapp.android.paging")
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.androidx.baselineprofile)
    alias(libs.plugins.module.graph.assert)
}

val envProperties = Properties().apply {
    val envFile = rootProject.file("config.env")
    if (envFile.exists()) {
        load(FileInputStream(envFile))
    }
}

android {
    namespace = "com.muh.arifandi.dicoding"

    defaultConfig {
        applicationId = envProperties.getProperty("APP_ID") ?: "com.muh.arifandi.dicoding"
        
        versionCode = envProperties.getProperty("VERSION_CODE")?.toInt() ?: 1
        versionName = envProperties.getProperty("VERSION_NAME") ?: "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        
        buildConfigField(
            "String",
            "NEWS_API_KEY",
            "\"${envProperties.getProperty("NEWS_API_KEY") ?: ""}\""
        )

        buildConfigField(
            "String",
            "BASE_URL",
            "\"${envProperties.getProperty("BASE_URL") ?: ""}\""
        )
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    packaging {
        jniLibs {
            useLegacyPackaging = false
        }
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/LICENSE.md"
            excludes += "META-INF/LICENSE-notice.md"
        }
    }
}

dependencies {
    baselineProfile(project(":baselineprofile"))
    implementation(project(":features:splash:impl"))
    implementation(project(":features:about:impl"))
    implementation(project(":features:news:impl"))
    implementation(project(":features:onboarding:impl"))
    implementation(project(":features:login:impl"))
    implementation(project(":features:register:impl"))
    implementation(project(":features:forgotpassword:impl"))
    
    implementation(project(":core:ui"))
    implementation(project(":core:common"))
    implementation(project(":core:network"))
    implementation(project(":core:architecture"))
    implementation(project(":navigation"))

    implementation(libs.timber)

    implementation(libs.androidx.core.splashscreen)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.leakcanary.android)
    
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.hilt.navigation.compose)
    
    implementation(libs.squareup.retrofit)
    implementation(libs.squareup.retrofit.gson)
    implementation(libs.squareup.okhttp.logging)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.coil.compose)
    
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockk)
    testImplementation(libs.turbine)
    
    androidTestImplementation(project(":core:testing"))
}

moduleGraphAssert {
    maxHeight = 4
    // Strict rules to maintain the "Highly Scalable Engineering Foundation"
    allowed = arrayOf(
        ":app -> :navigation",
        ":app -> :features:.*:impl",
        ":app -> :core:.*",
        ":navigation -> :features:.*:api",
        ":features:.*:impl -> :features:.*:api",
        ":features:.*:impl -> :core:.*",
        ":features:.*:impl -> :navigation",
        ":core:.* -> :core:.*"
    )
    restricted = arrayOf(
        ":features:.*:api -> :features:.*:impl", // API cannot depend on Impl
        ":features:.*:impl -> :features:.*:impl" // Impl cannot depend on Impl
    )
}
