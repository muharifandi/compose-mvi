package com.muh.arifandi.dicoding.core.common.util

import kotlinx.coroutines.flow.Flow

/**
 * Created by Muh. Arifandi on 07/05/26.
 * Email : arif76440@gmail.com
 * Project: My Application
 * File: ConnectivityObserver
 */
interface ConnectivityObserver {

    fun observe(): Flow<Status>

    enum class Status {
        Available, Unavailable, Losing, Lost
    }
}