plugins {
    id("myapp.android.feature")
    id("myapp.android.room")
    id("myapp.android.paging")
    id("myapp.android.security")
}

android {
    namespace = "com.muh.arifandi.dicoding.features.news"
}

dependencies {
    implementation(project(":features:news:api"))
    implementation(project(":features:about:api"))
    implementation(project(":core:network"))
    implementation(project(":core:architecture"))
    
    implementation(libs.coil.compose)

    implementation(libs.squareup.retrofit)
    implementation(libs.squareup.retrofit.gson)
}
