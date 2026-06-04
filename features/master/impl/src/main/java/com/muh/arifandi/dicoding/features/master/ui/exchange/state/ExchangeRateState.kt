package com.muh.arifandi.dicoding.features.master.ui.exchange.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiState
import com.muh.arifandi.dicoding.features.master.domain.model.ExchangeRateItem
import javax.annotation.concurrent.Immutable

@Immutable
data class ExchangeRateState(
    val isLoading: Boolean = false,
    val rates: List<ExchangeRateItem> = emptyList()
) : UiState

