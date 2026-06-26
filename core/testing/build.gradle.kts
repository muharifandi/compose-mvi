plugins {
    id("myapp.android.library")
    id("myapp.android.hilt")
}

android {
    namespace = "com.muh.arifandi.dicoding.core.testing"
}

dependencies {
    implementation(project(":core:model"))
    api(libs.junit)
    api(libs.androidx.junit)
    api(libs.androidx.espresso.core)
    api(libs.kotlinx.coroutines.test)
    api(libs.mockk)
    api(libs.turbine)
}
