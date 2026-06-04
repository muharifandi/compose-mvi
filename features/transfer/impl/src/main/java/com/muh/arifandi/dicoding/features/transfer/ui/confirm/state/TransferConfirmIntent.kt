package com.muh.arifandi.dicoding.features.transfer.ui.confirm.state

sealed interface TransferConfirmIntent {
    data class OtpChanged(val otp: String) : TransferConfirmIntent
    data object RequestOtp : TransferConfirmIntent
    data object ConfirmTransfer : TransferConfirmIntent
    data object BiometricAuth : TransferConfirmIntent
    data object DismissBiometric : TransferConfirmIntent
}
