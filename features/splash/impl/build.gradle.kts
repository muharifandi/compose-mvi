plugins {
    id("myapp.android.feature")
    id("myapp.android.hilt")
}

android {
    namespace = "com.muh.arifandi.dicoding.features.splash"
}

dependencies {
    implementation(project(":features:splash:api"))
    implementation(project(":features:login:api"))
    implementation(project(":core:architecture"))
    implementation(project(":navigation"))

    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.mockk)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
