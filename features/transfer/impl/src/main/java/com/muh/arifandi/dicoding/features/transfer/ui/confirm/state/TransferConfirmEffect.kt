package com.muh.arifandi.dicoding.features.transfer.ui.confirm.state

sealed interface TransferConfirmEffect {
    data object NavigateToSuccess : TransferConfirmEffect
    data class ShowError(val message: String) : TransferConfirmEffect
}
