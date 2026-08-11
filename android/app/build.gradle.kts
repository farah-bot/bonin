plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.bonin"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.bonin"

        minSdk = 26
        targetSdk = 36

        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner =
            "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {

        debug {
            buildConfigField(
                type = "String",
                name = "API_BASE_URL",
                value = "\"http://10.0.2.2:8080/\""
            )
        }

        release {
            isMinifyEnabled = false

            buildConfigField(
                type = "String",
                name = "API_BASE_URL",
                value = "\"https://example.invalid/\""
            )

            proguardFiles(
                getDefaultProguardFile(
                    "proguard-android-optimize.txt"
                ),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    implementation(
        platform(libs.androidx.compose.bom)
    )

    implementation(
        libs.androidx.core.ktx
    )

    implementation(
        libs.androidx.lifecycle.runtime.ktx
    )

    implementation(
        libs.androidx.activity.compose
    )

    implementation(
        libs.androidx.compose.ui
    )

    implementation(
        libs.androidx.compose.ui.graphics
    )

    implementation(
        libs.androidx.compose.ui.tooling.preview
    )

    implementation(
        libs.androidx.compose.material3
    )

    implementation(
        libs.androidx.navigation3.runtime
    )

    implementation(
        libs.androidx.navigation3.ui
    )

    implementation(
        libs.kotlinx.serialization.core
    )

    implementation(
        libs.kotlinx.serialization.json
    )

    implementation(
        libs.hilt.android
    )

    ksp(
        libs.hilt.compiler
    )

    implementation(
        libs.retrofit.core
    )

    implementation(
        libs.retrofit.kotlinx.serialization
    )

    implementation(
        libs.okhttp.logging.interceptor
    )

    implementation(
        libs.androidx.datastore.preferences
    )

    testImplementation(
        libs.junit
    )

    androidTestImplementation(
        platform(libs.androidx.compose.bom)
    )

    androidTestImplementation(
        libs.androidx.junit
    )

    androidTestImplementation(
        libs.androidx.espresso.core
    )

    androidTestImplementation(
        libs.androidx.compose.ui.test.junit4
    )

    debugImplementation(
        libs.androidx.compose.ui.tooling
    )

    debugImplementation(
        libs.androidx.compose.ui.test.manifest
    )
}