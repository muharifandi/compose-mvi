package com.muh.arifandi.dicoding.features.onboarding.ui

import com.muh.arifandi.dicoding.core.architecture.mvi.BaseViewModel
import com.muh.arifandi.dicoding.features.onboarding.domain.repository.OnboardingRepository
import com.muh.arifandi.dicoding.features.onboarding.ui.state.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val repository: OnboardingRepository // Inject Repository di sini
) : BaseViewModel<OnboardingState, OnboardingIntent, OnboardingEffect>(OnboardingState()) {

    override fun processIntent(intent: OnboardingIntent) {
        when (intent) {
            is OnboardingIntent.LoadPages -> {
                // Ambil data dari repository
                val pages = repository.getOnboardingPages()
                setState { copy(items = pages) }
            }
            is OnboardingIntent.NextPage -> {
                val next = state.value.currentPage + 1
                if (next < state.value.items.size) {
                    setState { copy(currentPage = next) }
                }
            }
            else -> {}
        }
    }
}
