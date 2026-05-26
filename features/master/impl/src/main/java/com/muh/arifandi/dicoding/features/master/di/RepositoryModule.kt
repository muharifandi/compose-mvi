/**
 * Created by Muh. Arifandi on 25/05/2026
 * Email : arif76440@gmail.com
 * Project : SakaAndroid
 * File : RepositoryModule.kt
 */

package com.muh.arifandi.dicoding.features.master.di

import com.muh.arifandi.dicoding.features.master.data.repository.MasterRepositoryImpl
import com.muh.arifandi.dicoding.features.master.domain.repository.MasterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindMasterRepository(impl: MasterRepositoryImpl): MasterRepository
}
