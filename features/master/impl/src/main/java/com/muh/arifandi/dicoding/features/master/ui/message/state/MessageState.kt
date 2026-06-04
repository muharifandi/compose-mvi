/**
 * Created by Muh. Arifandi on 25/05/26.
 * Email : arif76440@gmail.com
 * Project: SakaAndroid
 * File: MessageState
 */
package com.muh.arifandi.dicoding.features.master.ui.message.state

import androidx.compose.runtime.Immutable
import com.muh.arifandi.dicoding.core.architecture.mvi.UiState
import com.muh.arifandi.dicoding.features.master.domain.model.MessageModel

@Immutable
data class MessageState(
    val isLoading: Boolean = false,
    val messages: List<MessageModel> = emptyList()
) : UiState
