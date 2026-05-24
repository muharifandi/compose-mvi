package com.muh.arifandi.dicoding.features.onboarding.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * Created by Muh. Arifandi on 23/05/26.
 * Email : arif76440@gmail.com
 * Project: SakaAndroid
 * File: OnboardingPage
 */
data class OnboardingPage(
    @StringRes val titleRes: Int,
    @StringRes val descriptionRes: Int,
    @DrawableRes val imageRes: Int
)
