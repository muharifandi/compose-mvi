plugins {
    id("myapp.android.feature")
    id("myapp.android.hilt")
}

android {
    namespace = "com.muh.arifandi.dicoding.features.register"
}

dependencies {
    implementation(project(":features:register:api"))
    implementation(project(":core:architecture"))
    implementation(project(":core:ui"))
    implementation(project(":core:network"))
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(project(":navigation"))
}
