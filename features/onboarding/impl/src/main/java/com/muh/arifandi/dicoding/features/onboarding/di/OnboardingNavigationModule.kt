/**
 * Created by Muh. Arifandi on 23/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : features:onboarding:impl
 * File : OnboardingNavigationModule.kt
 */

package com.muh.arifandi.dicoding.features.onboarding.di

import com.muh.arifandi.dicoding.core.architecture.navigation.FeatureApi
import com.muh.arifandi.dicoding.features.onboarding.navigation.OnboardingFeatureApiImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class OnboardingNavigationModule {
    @Binds
    @Singleton
    @IntoSet
    abstract fun bindOnboardingFeatureApi(
        impl: OnboardingFeatureApiImpl
    ): FeatureApi
}
