
plugins {
    id("pohahang.plugin.application")
    id("pohahang.plugin.application.compose")
    id("pohahang.plugin.feature")
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
    testImplementation(libs.junit4)
    testImplementation(libs.orbit.test)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.espresso)
}