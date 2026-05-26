/**
 * Created by Muh. Arifandi on 25/05/26.
 * Email : arif76440@gmail.com
 * Project: SakaAndroid
 * File: OnboardingModule
 */
package com.muh.arifandi.dicoding.features.onboarding.di

import com.muh.arifandi.dicoding.features.onboarding.data.repository.OnboardingRepositoryImpl
import com.muh.arifandi.dicoding.features.onboarding.domain.repository.OnboardingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface OnboardingModule {

    @Binds
    @Singleton
    fun bindOnboardingRepository(impl: OnboardingRepositoryImpl): OnboardingRepository
}
