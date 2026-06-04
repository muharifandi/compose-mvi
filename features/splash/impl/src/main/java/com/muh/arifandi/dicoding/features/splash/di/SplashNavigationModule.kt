package com.muh.arifandi.dicoding.features.splash.di

import com.muh.arifandi.dicoding.core.architecture.navigation.FeatureApi
import com.muh.arifandi.dicoding.features.splash.navigation.SplashFeatureApiImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SplashNavigationModule {
    @Binds
    @Singleton
    @IntoSet
    abstract fun bindSplashFeatureApi(impl: SplashFeatureApiImpl): FeatureApi
}
