plugins {
    id("myapp.android.feature")
    id("myapp.android.hilt")
}

android {
    namespace = "com.muh.arifandi.dicoding.features.jobs.impl"
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:architecture"))
    implementation(project(":navigation"))
}
