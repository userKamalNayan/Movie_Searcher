import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.1" apply false
    id("org.jetbrains.kotlin.android") version Versions.kotlinVersion apply false
    id("com.android.library") version "8.2.1" apply false
    id("com.google.dagger.hilt.android") version Versions.hilt apply false
}

//subprojects {
//    this.apply {
////        apply(plugin = "kotlin-parcelize")
//plugin("kotlin-parcelize")
//
//        if (name == "app") { // for app module
//            apply(plugin = "com.android.application")
//            extensions.configure<ApplicationExtension> {
//                buildFeatures {
//                    buildConfig = true
//                }
//            }
//        } else {
//            apply(plugin = "com.android.library")
//            extensions.configure<LibraryExtension> {// for other modules
//                buildFeatures {
//                    buildConfig = true
//                }
//            }
//        }
//    }
//}