/**
 * Created by Muh. Arifandi on 25/05/26.
 * Email : arif76440@gmail.com
 * Project: SakaAndroid
 * File: RegisterModule
 */
package com.muh.arifandi.dicoding.features.register.di

import com.muh.arifandi.dicoding.features.register.data.repository.RegisterRepositoryImpl
import com.muh.arifandi.dicoding.features.register.domain.repository.RegisterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RegisterModule {

    @Binds
    @Singleton
    fun bindRegisterRepository(impl: RegisterRepositoryImpl): RegisterRepository
}
