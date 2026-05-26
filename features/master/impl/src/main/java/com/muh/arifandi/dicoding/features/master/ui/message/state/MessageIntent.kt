/**
 * Created by Muh. Arifandi on 25/05/26.
 * Email : arif76440@gmail.com
 * Project: SakaAndroid
 * File: MessageIntent
 */
package com.muh.arifandi.dicoding.features.master.ui.message.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiIntent

sealed interface MessageIntent : UiIntent {
    data object RefreshMessages : MessageIntent
}
