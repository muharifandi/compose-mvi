package com.muh.arifandi.dicoding.features.transfer.ui.confirm.state

data class TransferConfirmState(
    val fromAccount: String = "",
    val toName: String = "",
    val beneficiaryBank: String = "",
    val cardNumber: String = "",
    val amount: String = "",
    val fee: String = "10$",
    val content: String = "",
    val otp: String = "",
    val isOtpSent: Boolean = false,
    val isLoading: Boolean = false,
    val showBiometricDialog: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null
)
