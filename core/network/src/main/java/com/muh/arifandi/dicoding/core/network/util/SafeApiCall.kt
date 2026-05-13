package com.muh.arifandi.dicoding.core.network.util

import com.muh.arifandi.dicoding.core.model.ResultState
import com.google.gson.Gson
import com.muh.arifandi.dicoding.core.network.dto.ErrorResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SafeApiCall @Inject constructor() {
    fun <T> flow(apiCall: suspend () -> T): Flow<ResultState<T>> = kotlinx.coroutines.flow.flow {
        emit(ResultState.Loading)
        try {
            val response = apiCall.invoke()
            emit(ResultState.Success(response))
        } catch (throwable: Throwable) {
            val message = when (throwable) {
                is UnknownHostException -> "Tidak ada koneksi internet. Silakan periksa jaringan Anda."
                is SocketTimeoutException -> "Koneksi ke server terputus (timeout). Silakan coba lagi."
                is IOException -> "Terjadi kesalahan jaringan saat mengambil data."
                is HttpException -> {
                    val errorBody = throwable.response()?.errorBody()?.string()
                    val errorResponse = try {
                        Gson().fromJson(errorBody, ErrorResponse::class.java)
                    } catch (e: Exception) {
                        null
                    }

                    when (throwable.code()) {
                        400 -> parseBadRequest(errorResponse?.code, errorResponse?.message)
                        401 -> "Autentikasi gagal. API Key Anda mungkin tidak valid atau hilang."
                        429 -> parseRateLimit(errorResponse?.code)
                        500 -> "Server sedang mengalami gangguan. Silakan coba beberapa saat lagi."
                        else -> errorResponse?.message ?: "Terjadi kesalahan sistem (${throwable.code()})"
                    }
                }
                else -> throwable.message ?: "Terjadi kesalahan yang tidak terduga"
            }
            emit(ResultState.Error(message))
        }
    }

    private fun parseBadRequest(code: String?, defaultMessage: String?): String {
        return when (code) {
            "parameterInvalid" -> "Permintaan tidak valid. Silakan periksa kembali pencarian Anda."
            "parametersMissing" -> "Parameter wajib tidak ditemukan."
            "sourcesTooMany" -> "Terlalu banyak sumber yang diminta dalam satu waktu."
            "sourceDoesNotExist" -> "Sumber berita yang Anda cari tidak ditemukan."
            else -> defaultMessage ?: "Permintaan tidak dapat diproses (Bad Request)."
        }
    }

    private fun parseRateLimit(code: String?): String {
        return when (code) {
            "rateLimited" -> "Anda telah mencapai batas permintaan. Tunggu sebentar sebelum mencoba lagi."
            "apiKeyExhausted" -> "Limit harian API Key Anda telah habis. Silakan coba lagi besok."
            else -> "Terlalu banyak permintaan dalam waktu singkat (Rate Limited)."
        }
    }
}
