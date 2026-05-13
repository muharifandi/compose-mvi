plugins {
    id("com.android.test")
    id("org.jetbrains.kotlin.android")
    alias(libs.plugins.androidx.baselineprofile)
}

android {
    namespace = "com.muh.arifandi.dicoding.baselineprofile"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
        targetSdk = 35

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    targetProjectPath = ":app"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.espresso.core)
    implementation(libs.androidx.junit)
    implementation("androidx.benchmark:benchmark-macro-junit4:1.3.1")
    implementation("androidx.test.uiautomator:uiautomator:2.3.0")
}

baselineProfile {
    // The filter is used to include/exclude classes and methods from the profile.
    // By default, it includes everything from the target project.
}
