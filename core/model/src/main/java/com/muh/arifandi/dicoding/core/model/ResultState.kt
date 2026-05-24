/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : core:model
 * File : ResultState.kt
 *
 * Description:
 * Sealed interface untuk menangani berbagai status hasil operasi (Loading, Success, Error).
 */

package com.muh.arifandi.dicoding.core.model
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
