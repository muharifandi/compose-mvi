/**
 * Created by Muh. Arifandi on 25/05/26.
 * Email : arif76440@gmail.com
 * Project: SakaAndroid
 * File: MessageEffect
 */
package com.muh.arifandi.dicoding.features.master.ui.message.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiEffect

sealed interface MessageEffect : UiEffect {
    data object ScrollToTop : MessageEffect
}
