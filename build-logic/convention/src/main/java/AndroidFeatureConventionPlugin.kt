import com.koreatech.convention.implementation
import com.koreatech.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal class AndroidFeatureConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                implementation(libs.findLibrary("androidx.core.ktx").get())
                implementation(libs.findLibrary("androidx.lifecycle.runtime.ktx").get())
                implementation(libs.findLibrary("androidx.activity.compose").get())
                implementation(libs.findLibrary("androidx.lifecycle.viewmodel.compose").get())
                implementation(libs.findLibrary("androidx.navigation.compose").get())
                implementation(libs.findLibrary("coil.compose").get())
                implementation(libs.findBundle("orbit").get())
            }
        }
    }
}