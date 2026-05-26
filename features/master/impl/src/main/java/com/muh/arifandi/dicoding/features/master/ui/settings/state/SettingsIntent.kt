/**
 * Created by Muh. Arifandi on 25/05/26.
 * Email : arif76440@gmail.com
 * Project: SakaAndroid
 * File: SettingsIntent
 */
package com.muh.arifandi.dicoding.features.master.ui.settings.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiIntent

sealed interface SettingsIntent : UiIntent {
    data class ToggleDarkMode(val enabled: Boolean) : SettingsIntent
}
