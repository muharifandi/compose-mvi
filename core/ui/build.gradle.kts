plugins {
    id("myapp.android.library")
    id("myapp.android.compose")
}

android {
    namespace = "com.muh.arifandi.dicoding.core.ui"
}

dependencies {
    implementation(project(":core:common"))
    implementation(libs.coil.compose)
}
