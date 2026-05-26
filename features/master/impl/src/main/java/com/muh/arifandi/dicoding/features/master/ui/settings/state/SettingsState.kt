/**
 * Created by Muh. Arifandi on 25/05/26.
 * Email : arif76440@gmail.com
 * Project: SakaAndroid
 * File: SettingsState
 */
package com.muh.arifandi.dicoding.features.master.ui.settings.state

import androidx.compose.runtime.Immutable
import com.muh.arifandi.dicoding.core.architecture.mvi.UiState

@Immutable
data class SettingsState(
    val isDarkMode: Boolean = false,
    val language: String = "English"
) : UiState
