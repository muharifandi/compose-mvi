/**
 * Created by Muh. Arifandi on 25/05/26.
 * Email : arif76440@gmail.com
 * Project: SakaAndroid
 * File: GetOnboardingPagesUseCase
 */
package com.muh.arifandi.dicoding.features.onboarding.domain.usecase

import com.muh.arifandi.dicoding.features.onboarding.domain.model.OnboardingPage
import com.muh.arifandi.dicoding.features.onboarding.domain.repository.OnboardingRepository
import javax.inject.Inject

class GetOnboardingPagesUseCase @Inject constructor(
    private val repository: OnboardingRepository
) {
    operator fun invoke(): List<OnboardingPage> = repository.getOnboardingPages()
}
