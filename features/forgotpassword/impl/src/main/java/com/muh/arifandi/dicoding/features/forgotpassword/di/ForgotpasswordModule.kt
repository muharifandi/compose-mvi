/**
 * Created by Muh. Arifandi on 25/05/26.
 * Email : arif76440@gmail.com
 * Project: SakaAndroid
 * File: ForgotpasswordModule
 */
package com.muh.arifandi.dicoding.features.forgotpassword.di

import com.muh.arifandi.dicoding.features.forgotpassword.data.repository.ForgotpasswordRepositoryImpl
import com.muh.arifandi.dicoding.features.forgotpassword.domain.repository.ForgotpasswordRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ForgotpasswordModule {

    @Binds
    @Singleton
    fun bindForgotpasswordRepository(impl: ForgotpasswordRepositoryImpl): ForgotpasswordRepository
}
