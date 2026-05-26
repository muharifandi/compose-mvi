/**
 * Created by Muh. Arifandi on 25/05/26.
 * Email : arif76440@gmail.com
 * Project: SakaAndroid
 * File: OnboardingViewModel
 */
package com.muh.arifandi.dicoding.features.onboarding.ui

import com.muh.arifandi.dicoding.core.architecture.mvi.BaseViewModel
import com.muh.arifandi.dicoding.features.onboarding.domain.usecase.GetOnboardingPagesUseCase
import com.muh.arifandi.dicoding.features.onboarding.ui.state.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val getOnboardingPagesUseCase: GetOnboardingPagesUseCase
) : BaseViewModel<OnboardingState, OnboardingIntent, OnboardingEffect>(
    OnboardingState(items = getOnboardingPagesUseCase())
) {

    override fun processIntent(intent: OnboardingIntent) {
        when (intent) {
            is OnboardingIntent.NextPage -> {
                val next = state.value.currentPage + 1
                if (next < state.value.items.size) {
                    setState { copy(currentPage = next) }
                }
            }
            is OnboardingIntent.GetStarted -> {
                sendEffect { OnboardingEffect.NavigateToLogin }
            }
            else -> {}
        }
    }
}
