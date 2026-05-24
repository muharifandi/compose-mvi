/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : core:common
 * File : ConnectivityObserver.kt
 *
 * Description:
 * Interface untuk memantau status konektivitas jaringan secara real-time.
 */

package com.muh.arifandi.dicoding.core.common.util

import kotlinx.coroutines.flow.Flow
interface ConnectivityObserver {

    fun observe(): Flow<Status>

    enum class Status {
        Available, Unavailable, Losing, Lost
    }
}