plugins {
    id("myapp.android.feature")
    id("myapp.android.hilt")
}

android {
    namespace = "com.muh.arifandi.dicoding.features.intro"
}

dependencies {
    implementation(project(":core:architecture"))
    implementation(project(":core:ui"))
    implementation(project(":core:common"))
    implementation(project(":navigation"))
}
