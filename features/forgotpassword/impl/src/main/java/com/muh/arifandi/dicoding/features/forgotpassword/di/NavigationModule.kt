/**
 * Created by Muh. Arifandi on 24/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : features:forgotpassword:impl
 * File : NavigationModule.kt
 */
package com.muh.arifandi.dicoding.features.forgotpassword.di

import com.muh.arifandi.dicoding.core.architecture.navigation.FeatureApi
import com.muh.arifandi.dicoding.features.forgotpassword.navigation.ForgotpasswordFeatureApiImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {
    @Binds
    @IntoSet
    @Singleton
    fun bindForgotpasswordFeatureApi(impl: ForgotpasswordFeatureApiImpl): FeatureApi
}
