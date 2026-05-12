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
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "My Application"
include(":app")
include(":core:common")
include(":core:database")
include(":core:network")
include(":core:ui")
include(":core:testing")
include(":domain:news")
include(":features:home")
include(":features:detail")
include(":features:splash")
include(":features:about")
include(":features:bookmark")
include(":features:news:ui")
include(":navigation")
 