plugins {
    `kotlin-dsl`
}

group = "com.muh.arifandi.dicoding.convention"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.compiler.gradlePlugin)
    compileOnly(libs.detekt.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "myapp.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "myapp.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("kotlinLibrary") {
            id = "myapp.kotlin.library"
            implementationClass = "KotlinLibraryConventionPlugin"
        }
        register("androidFeature") {
            id = "myapp.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("androidCompose") {
            id = "myapp.android.compose"
            implementationClass = "AndroidComposeConventionPlugin"
        }
        register("androidHilt") {
            id = "myapp.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("androidRoom") {
            id = "myapp.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }
        register("androidPaging") {
            id = "myapp.android.paging"
            implementationClass = "AndroidPagingConventionPlugin"
        }
        register("androidSecurity") {
            id = "myapp.android.security"
            implementationClass = "AndroidSecurityConventionPlugin"
        }
        register("androidDetekt") {
            id = "myapp.android.detekt"
            implementationClass = "AndroidDetektConventionPlugin"
        }
    }
}
