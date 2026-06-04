plugins {
    id("myapp.android.feature")
    id("myapp.android.hilt")
}

android {
    namespace = "com.muh.arifandi.dicoding.features.master"
}

dependencies {
    implementation(project(":features:master:api"))
    implementation(project(":features:transfer:api"))
    implementation(project(":core:architecture"))
    implementation(project(":core:ui"))
    implementation(project(":core:network"))
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(project(":navigation"))
    implementation(libs.google.maps.compose)
}
