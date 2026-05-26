/**
 * Created by Muh. Arifandi on 25/05/2026
 * Email : arif76440@gmail.com
 * Project : SakaAndroid
 * Module : features:master:impl
 * File : MasterViewModel.kt
 */
package com.muh.arifandi.dicoding.features.master.ui

import androidx.lifecycle.viewModelScope
import com.muh.arifandi.dicoding.core.architecture.mvi.BaseViewModel
import com.muh.arifandi.dicoding.features.master.domain.usecase.GetCreditCardsUseCase
import com.muh.arifandi.dicoding.features.master.domain.usecase.GetMenuItemsUseCase
import com.muh.arifandi.dicoding.features.master.ui.state.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MasterViewModel @Inject constructor(
    private val getCreditCardsUseCase: GetCreditCardsUseCase,
    private val getMenuItemsUseCase: GetMenuItemsUseCase
) : BaseViewModel<MasterState, MasterIntent, MasterEffect>(MasterState()) {

    init {
        processIntent(MasterIntent.LoadInitialData)
    }

    override fun processIntent(intent: MasterIntent) {
        when (intent) {
            is MasterIntent.LoadInitialData -> {
                loadInitialData()
            }
            is MasterIntent.SelectTab -> {
                setState { copy(selectedTab = intent.tab) }
            }
            is MasterIntent.SelectCard -> {
                setState { copy(selectedCardIndex = intent.index) }
                updateMenuItems(intent.index)
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
