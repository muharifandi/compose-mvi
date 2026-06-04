package com.muh.arifandi.dicoding.features.transfer.ui.confirm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.muh.arifandi.dicoding.features.transfer.api.TransferConfirmDestination
import com.muh.arifandi.dicoding.features.transfer.ui.confirm.state.TransferConfirmEffect
import com.muh.arifandi.dicoding.features.transfer.ui.confirm.state.TransferConfirmIntent
import com.muh.arifandi.dicoding.features.transfer.ui.confirm.state.TransferConfirmState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransferConfirmViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val destination = savedStateHandle.toRoute<TransferConfirmDestination>()

    private val _state = MutableStateFlow(
        TransferConfirmState(
            fromAccount = destination.fromAccount,
            toName = destination.toName,
            beneficiaryBank = destination.beneficiaryBank,
            cardNumber = destination.cardNumber,
            amount = destination.amount,
            fee = destination.fee,
            content = destination.content
        )
    )
    val state: StateFlow<TransferConfirmState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<TransferConfirmEffect>()
    val effect: SharedFlow<TransferConfirmEffect> = _effect.asSharedFlow()

    fun processIntent(intent: TransferConfirmIntent) {
        when (intent) {
            is TransferConfirmIntent.OtpChanged -> {
                _state.update { it.copy(otp = intent.otp) }
            }
            TransferConfirmIntent.RequestOtp -> {
                _state.update { it.copy(isOtpSent = true, showBiometricDialog = true) }
            }
            TransferConfirmIntent.ConfirmTransfer -> {
                confirmTransfer()
            }
            TransferConfirmIntent.BiometricAuth -> {
                _state.update { it.copy(showBiometricDialog = true) }
            }
            TransferConfirmIntent.DismissBiometric -> {
                _state.update { it.copy(showBiometricDialog = false) }
            }
        }
    }

    private fun confirmTransfer() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            // Mock delay
            kotlinx.coroutines.delay(2000)
            _state.update { it.copy(isLoading = false, isSuccess = true) }
        }
    }
}
