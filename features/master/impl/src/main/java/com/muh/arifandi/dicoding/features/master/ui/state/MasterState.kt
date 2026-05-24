/**
 * Created by Muh. Arifandi on 25/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : features:master:impl
 * File : MasterState.kt
 */
package com.muh.arifandi.dicoding.features.master.ui.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiState
import androidx.compose.runtime.Immutable
import com.muh.arifandi.dicoding.features.master.domain.model.MasterMenuItem
import com.muh.arifandi.dicoding.features.master.domain.model.CreditCardInfo

@Immutable
enum class MasterTab {
    HOME, SEARCH, MESSAGE, SETTINGS
}

@Immutable
data class MasterState(
    val selectedTab: MasterTab = MasterTab.HOME,
    val isLoading: Boolean = false,
    val data: String? = null,
    val homeMenuItems: List<MasterMenuItem> = emptyList(),
    val creditCards: List<CreditCardInfo> = emptyList(),
    val selectedCardIndex: Int = 0
) : UiState
