package com.muh.arifandi.dicoding.features.onboarding.domain.repository

import com.muh.arifandi.dicoding.features.onboarding.domain.model.OnboardingPage

/**
 * Created by Muh. Arifandi on 23/05/26.
 * Email : arif76440@gmail.com
 * Project: SakaAndroid
 * File: OnboardingRepository
 */
interface OnboardingRepository {
    fun getOnboardingPages(): List<OnboardingPage>
}