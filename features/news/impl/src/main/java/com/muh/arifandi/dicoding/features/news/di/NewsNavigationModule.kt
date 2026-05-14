package com.muh.arifandi.dicoding.features.news.di

import com.muh.arifandi.dicoding.core.architecture.navigation.FeatureApi
import com.muh.arifandi.dicoding.features.news.navigation.NewsFeatureApiImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NewsNavigationModule {

    @Binds
    @IntoSet
    @Singleton
    fun bindNewsFeatureApi(impl: NewsFeatureApiImpl): FeatureApi
}
