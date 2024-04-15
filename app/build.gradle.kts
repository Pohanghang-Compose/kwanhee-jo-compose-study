@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.pohahang.android.application)
    alias(libs.plugins.pohahang.android.application.compose)
    alias(libs.plugins.pohahang.android.feature)
    alias(libs.plugins.pohahang.android.hilt)
}

android {
    namespace = "com.koreatech.kwanhee_jo_compose_study"

    defaultConfig {
        applicationId = "com.koreatech.kwanhee_jo_compose_study"
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
}

dependencies {
    implementation(project(mapOf("path" to ":core:network")))
    implementation(project(mapOf("path" to ":core:data")))

    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.androidx.navigation.compose)
    testImplementation(libs.junit4)
    testImplementation(libs.orbit.test)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.espresso)
}
