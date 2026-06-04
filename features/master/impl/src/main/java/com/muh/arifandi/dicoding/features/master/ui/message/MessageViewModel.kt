package com.muh.arifandi.dicoding.features.master.ui.message

import androidx.compose.ui.graphics.Color
import com.muh.arifandi.dicoding.core.architecture.mvi.BaseViewModel
import com.muh.arifandi.dicoding.features.master.domain.model.MessageModel
import com.muh.arifandi.dicoding.features.master.ui.message.state.MessageEffect
import com.muh.arifandi.dicoding.features.master.ui.message.state.MessageIntent
import com.muh.arifandi.dicoding.features.master.ui.message.state.MessageState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MessageViewModel @Inject constructor() :
    BaseViewModel<MessageState, MessageIntent, MessageEffect>(MessageState()) {

    init {
        processIntent(MessageIntent.LoadMessages)
    }

    override fun processIntent(intent: MessageIntent) {
        when (intent) {
            is MessageIntent.LoadMessages -> loadMessages()
        }
    }

    private fun loadMessages() {
        val mockMessages = listOf(
            MessageModel(
                id = "1",
                sender = "Bank of America",
                summary = "Bank of America: 256486 is the au...",
                date = "Today",
                iconBackgroundColor = Color(0xFF3629B7)
            ),
            MessageModel(
                id = "2",
                sender = "Account",
                summary = "Your account is limited. Please foll...",
                date = "12/10",
                iconBackgroundColor = Color(0xFFFF4B6E)
            ),
            MessageModel(
                id = "3",
                sender = "Alert",
                summary = "Your statement is ready for you to...",
                date = "11/10",
                iconBackgroundColor = Color(0xFF0081FF)
            ),
            MessageModel(
                id = "4",
                sender = "Paypal",
                summary = "Your account has been locked. Ple...",
                date = "10/11",
                iconBackgroundColor = Color(0xFFFFB238)
            ),
            MessageModel(
                id = "5",
                sender = "Withdraw",
                summary = "Dear customer, 2987456 is your co...",
                date = "10/12",
                iconBackgroundColor = Color(0xFF43D8B5)
            )
        )
        setState { copy(messages = mockMessages) }
    }
}
