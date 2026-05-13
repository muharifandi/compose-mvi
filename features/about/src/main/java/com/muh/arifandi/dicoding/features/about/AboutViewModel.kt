package com.muh.arifandi.dicoding.features.about

import com.muh.arifandi.dicoding.core.common.mvi.BaseViewModel
import com.muh.arifandi.dicoding.core.common.navigation.Navigator
import com.muh.arifandi.dicoding.features.about.state.AboutEffect
import com.muh.arifandi.dicoding.features.about.state.AboutIntent
import com.muh.arifandi.dicoding.features.about.state.AboutState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AboutViewModel @Inject constructor(
    private val navigator: Navigator
) : BaseViewModel<AboutState, AboutIntent, AboutEffect>(AboutState()) {

    override fun processIntent(intent: AboutIntent) {
        when (intent) {
            is AboutIntent.Back -> navigator.navigateBack()
        }
    }
}
