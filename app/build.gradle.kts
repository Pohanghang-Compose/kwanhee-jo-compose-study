
plugins {
    id("pohahang.plugin.application")
    id("pohahang.plugin.application.compose")
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
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.navigation.compose)

    implementation(libs.bundles.orbit)
    implementation(libs.coil.compose)

    testImplementation(libs.junit4)
    testImplementation(libs.orbit.test)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.espresso)
}