package com.muh.arifandi.dicoding.features.master.ui.interest

import com.muh.arifandi.dicoding.core.architecture.mvi.BaseViewModel
import com.muh.arifandi.dicoding.features.master.domain.model.InterestRateItem
import com.muh.arifandi.dicoding.features.master.ui.interest.state.InterestRateEffect
import com.muh.arifandi.dicoding.features.master.ui.interest.state.InterestRateIntent
import com.muh.arifandi.dicoding.features.master.ui.interest.state.InterestRateState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InterestRateViewModel @Inject constructor() : 
    BaseViewModel<InterestRateState, InterestRateIntent, InterestRateEffect>(InterestRateState()) {

    init {
        processIntent(InterestRateIntent.LoadRates)
    }

    override fun processIntent(intent: InterestRateIntent) {
        when (intent) {
            is InterestRateIntent.LoadRates -> loadRates()
        }
    }

    private fun loadRates() {
        setState { copy(isLoading = true) }
        
        // Mock data based on design
        val mockRates = listOf(
            InterestRateItem("Individual customers", "1m", "4.50%", isPrimary = true),
            InterestRateItem("Corporate customers", "2m", "5.50%"),
            InterestRateItem("Individual customers", "1m", "4.50%", isPrimary = true),
            InterestRateItem("Corporate customers", "6m", "2.50%"),
            InterestRateItem("Individual customers", "1m", "4.50%", isPrimary = true),
            InterestRateItem("Corporate customers", "8m", "6.50%"),
            InterestRateItem("Individual customers", "1m", "4.50%", isPrimary = true),
            InterestRateItem("Individual customers", "1m", "4.50%", isPrimary = true),
            InterestRateItem("Corporate customers", "7m", "6.80%"),
            InterestRateItem("Individual customers", "1m", "4.50%", isPrimary = true),
            InterestRateItem("Individual customers", "12m", "5.90%", isPrimary = true),
            InterestRateItem("Individual customers", "1m", "4.50%", isPrimary = true),
            InterestRateItem("Individual customers", "1m", "4.50%", isPrimary = true),
            InterestRateItem("Individual customers", "1m", "4.50%", isPrimary = true),
            InterestRateItem("Individual customers", "1m", "4.50%", isPrimary = true),
            InterestRateItem("Individual customers", "1m", "4.50%", isPrimary = true),
            InterestRateItem("Individual customers", "1m", "4.50%", isPrimary = true),
            InterestRateItem("Individual customers", "1m", "4.50%", isPrimary = true)
        )

        setState { 
            copy(
                isLoading = false,
                rates = mockRates
            )
        }
    }
}
