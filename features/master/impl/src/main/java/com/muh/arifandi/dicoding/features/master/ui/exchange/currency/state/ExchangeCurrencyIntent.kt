package com.muh.arifandi.dicoding.features.master.ui.exchange.currency.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiIntent

sealed interface ExchangeCurrencyIntent : UiIntent {
    data class FromAmountChanged(val amount: String) : ExchangeCurrencyIntent
    data object ToggleFromCurrencyPicker : ExchangeCurrencyIntent
    data object ToggleToCurrencyPicker : ExchangeCurrencyIntent
    data class SelectFromCurrency(val currency: CurrencyModel) : ExchangeCurrencyIntent
    data class SelectToCurrency(val currency: CurrencyModel) : ExchangeCurrencyIntent
    data object SwapCurrencies : ExchangeCurrencyIntent
    data object SubmitExchange : ExchangeCurrencyIntent
}
