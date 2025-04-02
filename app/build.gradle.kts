plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.metmuseum"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.metmuseum"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.support.annotations)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

       /* implementation(libs.retrofit)
        implementation(libs.converter.moshi)

        // Moshi (pentru serializarea/deserializarea JSON)
        implementation(libs.moshi)
        implementation(libs.moshi.kotlin)

        // OkHttp (pentru interceptor)
        implementation(libs.okhttp)
        implementation(libs.logging.interceptor)

        // Koin pentru injecție de dependență
        implementation(libs.koin.android)

        // Coil (pentru a încărca imagini în Jetpack Compose)
        implementation(libs.coil.compose)

        // Lifecycle pentru ViewModel și LiveData
        implementation(libs.androidx.lifecycle.viewmodel.compose)
        implementation(libs.androidx.lifecycle.runtime.ktx.v250)

        // Jetpack Compose
        implementation(libs.ui)
        implementation(libs.androidx.material)
        implementation(libs.ui.tooling.preview)
        implementation(libs.androidx.navigation.compose)*/

    implementation(libs.retrofit.core)
    implementation(libs.retrofit.moshiConverter)

    implementation(libs.navigation)
    implementation(libs.kotlinx.serialization.core)

    implementation(libs.moshi.core)
    implementation(libs.moshi.adapters)
    ksp(libs.moshi.annotationProcessor)

    implementation(libs.koin.android)
    implementation(libs.koin.compose)

    implementation(libs.coil.compose)

    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)




}