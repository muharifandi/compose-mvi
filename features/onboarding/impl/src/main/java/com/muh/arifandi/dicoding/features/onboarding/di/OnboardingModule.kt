package com.muh.arifandi.dicoding.features.onboarding.di

import com.muh.arifandi.dicoding.features.onboarding.domain.repository.OnboardingRepository
import com.muh.arifandi.dicoding.features.onboarding.domain.repository.OnboardingRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * Created by Muh. Arifandi on 23/05/26.
 * Email : arif76440@gmail.com
 * Project: SakaAndroid
 * File: OnboardingModule
 */
@Module
@InstallIn(ViewModelComponent::class)
abstract class OnboardingModule {
    @Binds
    abstract fun bindOnboardingRepository(
        impl: OnboardingRepositoryImpl
    ): OnboardingRepository
}