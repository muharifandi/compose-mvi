/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : features:home
 * File : DataModule.kt
 *
 * Description:
 * Dependency Injection module untuk menyediakan implementasi repository
 * menggunakan Hilt.
 */

package com.muh.arifandi.dicoding.features.home.di

import com.muh.arifandi.dicoding.features.home.data.repository.NewsRepositoryImpl
import com.muh.arifandi.dicoding.domain.news.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindNewsRepository(
        newsRepositoryImpl: NewsRepositoryImpl
    ): NewsRepository
}
