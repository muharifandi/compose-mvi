package com.muh.arifandi.dicoding.features.master.ui.message

import com.muh.arifandi.dicoding.core.architecture.mvi.BaseViewModel
import com.muh.arifandi.dicoding.features.master.ui.message.state.MessageEffect
import com.muh.arifandi.dicoding.features.master.ui.message.state.MessageIntent
import com.muh.arifandi.dicoding.features.master.ui.message.state.MessageState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MessageViewModel @Inject constructor() :
    BaseViewModel<MessageState, MessageIntent, MessageEffect>(MessageState()) {

    override fun processIntent(intent: MessageIntent) {
        when (intent) {
            is MessageIntent.RefreshMessages -> {
                // Implement logic
            }
        }
    }
}