package com.muh.arifandi.dicoding.features.transfer.ui.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiIntent
import com.muh.arifandi.dicoding.features.transfer.domain.model.Beneficiary

sealed interface TransferIntent : UiIntent {
    data object LoadData : TransferIntent
    data class SelectAccount(val account: String) : TransferIntent
    data class SelectTransactionType(val type: TransactionType) : TransferIntent
    data class SelectBeneficiary(val beneficiary: Beneficiary) : TransferIntent
    data class NameChanged(val name: String) : TransferIntent
    data class CardNumberChanged(val number: String) : TransferIntent
    data class AmountChanged(val amount: String) : TransferIntent
    data class NoteChanged(val note: String) : TransferIntent
    data class ToggleSaveToDirectory(val save: Boolean) : TransferIntent
    data object SubmitTransfer : TransferIntent
    
    data class BankSearchQueryChanged(val query: String) : TransferIntent
    data class SelectBank(val bank: String) : TransferIntent
    data class SelectBranch(val branch: String) : TransferIntent
    data class ToggleBankSheet(val isOpen: Boolean) : TransferIntent
}
