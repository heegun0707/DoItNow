import java.util.Properties
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.google.services)
    id("kotlin-kapt")
}

android {
    namespace = "org.choleemduo.doitnow"
    compileSdk = 35

    defaultConfig {
        applicationId = "org.choleemduo.doitnow"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            buildConfigField("String", "KAKAO_APP_KEY", getApiKey("kakao.app.key"))
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            buildConfigField("String", "KAKAO_APP_KEY", getApiKey("kakao.app.key"))
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

fun getApiKey(propertyKey: String): String {
    return gradleLocalProperties(rootDir, providers).getProperty(propertyKey)
}

dependencies {
    implementation (project(":data"))
    implementation (project(":domain"))

    // Ktx
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Compose
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
//    implementation(libs.androidx.material)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.navigation.compose)

    // Coroutine
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.core)

    // Coil
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    // DI(Hilt)
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    kapt(libs.hilt.android.compiler)

    // Room
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    // Kotlin Extensions and Coroutines support for Room
    implementation(libs.androidx.room.ktx)

    // Retrofit2
    implementation(libs.retrofit)
    implementation(libs.converter.scalars)

    // WorkManager
    implementation(libs.androidx.work.runtime.ktx)
    androidTestImplementation(libs.androidx.work.testing)

    // Timber
    implementation (libs.timber)

    // Splash
    implementation (libs.androidx.core.splashscreen)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)

    // Kakao Login
    implementation (libs.kakao.v2.user)

    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}