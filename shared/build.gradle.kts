plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.mavenPublish)
}

mavenPublishing {
    publishToMavenCentral(com.vanniktech.maven.publish.SonatypeHost.S01)
    signAllPublications()
    coordinates("com.raedghazal", "kotlinx_datetime_ext", "1.0.0")

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
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "16.0"
        framework {
            baseName = "shared"
            isStatic = true
        }
    }
    
    sourceSets {
        commonMain.dependencies {
            implementation(commonlibs.kotlinx.datetime)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
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
    implementation(commonlibs.androidx.core.ktx)
}
