plugins {
    id("myapp.android.feature")
    id("myapp.android.room")
    id("myapp.android.paging")
}

android {
    namespace = "com.muh.arifandi.dicoding.features.news"
}

dependencies {
    implementation(project(":features:news:api"))
    implementation(project(":features:about:api"))
    implementation(project(":core:network"))
    
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.coil.compose)

    implementation(libs.squareup.retrofit)
    implementation(libs.squareup.retrofit.gson)
}
