package com.muh.arifandi.dicoding.features.master.ui.exchange.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiIntent

sealed interface ExchangeRateIntent : UiIntent {
    data object LoadRates : ExchangeRateIntent
}
