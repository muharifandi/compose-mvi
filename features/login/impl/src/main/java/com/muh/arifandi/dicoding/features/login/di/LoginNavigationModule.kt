package com.muh.arifandi.dicoding.features.login.di

import com.muh.arifandi.dicoding.core.architecture.navigation.FeatureApi
import com.muh.arifandi.dicoding.features.login.navigation.LoginFeatureApiImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface LoginNavigationModule {
    @Binds
    @IntoSet
    @Singleton
    fun bindLoginFeatureApi(impl: LoginFeatureApiImpl): FeatureApi
}
