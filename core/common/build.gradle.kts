/**
 * Created by Muh. Arifandi on 07/05/26.
 * Email : arif76440@gmail.com
 * Project: My Application
 * File: core/common/build.gradle.kts
 */
plugins {
    id("myapp.android.library")
    id("myapp.android.hilt")
    id("myapp.android.security")
}

android {
    namespace = "com.muh.arifandi.dicoding.core.common"
}

dependencies {
    api(project(":core:model"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.compose.ui)
    implementation(libs.timber)
}
