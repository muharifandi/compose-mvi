package com.muh.arifandi.dicoding.features.register.di

import com.muh.arifandi.dicoding.core.architecture.navigation.FeatureApi
import com.muh.arifandi.dicoding.features.register.navigation.RegisterFeatureApiImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RegisterNavigationModule {
    @Binds
    @IntoSet
    @Singleton
    fun bindRegisterFeatureApi(impl: RegisterFeatureApiImpl): FeatureApi
}
