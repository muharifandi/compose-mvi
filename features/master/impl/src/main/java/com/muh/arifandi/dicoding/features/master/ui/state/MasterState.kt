/**
 * Created by Muh. Arifandi on 25/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : features:master:impl
 * File : MasterState.kt
 *
 * Description:
 * Definisi state untuk layar Master, termasuk tab yang sedang aktif.
 */
package com.muh.arifandi.dicoding.features.master.ui.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiState
import androidx.compose.runtime.Immutable

@Immutable
enum class MasterTab {
    HOME, SEARCH, MESSAGE, SETTINGS
}

@Immutable
data class MasterState(
    val selectedTab: MasterTab = MasterTab.HOME,
    val isLoading: Boolean = false,
    val data: String? = null
) : UiState
