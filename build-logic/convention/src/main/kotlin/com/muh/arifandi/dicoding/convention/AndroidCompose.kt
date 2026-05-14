package com.muh.arifandi.dicoding.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * Configure Compose-specific options
 */
internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        buildFeatures {
            compose = true
        }
    }

    val isLibrary = pluginManager.hasPlugin("com.android.library")
    val config = if (isLibrary) "api" else "implementation"

    dependencies {
        val bom = libs.findLibrary("androidx-compose-bom").get()
        add(config, platform(bom))
        add("androidTestImplementation", platform(bom))

        add(config, libs.findLibrary("androidx-compose-ui").get())
        add(config, libs.findLibrary("androidx-compose-material3").get())
        add(config, libs.findLibrary("androidx-compose-material-icons-extended").get())
        add(config, libs.findLibrary("androidx-compose-ui-tooling-preview").get())
        add("debugImplementation", libs.findLibrary("androidx-compose-ui-tooling").get())
    }
}
