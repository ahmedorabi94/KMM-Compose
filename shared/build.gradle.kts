plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("com.squareup.sqldelight")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    targets.withType(org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget::class.java).all {
        binaries.withType(org.jetbrains.kotlin.gradle.plugin.mpp.Framework::class.java).all {
            export("dev.icerock.moko:mvvm-core:0.16.1")
        }
    }
    
    listOf(
        //  iosX64(),
        //  ios(),
        //  iosArm64(),
        iosSimulatorArm64(),
        //  ios(),
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            binaryOption("bundleId", "com.example.test1kmm.shared")
            linkerOpts  += "-ld64"

        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                //put your multiplatform dependencies here

                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

                implementation("com.squareup.sqldelight:runtime:1.5.5")
                implementation("com.squareup.sqldelight:coroutines-extensions:1.5.5")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
                implementation("dev.icerock.moko:mvvm-core:0.16.1")
                implementation("dev.icerock.moko:mvvm-compose:0.16.1")
                implementation("dev.icerock.moko:mvvm-flow:0.16.1")
                implementation("dev.icerock.moko:mvvm-flow-compose:0.16.1")
                implementation("org.jetbrains.kotlinx:atomicfu:0.17.3")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        //Android main
        val androidMain by getting {
            dependencies {
                implementation("com.squareup.sqldelight:android-driver:1.5.5")
                implementation("androidx.appcompat:appcompat:1.6.1")
                implementation("androidx.activity:activity-compose:1.8.0")
            }
        }
        val androidUnitTest by getting

        ///////////////////IOS/////////////////////////////////

//        val iosX64Main by getting
//        val iosArm64Main by getting
//        val iosSimulatorArm64Main by getting
//        val iosMain by getting {
//            dependencies {
//                implementation("com.squareup.sqldelight:native-driver:1.5.5")
//            }
//            dependsOn(commonMain)
//            iosX64Main.dependsOn(this)
//            iosArm64Main.dependsOn(this)
//            iosSimulatorArm64Main.dependsOn(this)
//        }
//        val iosX64Test by getting
//        val iosArm64Test by getting
//        val iosSimulatorArm64Test by getting
//        val iosTest by getting {
//            dependsOn(commonTest)
//            iosX64Test.dependsOn(this)
//            iosArm64Test.dependsOn(this)
//            iosSimulatorArm64Test.dependsOn(this)
//        }


        val iosMain by getting {
            dependencies {
                // implementation("io.ktor:ktor-client-darwin:2.2.4")
                // implementation("io.ktor:ktor-client-ios:2.2.4")
                implementation("com.squareup.sqldelight:native-driver:1.5.5")

            }
        }
        val iosTest by getting
        val iosSimulatorArm64Main by getting {
            dependsOn(iosMain)
        }
        val iosSimulatorArm64Test by getting {
            dependsOn(iosTest)
        }


    }



}

sqldelight{
    database("ContactDatabase"){
        packageName = "com.example.test1kmm.database"
        sourceFolders = listOf("sqldelight")
    }
}

android {
    namespace = "com.example.test1kmm"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation("androidx.core:core:1.12.0")
    commonMainApi("dev.icerock.moko:mvvm-core:0.16.1")
    commonMainApi("dev.icerock.moko:mvvm-compose:0.16.1")
    commonMainApi("dev.icerock.moko:mvvm-flow:0.16.1")
    commonMainApi("dev.icerock.moko:mvvm-flow-compose:0.16.1")
}