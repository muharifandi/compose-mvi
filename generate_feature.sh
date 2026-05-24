#!/bin/bash

# Saka Feature Generator (Standardized with SakaScaffold and Navigation)
# Usage: ./generate_feature.sh <feature_name>

FEATURE_NAME=$1

if [ -z "$FEATURE_NAME" ]; then
    echo "Error: Nama fitur harus diisi. Contoh: ./generate_feature.sh login"
    exit 1
fi

# Konfigurasi Header
AUTHOR="Muh. Arifandi"
EMAIL="arif76440@gmail.com"
PROJECT="My Application"
CURRENT_DATE=$(date +"%d/%m/%Y")

# Nama paket dasar
BASE_PACKAGE="com.muh.arifandi.dicoding"
# Konversi titik ke slash untuk path direktori
PACKAGE_PATH=${BASE_PACKAGE//.//}/features/${FEATURE_NAME}

FEATURE_PACKAGE="${BASE_PACKAGE}.features.${FEATURE_NAME}"
FEATURE_DIR="features/${FEATURE_NAME}"
MODULE_NAME_API="features:${FEATURE_NAME}:api"
MODULE_NAME_IMPL="features:${FEATURE_NAME}:impl"

echo "🚀 Memulai pembuatan fitur: ${FEATURE_NAME}..."

# 1. Buat struktur folder
mkdir -p "${FEATURE_DIR}/api/src/main/java/${PACKAGE_PATH}/api"
mkdir -p "${FEATURE_DIR}/impl/src/main/java/${PACKAGE_PATH}/data/network"
mkdir -p "${FEATURE_DIR}/impl/src/main/java/${PACKAGE_PATH}/data/repository"
mkdir -p "${FEATURE_DIR}/impl/src/main/java/${PACKAGE_PATH}/data/mapper"
mkdir -p "${FEATURE_DIR}/impl/src/main/java/${PACKAGE_PATH}/domain/model"
mkdir -p "${FEATURE_DIR}/impl/src/main/java/${PACKAGE_PATH}/domain/usecase"
mkdir -p "${FEATURE_DIR}/impl/src/main/java/${PACKAGE_PATH}/domain/repository"
mkdir -p "${FEATURE_DIR}/impl/src/main/java/${PACKAGE_PATH}/ui/state"
mkdir -p "${FEATURE_DIR}/impl/src/main/java/${PACKAGE_PATH}/di"
mkdir -p "${FEATURE_DIR}/impl/src/main/java/${PACKAGE_PATH}/navigation"

# Fungsi untuk membuat Header
create_header() {
    local file_name=$1
    local module_name=$2
    cat <<EOF
/**
 * Created by ${AUTHOR} on ${CURRENT_DATE}
 * Email : ${EMAIL}
 * Project : ${PROJECT}
 * Module : ${module_name}
 * File : ${file_name}
 */
EOF
}

# 2. Buat build.gradle.kts untuk API
cat <<EOF > "${FEATURE_DIR}/api/build.gradle.kts"
plugins {
    id("myapp.kotlin.library")
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
}
EOF

# 3. Buat build.gradle.kts untuk IMPL
cat <<EOF > "${FEATURE_DIR}/impl/build.gradle.kts"
plugins {
    id("myapp.android.feature")
    id("myapp.android.hilt")
}

android {
    namespace = "${FEATURE_PACKAGE}"
}

dependencies {
    implementation(project(":features:${FEATURE_NAME}:api"))
    implementation(project(":core:architecture"))
    implementation(project(":core:ui"))
    implementation(project(":core:network"))
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(project(":navigation"))
}
EOF

# 4. Buat file MVI State
CLASS_NAME_PREFIX="$(tr '[:lower:]' '[:upper:]' <<< ${FEATURE_NAME:0:1})${FEATURE_NAME:1}"

# State
{
    create_header "${CLASS_NAME_PREFIX}State.kt" "${MODULE_NAME_IMPL}"
    cat <<EOF
package ${FEATURE_PACKAGE}.ui.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiState
import androidx.compose.runtime.Immutable

@Immutable
data class ${CLASS_NAME_PREFIX}State(
    val isLoading: Boolean = false,
    val data: String? = null
) : UiState
EOF
} > "${FEATURE_DIR}/impl/src/main/java/${PACKAGE_PATH}/ui/state/${CLASS_NAME_PREFIX}State.kt"

# Intent
{
    create_header "${CLASS_NAME_PREFIX}Intent.kt" "${MODULE_NAME_IMPL}"
    cat <<EOF
package ${FEATURE_PACKAGE}.ui.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiIntent

sealed interface ${CLASS_NAME_PREFIX}Intent : UiIntent {
    data object LoadInitialData : ${CLASS_NAME_PREFIX}Intent
}
EOF
} > "${FEATURE_DIR}/impl/src/main/java/${PACKAGE_PATH}/ui/state/${CLASS_NAME_PREFIX}Intent.kt"

# Effect
{
    create_header "${CLASS_NAME_PREFIX}Effect.kt" "${MODULE_NAME_IMPL}"
    cat <<EOF
package ${FEATURE_PACKAGE}.ui.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiEffect

sealed interface ${CLASS_NAME_PREFIX}Effect : UiEffect {
    data class ShowError(val message: String) : ${CLASS_NAME_PREFIX}Effect
}
EOF
} > "${FEATURE_DIR}/impl/src/main/java/${PACKAGE_PATH}/ui/state/${CLASS_NAME_PREFIX}Effect.kt"

# 5. Buat Destinations di API
{
    create_header "${CLASS_NAME_PREFIX}Destinations.kt" "${MODULE_NAME_API}"
    cat <<EOF
package ${FEATURE_PACKAGE}.api

import kotlinx.serialization.Serializable

@Serializable
data object ${CLASS_NAME_PREFIX}Destinations
EOF
} > "${FEATURE_DIR}/api/src/main/java/${PACKAGE_PATH}/api/${CLASS_NAME_PREFIX}Destinations.kt"

# 6. Buat FeatureApiImpl di IMPL
{
    create_header "${CLASS_NAME_PREFIX}FeatureApiImpl.kt" "${MODULE_NAME_IMPL}"
    cat <<EOF
package ${FEATURE_PACKAGE}.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.muh.arifandi.dicoding.core.architecture.navigation.FeatureApi
import ${FEATURE_PACKAGE}.api.${CLASS_NAME_PREFIX}Destinations
import ${FEATURE_PACKAGE}.ui.${CLASS_NAME_PREFIX}Screen
import javax.inject.Inject

class ${CLASS_NAME_PREFIX}FeatureApiImpl @Inject constructor() : FeatureApi {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController
    ) {
        navGraphBuilder.composable<${CLASS_NAME_PREFIX}Destinations> {
            ${CLASS_NAME_PREFIX}Screen()
        }
    }
}
EOF
} > "${FEATURE_DIR}/impl/src/main/java/${PACKAGE_PATH}/navigation/${CLASS_NAME_PREFIX}FeatureApiImpl.kt"

# 7. Buat DI Module untuk Navigation
{
    create_header "NavigationModule.kt" "${MODULE_NAME_IMPL}"
    cat <<EOF
package ${FEATURE_PACKAGE}.di

import com.muh.arifandi.dicoding.core.architecture.navigation.FeatureApi
import ${FEATURE_PACKAGE}.navigation.${CLASS_NAME_PREFIX}FeatureApiImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {
    @Binds
    @IntoSet
    @Singleton
    fun bind${CLASS_NAME_PREFIX}FeatureApi(impl: ${CLASS_NAME_PREFIX}FeatureApiImpl): FeatureApi
}
EOF
} > "${FEATURE_DIR}/impl/src/main/java/${PACKAGE_PATH}/di/NavigationModule.kt"

# 8. Buat ViewModel
{
    create_header "${CLASS_NAME_PREFIX}ViewModel.kt" "${MODULE_NAME_IMPL}"
    cat <<EOF
package ${FEATURE_PACKAGE}.ui

import com.muh.arifandi.dicoding.core.architecture.mvi.BaseViewModel
import ${FEATURE_PACKAGE}.ui.state.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ${CLASS_NAME_PREFIX}ViewModel @Inject constructor() :
    BaseViewModel<${CLASS_NAME_PREFIX}State, ${CLASS_NAME_PREFIX}Intent, ${CLASS_NAME_PREFIX}Effect>(${CLASS_NAME_PREFIX}State()) {

    override fun processIntent(intent: ${CLASS_NAME_PREFIX}Intent) {
        when (intent) {
            is ${CLASS_NAME_PREFIX}Intent.LoadInitialData -> { /* Logic */ }
        }
    }
}
EOF
} > "${FEATURE_DIR}/impl/src/main/java/${PACKAGE_PATH}/ui/${CLASS_NAME_PREFIX}ViewModel.kt"

# 9. Buat Screen dengan SakaScaffold
{
    create_header "${CLASS_NAME_PREFIX}Screen.kt" "${MODULE_NAME_IMPL}"
    cat <<EOF
package ${FEATURE_PACKAGE}.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaLoadingView
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaScaffold
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.MyApplicationTheme
import ${FEATURE_PACKAGE}.ui.state.${CLASS_NAME_PREFIX}State
import ${FEATURE_PACKAGE}.ui.state.${CLASS_NAME_PREFIX}Intent

@Composable
fun ${CLASS_NAME_PREFIX}Screen(
    viewModel: ${CLASS_NAME_PREFIX}ViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ${CLASS_NAME_PREFIX}Content(
        state = state,
        onIntent = { viewModel.processIntent(it) }
    )
}

@Composable
internal fun ${CLASS_NAME_PREFIX}Content(
    state: ${CLASS_NAME_PREFIX}State,
    onIntent: (${CLASS_NAME_PREFIX}Intent) -> Unit,
    modifier: Modifier = Modifier
) {
    SakaScaffold(
        modifier = modifier.fillMaxSize()
    ) { paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (state.isLoading) {
                SakaLoadingView()
            } else {
                Text(text = "Welcome to ${CLASS_NAME_PREFIX} Screen")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ${CLASS_NAME_PREFIX}ScreenPreview() {
    MyApplicationTheme {
        ${CLASS_NAME_PREFIX}Content(
            state = ${CLASS_NAME_PREFIX}State(
                isLoading = false,
                data = "Preview Data"
            ),
            onIntent = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ${CLASS_NAME_PREFIX}ScreenLoadingPreview() {
    MyApplicationTheme {
        ${CLASS_NAME_PREFIX}Content(
            state = ${CLASS_NAME_PREFIX}State(
                isLoading = true
            ),
            onIntent = {}
        )
    }
}
EOF
} > "${FEATURE_DIR}/impl/src/main/java/${PACKAGE_PATH}/ui/${CLASS_NAME_PREFIX}Screen.kt"

# 10. Daftarkan di settings.gradle.kts
if ! grep -q ":features:${FEATURE_NAME}:api" settings.gradle.kts; then
    echo "include(\":features:${FEATURE_NAME}:api\")" >> settings.gradle.kts
fi
if ! grep -q ":features:${FEATURE_NAME}:impl" settings.gradle.kts; then
    echo "include(\":features:${FEATURE_NAME}:impl\")" >> settings.gradle.kts
fi

echo "✅ Fitur ${FEATURE_NAME} berhasil dibuat dengan standar SakaScaffold & Navigation!"
echo "⚠️  Langkah selanjutnya:"
echo "1. Lakukan 'Gradle Sync'."
echo "2. Daftarkan project(\":features:${FEATURE_NAME}:api\") di navigation/build.gradle.kts."
echo "3. Gunakan ${CLASS_NAME_PREFIX}Destinations untuk navigasi."
