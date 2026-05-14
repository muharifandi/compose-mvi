plugins {
    id("myapp.android.library")
}

android {
    namespace = "com.muh.arifandi.dicoding.core.testing"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    
    api(libs.junit)
    api(libs.mockk)
    api(libs.kotlinx.coroutines.test)
    api(libs.turbine)
    api(libs.archunit)
    
    api(libs.androidx.compose.ui.test.junit4)
    debugApi(libs.androidx.compose.ui.test.manifest)
}
