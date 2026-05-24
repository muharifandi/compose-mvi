package com.muh.arifandi.dicoding.features.onboarding.domain.repository

import com.muh.arifandi.dicoding.core.ui.R as CoreR
import com.muh.arifandi.dicoding.features.onboarding.R
import com.muh.arifandi.dicoding.features.onboarding.domain.model.OnboardingPage
import javax.inject.Inject

/**
 * Created by Muh. Arifandi on 23/05/26.
 * Email : arif76440@gmail.com
 * Project: SakaAndroid
 * File: OnboardingRepositoryImpl
 */
class OnboardingRepositoryImpl @Inject constructor() : OnboardingRepository {
    override fun getOnboardingPages(): List<OnboardingPage> {
        return listOf(
            OnboardingPage(
                titleRes = R.string.onboarding_page1_title,
                descriptionRes = R.string.onboarding_page1_desc,
                imageRes = CoreR.drawable.ic_onboarding_1
            ),
            OnboardingPage(
                titleRes = R.string.onboarding_page2_title,
                descriptionRes = R.string.onboarding_page2_desc,
                imageRes = CoreR.drawable.ic_onboarding_2
            ),
            OnboardingPage(
                titleRes = R.string.onboarding_page3_title,
                descriptionRes = R.string.onboarding_page3_desc,
                imageRes = CoreR.drawable.ic_onboarding_3
            )
        )
    }
}
