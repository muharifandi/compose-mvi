package com.muh.arifandi.dicoding.features.transfer.ui.state

import com.muh.arifandi.dicoding.core.architecture.mvi.UiState
import com.muh.arifandi.dicoding.features.transfer.domain.model.Beneficiary
import javax.annotation.concurrent.Immutable

@Immutable
data class TransferState(
    val isLoading: Boolean = false,
    val selectedAccount: String = "VISA **** **** **** 1234",
    val availableBalance: String = "10,000$",
    val accounts: List<String> = listOf("VISA **** **** **** 1234", "Mastercard **** **** **** 5678"),
    val transactionTypes: List<TransactionType> = listOf(
        TransactionType.CARD,
        TransactionType.SAME_BANK,
        TransactionType.ANOTHER_BANK
    ),
    val selectedTransactionType: TransactionType = TransactionType.ANOTHER_BANK,
    val beneficiaries: List<Beneficiary> = emptyList(),
    val name: String = "",
    val cardNumber: String = "",
    val amount: String = "",
    val note: String = "",
    val saveToDirectory: Boolean = false,
    val selectedBank: String = "",
    val selectedBranch: String = "",
    val bankSearchQuery: String = "",
    val isBankSheetOpen: Boolean = false,
    val banks: List<String> = listOf(
        "Fifth Third",
        "Bank of the West",
        "Wells Fargo",
        "JP Morgan Chase",
        "US bank",
        "HSBC bank",
        "Citibank",
        "Ame Express"
    )
) : UiState

enum class TransactionType {
    CARD, SAME_BANK, ANOTHER_BANK
}
