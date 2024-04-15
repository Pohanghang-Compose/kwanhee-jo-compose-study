@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.pohahang.android.library)
    alias(libs.plugins.pohahang.android.hilt)
    id("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = "com.koreatech.core.network"


    defaultConfig {
        buildConfigField("String", "base_url", getPropertyKey("base_url"))
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
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.okhttp.logging)
    implementation(libs.gson)
    implementation(project(mapOf("path" to ":core:data")))
}

fun getPropertyKey(propertyKey: String): String {
    val nullableProperty: String? =
        com.android.build.gradle.internal.cxx.configure.gradleLocalProperties(rootDir)
            .getProperty(propertyKey)
    return nullableProperty ?: "null"
}