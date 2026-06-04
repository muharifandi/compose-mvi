package com.muh.arifandi.dicoding.features.master.ui.interest.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiIntent

sealed interface InterestRateIntent : UiIntent {
    data object LoadRates : InterestRateIntent
}
