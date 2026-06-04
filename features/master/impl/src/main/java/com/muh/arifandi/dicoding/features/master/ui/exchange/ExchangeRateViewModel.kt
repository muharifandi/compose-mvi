package com.muh.arifandi.dicoding.features.master.ui.exchange

import com.muh.arifandi.dicoding.core.architecture.mvi.BaseViewModel
import com.muh.arifandi.dicoding.features.master.domain.model.ExchangeRateItem
import com.muh.arifandi.dicoding.features.master.ui.exchange.state.ExchangeRateEffect
import com.muh.arifandi.dicoding.features.master.ui.exchange.state.ExchangeRateIntent
import com.muh.arifandi.dicoding.features.master.ui.exchange.state.ExchangeRateState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExchangeRateViewModel @Inject constructor() : 
    BaseViewModel<ExchangeRateState, ExchangeRateIntent, ExchangeRateEffect>(ExchangeRateState()) {

    init {
        processIntent(ExchangeRateIntent.LoadRates)
    }

    override fun processIntent(intent: ExchangeRateIntent) {
        when (intent) {
            is ExchangeRateIntent.LoadRates -> loadRates()
        }
    }

    private fun loadRates() {
        setState { copy(isLoading = true) }
        
        // Mock data based on design
        val mockRates = listOf(
            ExchangeRateItem("Vietnam", "https://flagcdn.com/w320/vn.png", "1.403", "1.746"),
            ExchangeRateItem("Nicaragua", "https://flagcdn.com/w320/ni.png", "9.123", "12.09"),
            ExchangeRateItem("Korea", "https://flagcdn.com/w320/kr.png", "3.704", "5.151"),
            ExchangeRateItem("Russia", "https://flagcdn.com/w320/ru.png", "116.0", "144.4"),
            ExchangeRateItem("China", "https://flagcdn.com/w320/cn.png", "1.725", "2.234"),
            ExchangeRateItem("Portuguese", "https://flagcdn.com/w320/pt.png", "1.403", "1.746"),
            ExchangeRateItem("Korea", "https://flagcdn.com/w320/kr.png", "3.454", "4.312"),
            ExchangeRateItem("French", "https://flagcdn.com/w320/fr.png", "23.45", "34.56"),
            ExchangeRateItem("Nicaragua", "https://flagcdn.com/w320/ni.png", "263.1", "300.3"),
            ExchangeRateItem("China", "https://flagcdn.com/w320/cn.png", "1.725", "2.234")
        )

        setState { 
            copy(
                isLoading = false,
                rates = mockRates
            )
        }
    }
}
