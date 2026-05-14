import com.android.build.api.dsl.CommonExtension
import com.muh.arifandi.dicoding.convention.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType

class AndroidComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "org.jetbrains.kotlin.plugin.compose")

            // Use getByType and rely on the fact that this should only be applied 
            // to projects that have an Android extension.
            // If it's not there, it will throw a helpful error during configuration.
            try {
                val extension = extensions.getByType<CommonExtension<*, *, *, *, *, *>>()
                configureAndroidCompose(extension)
            } catch (e: Exception) {
                // Fallback for cases where getByType might fail with CommonExtension
                pluginManager.withPlugin("com.android.application") {
                    configureAndroidCompose(extensions.getByType<com.android.build.api.dsl.ApplicationExtension>())
                }
                pluginManager.withPlugin("com.android.library") {
                    configureAndroidCompose(extensions.getByType<com.android.build.api.dsl.LibraryExtension>())
                }
            }
        }
    }
}
