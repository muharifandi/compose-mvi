/**
 * Created by Muh. Arifandi on 25/05/26.
 * Email : arif76440@gmail.com
 * Project: SakaAndroid
 * File: SearchIntent
 */
package com.muh.arifandi.dicoding.features.master.ui.search.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiIntent

sealed interface SearchIntent : UiIntent {
    data class UpdateQuery(val query: String) : SearchIntent
    data object ClearSearch : SearchIntent
}
