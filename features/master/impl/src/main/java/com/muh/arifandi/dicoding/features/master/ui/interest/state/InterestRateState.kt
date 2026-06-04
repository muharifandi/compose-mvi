package com.muh.arifandi.dicoding.features.master.ui.interest.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiState
import com.muh.arifandi.dicoding.features.master.domain.model.InterestRateItem
import javax.annotation.concurrent.Immutable

@Immutable
data class InterestRateState(
    val isLoading: Boolean = false,
    val rates: List<InterestRateItem> = emptyList()
) : UiState

