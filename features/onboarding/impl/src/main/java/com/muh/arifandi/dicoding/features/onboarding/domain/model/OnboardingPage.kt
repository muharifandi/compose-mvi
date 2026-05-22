package com.muh.arifandi.dicoding.features.onboarding.domain.model

import androidx.annotation.DrawableRes

/**
 * Created by Muh. Arifandi on 23/05/26.
 * Email : arif76440@gmail.com
 * Project: SakaAndroid
 * File: OnboardingPage
 */
data class OnboardingPage(
    val title: String,
    val description: String,
    @DrawableRes val imageRes: Int
)