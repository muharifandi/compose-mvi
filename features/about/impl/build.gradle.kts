plugins {
    id("myapp.android.feature")
}

android {
    namespace = "com.muh.arifandi.dicoding.features.about"
}

dependencies {
    implementation(project(":features:about:api"))
    implementation(project(":core:architecture"))
    implementation(project(":navigation"))

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)

    androidTestImplementation(project(":core:testing"))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
