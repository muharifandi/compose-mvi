package com.muh.arifandi.dicoding.core.network.util

import retrofit2.HttpException
import java.io.IOException

/**
 * Created by Muh. Arifandi on 06/05/26.
 * Email : arif76440@gmail.com
 * Project: My Application
 * File: SafeApiCall
 */
internal suspend fun <T> safeApiCall(apiCall: suspend () -> T): Result<T> {
    return try {
        Result.success(apiCall.invoke())
    } catch (throwable: Throwable) {
        when (throwable) {
            is IOException -> Result.failure(Exception("Network failure. Please check your connection."))
            is HttpException -> {
                val code = throwable.code()
                val message = when (code) {
                    401 -> "Unauthorized access. Please check your API key."
                    429 -> "Too many requests. Please try again later."
                    else -> "Internal server error ($code)."
                }
                Result.failure(Exception(message))
            }
            else -> Result.failure(throwable)
        }
    }
}
