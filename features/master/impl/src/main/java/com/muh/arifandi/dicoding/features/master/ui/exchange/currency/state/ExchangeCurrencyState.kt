package com.muh.arifandi.dicoding.features.master.ui.exchange.currency.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiState
import javax.annotation.concurrent.Immutable

@Immutable
data class ExchangeCurrencyState(
    val fromAmount: String = "",
    val toAmount: String = "",
    val fromCurrency: CurrencyModel = CurrencyModel("USD", "Dollar"),
    val toCurrency: CurrencyModel = CurrencyModel("KRW", "South Korean Won"),
    val isFromCurrencyPickerOpen: Boolean = false,
    val isToCurrencyPickerOpen: Boolean = false,
    val currencyRate: Double = 1122.0,
    val availableCurrencies: List<CurrencyModel> = listOf(
        CurrencyModel("VND", "Viet Nam Dong"),
        CurrencyModel("HK$", "Hong Kong Dollar"),
        CurrencyModel("USD", "Dollar"),
        CurrencyModel("NT$", "Taiwan Dollar"),
        CurrencyModel("J$", "Jamaika Dollar"),
        CurrencyModel("KRW", "South Korean Won")
    )
) : UiState

data class CurrencyModel(
    val code: String,
    val name: String
)
