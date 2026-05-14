import com.android.build.api.dsl.LibraryExtension
import com.muh.arifandi.dicoding.convention.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("myapp.android.detekt")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 35
                // The resource prefix is derived from the module name,
                // so we can use it to help prevent resource name collisions.
                // resourcePrefix = path.split(':').filter { it.isNotEmpty() }.joinToString(separator = "_")
            }
        }
    }
}
