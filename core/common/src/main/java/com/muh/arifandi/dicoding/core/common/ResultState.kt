package com.muh.arifandi.dicoding.core.common

/**
 * Created by Muh. Arifandi on 06/05/26.
 * Email : arif76440@gmail.com
 * Project: My Application
 * File: ResultState
 */
sealed interface ResultState<out T> {
    data object Loading : ResultState<Nothing>
    data class Success<out T>(val data: T) : ResultState<T>
    data class Error(val message: String) : ResultState<Nothing>
}
