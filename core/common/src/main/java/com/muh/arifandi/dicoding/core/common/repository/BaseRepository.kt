package com.muh.arifandi.dicoding.core.common.repository

import com.muh.arifandi.dicoding.core.common.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import android.util.Log

/**
 * Created by Foundation Team
 * Generic BaseRepository to standardize error handling and flow emission across features.
 */
abstract class BaseRepository {

    protected fun <T> safeNetworkCall(
        call: suspend () -> T
    ): Flow<ResultState<T>> = flow {
        emit(ResultState.Loading)
        try {
            val response = call()
            emit(ResultState.Success(response))
        } catch (e: Exception) {
            Log.e("BaseRepository", "Network call failed", e)
            emit(ResultState.Error(e.message ?: "Unknown Error occurred"))
        }
    }
}
