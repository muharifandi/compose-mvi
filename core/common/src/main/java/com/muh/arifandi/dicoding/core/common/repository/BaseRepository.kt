package com.muh.arifandi.dicoding.core.common.repository

import com.muh.arifandi.dicoding.core.model.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import android.util.Log
import java.net.UnknownHostException
import java.net.SocketTimeoutException
import java.io.IOException

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
        } catch (e: UnknownHostException) {
            Log.e("BaseRepository", "No Internet Connection", e)
            emit(ResultState.Error("Tidak ada koneksi internet. Silakan periksa jaringan Anda."))
        } catch (e: SocketTimeoutException) {
            Log.e("BaseRepository", "Connection Timeout", e)
            emit(ResultState.Error("Koneksi ke server terputus (timeout). Silakan coba lagi nanti."))
        } catch (e: IOException) {
            Log.e("BaseRepository", "Network error", e)
            emit(ResultState.Error("Terjadi kesalahan jaringan. Silakan coba beberapa saat lagi."))
        } catch (e: Exception) {
            Log.e("BaseRepository", "Unexpected error", e)
            emit(ResultState.Error(e.message ?: "Terjadi kesalahan yang tidak terduga."))
        }
    }
}
