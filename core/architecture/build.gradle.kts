plugins {
    id("myapp.android.library")
}

android {
    namespace = "com.muh.arifandi.dicoding.core.architecture"
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.navigation.fragment.ktx)
}
