plugins {
    id("myapp.android.feature")
    id("myapp.android.hilt")
}

dependencies {
    implementation(project(":features:login:api"))
    implementation(project(":core:architecture"))
    implementation(project(":core:ui"))
    implementation(project(":core:network"))
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(project(":navigation"))
}
