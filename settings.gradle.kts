enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("commonlibs") {
            from(files("./catalogs/common.versions.toml"))
        }
    }
}

rootProject.name = "kotlinx-datetime-ext"
include(":shared")