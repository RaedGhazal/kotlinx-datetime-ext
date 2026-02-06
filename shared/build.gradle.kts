import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsEnvSpec

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.mavenPublish)
}

mavenPublishing {
    publishToMavenCentral()
    signAllPublications()
    coordinates("com.raedghazal", "kotlinx_datetime_ext", "1.4.0")

    pom {
        name.set("Kotlinx datetime ext")
        description.set("A KMP library that provides extensions and helper functions for kotlinx-datetime ")
        inceptionYear.set("2023")
        url.set("https://github.com/RaedGhazal/kotlinx-datetime-ext")
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
            connection.set("scm:git:git://github.com/RaedGhazal/kotlinx-datetime-ext.git")
            developerConnection.set("scm:git:ssh://git@github.com/RaedGhazal/kotlinx-datetime-ext.git")
        }
    }
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_1_8
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
                implementation(libs.kotlinx.browser)
                implementation(npm("date-fns", libs.versions.date.fns.version.get()))
            }
        }
        val wasmJsMain by getting {
            dependencies {
                implementation(libs.kotlinx.browser)
                implementation(npm("date-fns", libs.versions.date.fns.version.get()))
            }
        }
    }
}

android {
    namespace = "com.raedghazal.kotlinx_datetime_ext"
    compileSdk = 36
    defaultConfig {
        minSdk = 26
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
}

rootProject.the<NodeJsEnvSpec>().apply {
    version = "22.0.0-v8-canary202401102ecfc94f85"
    downloadBaseUrl = "https://mirrors.dotsrc.org/nodejs/v8-canary"
}

rootProject.tasks.withType<org.jetbrains.kotlin.gradle.targets.js.npm.tasks.KotlinNpmInstallTask>().configureEach {
    args.add("--ignore-engines")
}

/*
* How to publish a new version:
* * increase library verion
* * run ./gradlew publishAndReleaseToMavenCentral
*  */