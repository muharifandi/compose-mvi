/**
 * Created by Muh. Arifandi on 25/05/26.
 * Email : arif76440@gmail.com
 * Project: SakaAndroid
 * File: SettingsEffect
 */
package com.muh.arifandi.dicoding.features.master.ui.settings.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiEffect

sealed interface SettingsEffect : UiEffect {
    data object RestartApp : SettingsEffect
}
