package com.muh.arifandi.dicoding.features.master.ui.exchange.currency.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiEffect

sealed interface ExchangeCurrencyEffect : UiEffect {
    data object ShowSuccess : ExchangeCurrencyEffect
}
