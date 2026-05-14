plugins {
    id("myapp.android.library")
}

android {
    namespace = "com.muh.arifandi.dicoding.core.architecture"
}

dependencies {
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.navigation.compose)
}
