/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : core:data
 * File : DataModule.kt
 *
 * Description:
 * Dependency Injection module untuk menyediakan implementasi repository
 * menggunakan Hilt.
 */

package com.muh.arifandi.dicoding.core.data

import com.muh.arifandi.dicoding.core.domain.NewsRepository
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
