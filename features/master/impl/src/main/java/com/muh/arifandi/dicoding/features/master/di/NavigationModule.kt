/**
 * Created by Muh. Arifandi on 25/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : features:master:impl
 * File : NavigationModule.kt
 *
 * Description:
 * Modul DI Hilt untuk mengikat implementasi navigasi fitur Master.
 */
package com.muh.arifandi.dicoding.features.master.di

import com.muh.arifandi.dicoding.core.architecture.navigation.FeatureApi
import com.muh.arifandi.dicoding.features.master.navigation.MasterFeatureApiImpl
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
    fun bindMasterFeatureApi(impl: MasterFeatureApiImpl): FeatureApi
}
