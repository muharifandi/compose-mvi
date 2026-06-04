pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

includeBuild("build-logic")

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "SakaAndroid"

// App & Navigation
include(":app")
include(":navigation")
include(":baselineprofile")

// Core Modules
include(":core:common")
include(":core:architecture")
include(":core:model")
include(":core:network")
include(":core:ui")
include(":core:testing")

// Feature Modules
include(":features:splash:impl")
include(":features:splash:api")
include(":features:login:api")
include(":features:login:impl")
include(":features:register:api")
include(":features:register:impl")
