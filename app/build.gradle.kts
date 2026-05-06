import org.gradle.kotlin.dsl.debugImplementation
import org.gradle.kotlin.dsl.releaseImplementation

plugins {
    alias(libs.plugins.android.application)
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.quotesapp2026"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }

    defaultConfig {
        applicationId = "com.example.quotesapp2026"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", "\"https://zenquotes.io/api/\"")
        }

        release {
            isMinifyEnabled = false

            buildConfigField("String", "BASE_URL", "\"https://zenquotes.io/api/\"")

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.androidx.hilt.common)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    // LiveData
    implementation(libs.androidx.lifecycle.livedata.ktx)
    // To use 'by viewModels()'
    implementation(libs.androidx.fragment.ktx)
    // Room Runtime
    implementation(libs.androidx.room.runtime)
    // Kotlin Extensions and Coroutines support for Room
    implementation(libs.androidx.room.ktx)
    // If this project uses any Kotlin source, use Kotlin Symbol Processing (KSP)
    ksp(libs.androidx.room.compiler.v261)
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation(libs.androidx.room.ktx.v261)
    // optional - Paging 3 Integration
    implementation(libs.androidx.room.paging)
    //retrofit
    implementation(libs.retrofit)
    //GSON-Converter
    implementation(libs.converter.gson)
    //Coroutine
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    implementation(libs.shimmer)

    debugImplementation(libs.leakcanary.android)
    releaseImplementation(libs.leakcanary.noop)

    implementation(libs.androidx.paging.runtime)

}