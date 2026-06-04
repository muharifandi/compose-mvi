/**
 * Created by Muh. Arifandi on 25/05/2026
 * Email : arif76440@gmail.com
 * Project : SakaAndroid
 * Module : features:master:impl
 * File : HomeViewModel.kt
 */
package com.muh.arifandi.dicoding.features.master.ui.home

import androidx.lifecycle.viewModelScope
import com.muh.arifandi.dicoding.core.architecture.mvi.BaseViewModel
import com.muh.arifandi.dicoding.features.master.domain.usecase.GetCreditCardsUseCase
import com.muh.arifandi.dicoding.features.master.domain.usecase.GetMenuItemsUseCase
import com.muh.arifandi.dicoding.features.master.ui.home.state.HomeEffect
import com.muh.arifandi.dicoding.features.master.ui.home.state.HomeIntent
import com.muh.arifandi.dicoding.features.master.ui.home.state.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCreditCardsUseCase: GetCreditCardsUseCase,
    private val getMenuItemsUseCase: GetMenuItemsUseCase
) : BaseViewModel<HomeState, HomeIntent, HomeEffect>(HomeState()) {

    init {
        processIntent(HomeIntent.LoadHomeData)
    }

    override fun processIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.LoadHomeData -> loadInitialData()
            is HomeIntent.SelectCard -> {
                setState { copy(selectedCardIndex = intent.index) }
                updateMenuItems(intent.index)
            }
            is HomeIntent.ToggleDataVisibility -> {
                setState { copy(isDataVisible = !isDataVisible) }
            }
            is HomeIntent.NavigateToTransfer -> {
                sendEffect { HomeEffect.NavigateToTransfer }
            }
        }
    }

    private fun loadInitialData() {
        combine(
            getCreditCardsUseCase(),
            getMenuItemsUseCase(state.value.selectedCardIndex)
        ) { cards, menus ->
            setState {
                copy(
                    creditCards = cards,
                    homeMenuItems = menus
                )
            }
        }.launchIn(viewModelScope)
    }

    private fun updateMenuItems(cardIndex: Int) {
        getMenuItemsUseCase(cardIndex)
            .onEach { menus ->
                setState { copy(homeMenuItems = menus) }
            }
            .launchIn(viewModelScope)
    }
}
