package com.muh.arifandi.dicoding.features.master.ui.home.state

import androidx.compose.runtime.Immutable
import com.muh.arifandi.dicoding.core.architecture.mvi.UiState
import com.muh.arifandi.dicoding.features.master.domain.model.CreditCardInfo
import com.muh.arifandi.dicoding.features.master.domain.model.MasterMenuItem

@Immutable
data class HomeState(
    val isLoading: Boolean = false,
    val creditCards: List<CreditCardInfo> = emptyList(),
    val homeMenuItems: List<MasterMenuItem> = emptyList(),
    val selectedCardIndex: Int = 0,
    val isDataVisible: Boolean = false
) : UiState
