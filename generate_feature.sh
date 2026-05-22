#!/bin/bash

# Saka Feature Generator (Revised with Namespace fix)
# Usage: ./generate_feature.sh <feature_name>

FEATURE_NAME=$1

if [ -z "$FEATURE_NAME" ]; then
    echo "Error: Nama fitur harus diisi. Contoh: ./generate_feature.sh login"
    exit 1
fi

# Nama paket dasar
BASE_PACKAGE="com.muh.arifandi.dicoding"
FEATURE_PACKAGE="${BASE_PACKAGE}.features.${FEATURE_NAME}"
FEATURE_DIR="features/${FEATURE_NAME}"

echo "🚀 Memulai pembuatan fitur: ${FEATURE_NAME}..."

# 1. Buat struktur folder
mkdir -p "${FEATURE_DIR}/api/src/main/java/${BASE_PACKAGE}/features/${FEATURE_NAME}"
mkdir -p "${FEATURE_DIR}/impl/src/main/java/${BASE_PACKAGE}/features/${FEATURE_NAME}/data/network"
mkdir -p "${FEATURE_DIR}/impl/src/main/java/${BASE_PACKAGE}/features/${FEATURE_NAME}/data/repository"
mkdir -p "${FEATURE_DIR}/impl/src/main/java/${BASE_PACKAGE}/features/${FEATURE_NAME}/data/mapper"
mkdir -p "${FEATURE_DIR}/impl/src/main/java/${BASE_PACKAGE}/features/${FEATURE_NAME}/domain/model"
mkdir -p "${FEATURE_DIR}/impl/src/main/java/${BASE_PACKAGE}/features/${FEATURE_NAME}/domain/usecase"
mkdir -p "${FEATURE_DIR}/impl/src/main/java/${BASE_PACKAGE}/features/${FEATURE_NAME}/domain/repository"
mkdir -p "${FEATURE_DIR}/impl/src/main/java/${BASE_PACKAGE}/features/${FEATURE_NAME}/ui/state"
mkdir -p "${FEATURE_DIR}/impl/src/main/java/${BASE_PACKAGE}/features/${FEATURE_NAME}/di"
mkdir -p "${FEATURE_DIR}/impl/src/main/java/${BASE_PACKAGE}/features/${FEATURE_NAME}/navigation"

# 2. Buat build.gradle.kts untuk API
cat <<EOF > "${FEATURE_DIR}/api/build.gradle.kts"
plugins {
    id("myapp.kotlin.library")
}

dependencies {
}
EOF

# 3. Buat build.gradle.kts untuk IMPL (Fix: Added Namespace)
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

# 4. Buat file MVI State (Dipisah per file)
CLASS_NAME_PREFIX="$(tr '[:lower:]' '[:upper:]' <<< ${FEATURE_NAME:0:1})${FEATURE_NAME:1}"

# State
cat <<EOF > "${FEATURE_DIR}/impl/src/main/java/${BASE_PACKAGE}/features/${FEATURE_NAME}/ui/state/${CLASS_NAME_PREFIX}State.kt"
package ${FEATURE_PACKAGE}.ui.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiState
import androidx.compose.runtime.Immutable

@Immutable
data class ${CLASS_NAME_PREFIX}State(
    val isLoading: Boolean = false,
    val data: String? = null
) : UiState
EOF

# Intent
cat <<EOF > "${FEATURE_DIR}/impl/src/main/java/${BASE_PACKAGE}/features/${FEATURE_NAME}/ui/state/${CLASS_NAME_PREFIX}Intent.kt"
package ${FEATURE_PACKAGE}.ui.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiIntent

sealed interface ${CLASS_NAME_PREFIX}Intent : UiIntent {
    data object LoadInitialData : ${CLASS_NAME_PREFIX}Intent
}
EOF

# Effect
cat <<EOF > "${FEATURE_DIR}/impl/src/main/java/${BASE_PACKAGE}/features/${FEATURE_NAME}/ui/state/${CLASS_NAME_PREFIX}Effect.kt"
package ${FEATURE_PACKAGE}.ui.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiEffect

sealed interface ${CLASS_NAME_PREFIX}Effect : UiEffect {
    data class ShowError(val message: String) : ${CLASS_NAME_PREFIX}Effect
}
EOF

# 5. Buat ViewModel
cat <<EOF > "${FEATURE_DIR}/impl/src/main/java/${BASE_PACKAGE}/features/${FEATURE_NAME}/ui/${CLASS_NAME_PREFIX}ViewModel.kt"
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

# 6. Buat Screen dengan Preview
cat <<EOF > "${FEATURE_DIR}/impl/src/main/java/${BASE_PACKAGE}/features/${FEATURE_NAME}/ui/${CLASS_NAME_PREFIX}Screen.kt"
package ${FEATURE_PACKAGE}.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.muh.arifandi.dicoding.core.ui.designsystem.components.SakaLoadingView
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.MyApplicationTheme
import ${FEATURE_PACKAGE}.ui.state.${CLASS_NAME_PREFIX}State

@Composable
fun ${CLASS_NAME_PREFIX}Screen(
    viewModel: ${CLASS_NAME_PREFIX}ViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ${CLASS_NAME_PREFIX}Content(state = state)
}

@Composable
internal fun ${CLASS_NAME_PREFIX}Content(
    state: ${CLASS_NAME_PREFIX}State,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            SakaLoadingView()
        } else {
            Text(text = "Welcome to ${CLASS_NAME_PREFIX} Screen")
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
            )
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
            )
        )
    }
}
EOF

# 7. Daftarkan di settings.gradle.kts (Hanya jika belum ada)
if ! grep -q ":features:${FEATURE_NAME}:api" settings.gradle.kts; then
    echo "include(\":features:${FEATURE_NAME}:api\")" >> settings.gradle.kts
fi
if ! grep -q ":features:${FEATURE_NAME}:impl" settings.gradle.kts; then
    echo "include(\":features:${FEATURE_NAME}:impl\")" >> settings.gradle.kts
fi

echo "✅ Fitur ${FEATURE_NAME} berhasil dibuat dengan Namespace yang benar!"
echo "Saran: Lakukan 'Gradle Sync' sekarang."
