plugins {
    id("myapp.kotlin.library")
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
    compileOnly(libs.androidx.compose.ui)
}
