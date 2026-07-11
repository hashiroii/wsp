enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

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
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "wsp"
include(":composeApp")
include(":core:ui")
include(":core:domain")
include(":core:data")
include(":feature:home")
include(":feature:settings")
include(":feature:profile")
include(":feature:schedule")
include(":feature:files")
include(":feature:financial")
include(":feature:grades")
include(":feature:registration")
include(":feature:news")
