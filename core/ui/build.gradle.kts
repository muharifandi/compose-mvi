plugins {
    id("myapp.android.library")
}

android {
    namespace = "com.muh.arifandi.dicoding.core.ui"
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":core:common"))
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
}
