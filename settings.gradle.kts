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

rootProject.name = "3205Team"
include(":app")

include(":presentation:search")
include(":presentation:history")
include(":domain:search")
include(":domain:history")
include(":data:database")
include(":data:search")
include(":data:history")
include(":presentation:base_ui")
