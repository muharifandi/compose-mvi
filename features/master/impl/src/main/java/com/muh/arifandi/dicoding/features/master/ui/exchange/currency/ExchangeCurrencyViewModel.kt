package com.muh.arifandi.dicoding.features.master.ui.exchange.currency

import com.muh.arifandi.dicoding.core.architecture.mvi.BaseViewModel
import com.muh.arifandi.dicoding.features.master.ui.exchange.currency.state.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExchangeCurrencyViewModel @Inject constructor() : 
    BaseViewModel<ExchangeCurrencyState, ExchangeCurrencyIntent, ExchangeCurrencyEffect>(ExchangeCurrencyState()) {

    override fun processIntent(intent: ExchangeCurrencyIntent) {
        when (intent) {
            is ExchangeCurrencyIntent.FromAmountChanged -> handleAmountChange(intent.amount)
            is ExchangeCurrencyIntent.ToggleFromCurrencyPicker -> setState { copy(isFromCurrencyPickerOpen = !isFromCurrencyPickerOpen) }
            is ExchangeCurrencyIntent.ToggleToCurrencyPicker -> setState { copy(isToCurrencyPickerOpen = !isToCurrencyPickerOpen) }
            is ExchangeCurrencyIntent.SelectFromCurrency -> setState { copy(fromCurrency = intent.currency, isFromCurrencyPickerOpen = false) }
            is ExchangeCurrencyIntent.SelectToCurrency -> setState { copy(toCurrency = intent.currency, isToCurrencyPickerOpen = false) }
            is ExchangeCurrencyIntent.SwapCurrencies -> swapCurrencies()
            is ExchangeCurrencyIntent.SubmitExchange -> sendEffect { ExchangeCurrencyEffect.ShowSuccess }
        }
    }

    private fun handleAmountChange(amount: String) {
        val calculatedAmount = if (amount.isEmpty()) "" else {
            try {
                (amount.toDouble() * state.value.currencyRate).toString()
            } catch (_: Exception) {
                ""
            }
        }
        setState { copy(fromAmount = amount, toAmount = calculatedAmount) }
    }

    private fun swapCurrencies() {
        setState { 
            copy(
                fromCurrency = toCurrency,
                toCurrency = fromCurrency,
                fromAmount = "",
                toAmount = ""
            )
        }
    }
}
