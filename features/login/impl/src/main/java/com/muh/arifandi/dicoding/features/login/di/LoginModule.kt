/**
 * Created by Muh. Arifandi on 25/05/26.
 * Email : arif76440@gmail.com
 * Project: SakaAndroid
 * File: LoginModule
 */
package com.muh.arifandi.dicoding.features.login.di

import com.muh.arifandi.dicoding.features.login.data.repository.LoginRepositoryImpl
import com.muh.arifandi.dicoding.features.login.domain.repository.LoginRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface LoginModule {

    @Binds
    @Singleton
    fun bindLoginRepository(impl: LoginRepositoryImpl): LoginRepository
}
