package com.muh.arifandi.dicoding.features.onboarding.ui

import com.muh.arifandi.dicoding.core.architecture.mvi.BaseViewModel
import com.muh.arifandi.dicoding.features.onboarding.ui.state.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor() :
    BaseViewModel<OnboardingState, OnboardingIntent, OnboardingEffect>(OnboardingState()) {

    override fun processIntent(intent: OnboardingIntent) {
        when (intent) {
            is OnboardingIntent.LoadInitialData -> { /* Logic */ }
        }
    }
}
