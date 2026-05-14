package com.muh.arifandi.dicoding.core.model

/**
 * Created by Muh. Arifandi on 06/05/26.
 * Email : arif76440@gmail.com
 * Project: My Application
 * File: ResultState
 */
import androidx.compose.runtime.Immutable

@Immutable
sealed interface ResultState<out T> {
    @Immutable
    data object Loading : ResultState<Nothing>
    @Immutable
    data class Success<out T>(val data: T) : ResultState<T>
    @Immutable
    data class Error(val message: String) : ResultState<Nothing>
}
