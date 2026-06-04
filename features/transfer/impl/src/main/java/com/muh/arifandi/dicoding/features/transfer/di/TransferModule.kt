package com.muh.arifandi.dicoding.features.transfer.di

import com.muh.arifandi.dicoding.core.architecture.navigation.FeatureApi
import com.muh.arifandi.dicoding.features.transfer.navigation.TransferFeatureApiImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TransferModule {

    @Binds
    @IntoSet
    @Singleton
    abstract fun bindTransferFeatureApi(
        transferFeatureApiImpl: TransferFeatureApiImpl
    ): FeatureApi
}
