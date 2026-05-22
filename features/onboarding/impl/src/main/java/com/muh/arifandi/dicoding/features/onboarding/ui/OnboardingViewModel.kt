package com.muh.arifandi.dicoding.features.onboarding.ui

import com.muh.arifandi.dicoding.core.architecture.mvi.BaseViewModel
import com.muh.arifandi.dicoding.features.onboarding.domain.repository.OnboardingRepository
import com.muh.arifandi.dicoding.features.onboarding.ui.state.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val repository: OnboardingRepository // Inject Repository di sini
) : BaseViewModel<OnboardingState, OnboardingIntent, OnboardingEffect>(
    OnboardingState(items = repository.getOnboardingPages())
) {

    override fun processIntent(intent: OnboardingIntent) {
        when (intent) {
            is OnboardingIntent.LoadPages -> {
                // Data sudah dimuat saat inisialisasi, fungsi ini bisa tetap ada jika butuh refresh
            }
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
