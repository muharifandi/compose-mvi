package com.muh.arifandi.dicoding.features.transfer.ui

import com.muh.arifandi.dicoding.core.architecture.mvi.BaseViewModel
import com.muh.arifandi.dicoding.features.transfer.domain.model.Beneficiary
import com.muh.arifandi.dicoding.features.transfer.ui.state.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TransferViewModel @Inject constructor() : 
    BaseViewModel<TransferState, TransferIntent, TransferEffect>(TransferState()) {

    init {
        processIntent(TransferIntent.LoadData)
    }

    override fun processIntent(intent: TransferIntent) {
        when (intent) {
            is TransferIntent.LoadData -> loadInitialData()
            is TransferIntent.SelectAccount -> setState { copy(selectedAccount = intent.account) }
            is TransferIntent.SelectTransactionType -> setState { copy(selectedTransactionType = intent.type) }
            is TransferIntent.SelectBeneficiary -> handleSelectBeneficiary(intent.beneficiary)
            is TransferIntent.NameChanged -> setState { copy(name = intent.name) }
            is TransferIntent.CardNumberChanged -> setState { copy(cardNumber = intent.number) }
            is TransferIntent.AmountChanged -> setState { copy(amount = intent.amount) }
            is TransferIntent.NoteChanged -> setState { copy(note = intent.note) }
            is TransferIntent.ToggleSaveToDirectory -> setState { copy(saveToDirectory = intent.save) }
            is TransferIntent.SubmitTransfer -> sendEffect { TransferEffect.NavigateToConfirm }
            
            is TransferIntent.BankSearchQueryChanged -> setState { copy(bankSearchQuery = intent.query) }
            is TransferIntent.SelectBank -> setState { copy(selectedBank = intent.bank, isBankSheetOpen = false) }
            is TransferIntent.SelectBranch -> setState { copy(selectedBranch = intent.branch) }
            is TransferIntent.ToggleBankSheet -> setState { copy(isBankSheetOpen = intent.isOpen) }
        }
    }

    private fun loadInitialData() {
        val mockBeneficiaries = listOf(
            Beneficiary("1", "Emma", "https://i.pravatar.cc/150?u=emma", "1234 5678 9012 3456"),
            Beneficiary("2", "Justin", "https://i.pravatar.cc/150?u=justin", "9876 5432 1098 7654")
        )
        setState { copy(beneficiaries = mockBeneficiaries) }
    }

    private fun handleSelectBeneficiary(beneficiary: Beneficiary) {
        setState { 
            copy(
                name = beneficiary.name,
                cardNumber = beneficiary.cardNumber
            )
        }
    }
}
