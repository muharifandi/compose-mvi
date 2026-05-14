package com.muh.arifandi.dicoding.features.news.data.di

import com.muh.arifandi.dicoding.features.news.data.network.api.NewsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsNetworkModule {

    @Provides
    @Singleton
    fun provideNewsApiService(
        retrofit: Retrofit
    ): NewsApiService {
        return retrofit.create(NewsApiService::class.java)
    }
}
