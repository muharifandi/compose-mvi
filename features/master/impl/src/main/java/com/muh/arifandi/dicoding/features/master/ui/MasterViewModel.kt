/**
 * Created by Muh. Arifandi on 25/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : features:master:impl
 * File : MasterViewModel.kt
 *
 * Description:
 * ViewModel untuk mengelola state navigasi tab dan data pada layar Master.
 */
package com.muh.arifandi.dicoding.features.master.ui

import com.muh.arifandi.dicoding.core.architecture.mvi.BaseViewModel
import com.muh.arifandi.dicoding.features.master.ui.state.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MasterViewModel @Inject constructor() :
    BaseViewModel<MasterState, MasterIntent, MasterEffect>(MasterState()) {

    override fun processIntent(intent: MasterIntent) {
        when (intent) {
            is MasterIntent.LoadInitialData -> { /* Logic */ }
            is MasterIntent.SelectTab -> {
                setState { copy(selectedTab = intent.tab) }
            }
        }
    }
}
