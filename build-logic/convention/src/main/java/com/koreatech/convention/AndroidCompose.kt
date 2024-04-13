package com.koreatech.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *>,
) {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    commonExtension.apply {
        composeOptions {
            kotlinCompilerExtensionVersion =
                libs.findVersion("composeCompilerVersion").get().requiredVersion
        }
        buildFeatures {
            compose = true
        }

        packaging {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }
    }
    dependencies {
        api(platform(libs.findLibrary("androidx.compose.bom").get()))
        implementation(libs.findBundle("compose").get())
        debugImplementation(libs.findLibrary("androidx.compose.ui.test.manifest").get())
        androidTestImplementation(libs.findLibrary("androidx.compose.ui.test").get())
    }
}

