plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id ("kotlin-kapt")
    id("kotlin-parcelize")
    id ("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.realestatemanager"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.realestatemanager"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        compose = true
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.room.common)
    implementation(libs.androidx.lifecycle.runtime.compose.android)
    implementation(libs.androidx.runtime.livedata)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation ("androidx.room:room-runtime:2.6.1")
    kapt ("androidx.room:room-compiler:2.6.1")

    // optional - Kotlin Extensions and Coroutines support for Room
    implementation ("androidx.room:room-ktx:2.6.1")

    implementation ("com.google.dagger:hilt-android:2.51.1")
    kapt ("com.google.dagger:hilt-compiler:2.51.1")

    implementation ("androidx.hilt:hilt-navigation-compose:1.2.0")

    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.4")

    // Android Maps Compose composables for the Maps SDK for Android
    implementation ("com.google.maps.android:maps-compose:4.4.1")
    implementation ("com.google.android.gms:play-services-maps:19.0.0")
    implementation ("com.google.android.gms:play-services-location:21.3.0")

    // Location
    implementation(libs.play.services.location)

    //permissions
    implementation ("com.google.accompanist:accompanist-permissions:0.32.0")

    implementation ("com.google.code.gson:gson:2.10.1")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

    // Coil
    implementation("io.coil-kt:coil:2.7.0")
    implementation("io.coil-kt:coil-compose:2.7.0")

    // Navigation adaptive
    implementation(libs.androidx.material3.adaptive.navigation.suite)
    implementation("androidx.compose.material3:material3-window-size-class:1.3.0")
    implementation("androidx.compose.material3.adaptive:adaptive")
    implementation("androidx.compose.material3.adaptive:adaptive-layout")
    implementation("androidx.compose.material3.adaptive:adaptive-navigation")


    //testImplementation ("org.mockito.kotlin:mockito-kotlin:4.0.0")
    //Android UI Test
    //androidTestImplementation("org.robolectric:robolectric:4.9.2")
    // Optional -- Mockito framework
    testImplementation ("org.mockito:mockito-core:4.+")
    testImplementation ("org.mockito:mockito-core:4.+")
    androidTestImplementation ("org.mockito:mockito-android:4.+")
    //debugImplementation (libs.mockito.inline)

}