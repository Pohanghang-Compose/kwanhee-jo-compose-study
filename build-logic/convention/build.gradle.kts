@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("AndroidApplicationConventionPlugin") {
            id = "pohahang.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("AndroidApplicationComposeConventionPlugin") {
            id = "pohahang.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("AndroidFeatureConventionPlugin") {
            id = "pohahang.android.plugin.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("AndroidHiltConventionPlugin") {
            id = "pohahang.android.plugin.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("AndroidLibraryConventionPlugin") {
            id = "pohahang.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
    }
}