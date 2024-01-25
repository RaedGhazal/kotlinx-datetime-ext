import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.mavenPublish)
}

mavenPublishing {
    publishToMavenCentral(com.vanniktech.maven.publish.SonatypeHost.S01)
    signAllPublications()
    coordinates("com.raedghazal", "kotlinx_datetime_ext", "1.2.0")

    pom {
        name.set("Kotlinx datetime ext")
        description.set("A KMM library that provides extensions and helper functions for kotlinx-datetime ")
        inceptionYear.set("2023")
        url.set("https://github.com/RaedGhazal/kmm-kotlinx-datetime-ext")
        developers {
            developer {
                id.set("RaedGhazal")
                name.set("Raed Ghazal")
                url.set("https://github.com/RaedGhazal")
            }
        }
        licenses {
            license {
                name.set("MIT License")
                url.set("https://opensource.org/license/mit/")
                distribution.set("https://opensource.org/license/mit/")
            }
        }
        scm {
            url.set("https://github.com/RaedGhazal")
            connection.set("scm:git:git://github.com/RaedGhazal/kmm-kotlinx-datetime-ext.git")
            developerConnection.set("scm:git:ssh://git@github.com/RaedGhazal/kmm-kotlinx-datetime-ext.git")
        }
    }
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
        publishLibraryVariants("release", "debug")
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    jvm("desktop")
    js(IR) {
        useEsModules()
        browser {
            testTask {
                useKarma {
                    useChromeHeadless()
                }
            }
        }
        nodejs {
            testTask {
                useMocha()
            }
        }
    }
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        useEsModules()
        browser {
            testTask {
                useKarma {
                    useChromeHeadless()
                }
            }
        }
        nodejs {
            testTask {
                useMocha()
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlinx.datetime)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(npm("date-fns", libs.versions.date.fns.version.get()))
            }
        }
        val wasmJsMain by getting {
            dependencies {
                implementation(npm("date-fns", libs.versions.date.fns.version.get()))
            }
        }
    }
}

android {
    namespace = "com.raedghazal.kotlinx_datetime_ext"
    compileSdk = 34
    defaultConfig {
        minSdk = 26
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
}

rootProject.the<NodeJsRootExtension>().apply {
    nodeVersion = "22.0.0-v8-canary202401102ecfc94f85"
    nodeDownloadBaseUrl = "https://mirrors.dotsrc.org/nodejs/v8-canary"
}

rootProject.tasks.withType<org.jetbrains.kotlin.gradle.targets.js.npm.tasks.KotlinNpmInstallTask>().configureEach {
    args.add("--ignore-engines")
}
