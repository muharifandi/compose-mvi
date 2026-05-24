/**
 * Created by Muh. Arifandi on 25/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : features:master:impl
 * File : MasterEffect.kt
 *
 * Description:
 * Definisi side effects untuk layar Master.
 */
package com.muh.arifandi.dicoding.features.master.ui.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiEffect

sealed interface MasterEffect : UiEffect {
    data class ShowError(val message: String) : MasterEffect
}
