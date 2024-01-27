plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.kamalnayan.moviesearcher"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.kamalnayan.moviesearcher"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        buildConfigField("String", "BASE_URL", "\"https://www.omdbapi.com/\"")
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
        dataBinding = true
    }
}

dependencies {
    implementation(project(Module.commons))
    implementation(project(Module.data))
    implementation(project(Module.domain))

    implementation(Dependencies.navigationFragment)
    implementation(Dependencies.navigationUi)
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.material)
    implementation(Dependencies.constraintLayout)

    //hilt
    implementation(Dependencies.hiltAndroid)
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    kapt(Dependencies.hiltCompiler)

    implementation(Dependencies.lifecycleKtx)
    implementation(Dependencies.lifecycleViewModelKtx)

    // epoxy
    implementation(Dependencies.epoxy)
    implementation(Dependencies.epoxyPaging)
    implementation(Dependencies.epoxyDataBinding)
    kapt(Dependencies.epoxyProcessor)

    //reflection
    implementation(Dependencies.kotlinReflection)

    //lottie
    implementation(Dependencies.lottie)
}