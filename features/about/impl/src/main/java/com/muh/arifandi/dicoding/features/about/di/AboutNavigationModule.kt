package com.muh.arifandi.dicoding.features.about.di

import com.muh.arifandi.dicoding.core.architecture.navigation.FeatureApi
import com.muh.arifandi.dicoding.features.about.navigation.AboutFeatureApiImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AboutNavigationModule {

    @Binds
    @IntoSet
    @Singleton
    fun bindAboutFeatureApi(impl: AboutFeatureApiImpl): FeatureApi
}
