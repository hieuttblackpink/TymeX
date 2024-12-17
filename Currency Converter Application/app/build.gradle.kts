plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("kapt")
    id("com.google.dagger.hilt.android") version "2.44" apply false
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.currencyconverterapplication"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.currencyconverterapplication"
        minSdk = 21
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            buildConfigField("String", "API_KEY", "\"e5914c5171bc9d248c576bdec9f75282\"")
        }
        release {
            buildConfigField("String", "API_KEY", "\"e5914c5171bc9d248c576bdec9f75282\"")
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
        buildConfig = true
        compose = true
        viewBinding = true
        dataBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.google.material)
    implementation(libs.retrofit) // Networking
    implementation(libs.converter.gson) // JSON Parsing
    implementation(libs.retrofit2.converter.scalars)
    implementation(libs.kotlinx.coroutines.android) // Async operations
    implementation(libs.androidx.lifecycle.viewmodel.ktx) // ViewModel
    implementation(libs.androidx.lifecycle.livedata.ktx) // LiveData
    implementation(libs.androidx.recyclerview) // UI Component
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.material.spinner)
    implementation(libs.dagger.hilt.android)
    implementation(libs.logging.interceptor)
    implementation(libs.androidx.hilt.navigation.compose)
    kapt(libs.dagger.hilt.compiler)
    kapt(libs.androidx.hilt.hilt.compiler2)
    kapt(libs.hilt.android.compiler)
    kapt(libs.dagger.compiler)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
