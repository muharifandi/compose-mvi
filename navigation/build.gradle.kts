/**
 * Created by Muh. Arifandi on 07/05/26.
 * Email : arif76440@gmail.com
 * Project: My Application
 * File: navigation/build.gradle.kts
 */
plugins {
    id("myapp.android.library")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.muh.arifandi.dicoding.navigation"
}

dependencies {
    api(project(":features:news:api"))
    api(project(":features:about:api"))
    api(project(":features:splash:api"))
    api(project(":features:onboarding:api"))
    api(project(":features:login:api"))

    implementation(libs.kotlinx.serialization.json)
}
