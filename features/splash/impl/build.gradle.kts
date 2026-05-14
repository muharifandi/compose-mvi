plugins {
    id("myapp.android.feature")
}

android {
    namespace = "com.muh.arifandi.dicoding.features.splash"
}

dependencies {
    implementation(project(":features:splash:api"))
    implementation(project(":navigation"))

    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.mockk)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
