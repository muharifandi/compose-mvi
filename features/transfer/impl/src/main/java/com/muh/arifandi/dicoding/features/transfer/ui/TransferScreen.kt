package com.muh.arifandi.dicoding.features.transfer.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.muh.arifandi.dicoding.core.ui.designsystem.components.*
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.MyApplicationTheme
import com.muh.arifandi.dicoding.core.ui.designsystem.theme.SakaTheme
import com.muh.arifandi.dicoding.features.transfer.api.TransferConfirmDestination
import com.muh.arifandi.dicoding.features.transfer.ui.component.TransferBeneficiaryList
import com.muh.arifandi.dicoding.features.transfer.ui.component.TransferForm
import com.muh.arifandi.dicoding.features.transfer.ui.component.TransferTransactionTypeSelector
import com.muh.arifandi.dicoding.features.transfer.ui.state.*

@Composable
fun TransferScreen(
    viewModel: TransferViewModel = hiltViewModel(),
    onBackClick: () -> Unit = {},
    onNavigateToConfirm: (TransferConfirmDestination) -> Unit = {}
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is TransferEffect.NavigateToConfirm -> {
                    onNavigateToConfirm(
                        TransferConfirmDestination(
                            fromAccount = state.selectedAccount,
                            toName = state.name,
                            beneficiaryBank = state.selectedBank.ifEmpty { "US bank" },
                            cardNumber = state.cardNumber,
                            amount = state.amount,
                            content = state.note
                        )
                    )
                }
                else -> {}
            }
        }
    }

    TransferContent(
        state = state,
        onIntent = viewModel::processIntent,
        onBackClick = onBackClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TransferContent(
    state: TransferState,
    onIntent: (TransferIntent) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val sheetState = rememberModalBottomSheetState()

    if (state.isBankSheetOpen) {
        ModalBottomSheet(
            onDismissRequest = { onIntent(TransferIntent.ToggleBankSheet(false)) },
            sheetState = sheetState,
            containerColor = Color.White,
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
        ) {
            BankSelectionContent(
                searchQuery = state.bankSearchQuery,
                onSearchChange = { onIntent(TransferIntent.BankSearchQueryChanged(it)) },
                banks = state.banks.filter { it.contains(state.bankSearchQuery, ignoreCase = true) },
                selectedBank = state.selectedBank,
                onBankSelect = { onIntent(TransferIntent.SelectBank(it)) }
            )
        }
    }

    SakaScaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color(0xFFF8F9FA),
        topBar = {
            SakaNavigationBar(
                title = "Transfer",
                onBackClick = onBackClick,
                backgroundColor = Color(0xFFF8F9FA)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            
            // Account Selector
            SectionTitle("Choose account/ card")
            AccountSelector(
                selectedAccount = state.selectedAccount,
                onAccountClick = { /* Handle Account Selection if needed */ }
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Available balance : ${state.availableBalance}",
                style = SakaTheme.typography.caption1.copy(fontWeight = FontWeight.Bold),
                color = Color(0xFF3629B7)
            )

            Spacer(modifier = Modifier.height(24.dp))
            SectionTitle("Choose transaction")
            
            TransferTransactionTypeSelector(
                selectedType = state.selectedTransactionType,
                onTypeSelect = { onIntent(TransferIntent.SelectTransactionType(it)) }
            )

            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SectionTitle("Choose beneficiary")
                Text(
                    text = "Find beneficiary",
                    style = SakaTheme.typography.caption1.copy(fontWeight = FontWeight.Bold),
                    color = Color(0xFF3629B7),
                    modifier = Modifier.clickable { }
                )
            }

            Spacer(modifier = Modifier.height(12.dp))
            TransferBeneficiaryList(
                beneficiaries = state.beneficiaries,
                onBeneficiaryClick = { onIntent(TransferIntent.SelectBeneficiary(it)) },
                onAddClick = { /* Handle Add */ }
            )

            Spacer(modifier = Modifier.height(24.dp))
            
            TransferForm(
                selectedBank = state.selectedBank,
                selectedBranch = state.selectedBranch,
                name = state.name,
                cardNumber = state.cardNumber,
                amount = state.amount,
                note = state.note,
                saveToDirectory = state.saveToDirectory,
                onBankClick = { onIntent(TransferIntent.ToggleBankSheet(true)) },
                onBranchClick = { /* Handle Branch Click */ },
                onNameChange = { onIntent(TransferIntent.NameChanged(it)) },
                onCardNumberChange = { onIntent(TransferIntent.CardNumberChanged(it)) },
                onAmountChange = { onIntent(TransferIntent.AmountChanged(it)) },
                onNoteChange = { onIntent(TransferIntent.NoteChanged(it)) },
                onSaveToDirectoryToggle = { onIntent(TransferIntent.ToggleSaveToDirectory(it)) },
                onSubmit = { onIntent(TransferIntent.SubmitTransfer) }
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun BankSelectionContent(
    searchQuery: String,
    onSearchChange: (String) -> Unit,
    banks: List<String>,
    selectedBank: String,
    onBankSelect: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 600.dp)
            .padding(24.dp)
    ) {
        Text(
            text = "Choose beneficiary bank",
            style = SakaTheme.typography.title3,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = Color(0xFF333333)
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        SakaSearchField(
            value = searchQuery,
            onValueChange = onSearchChange,
            placeholder = "Search",
            onClear = { onSearchChange("") }
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(banks) { bank ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onBankSelect(bank) }
                        .padding(vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = bank,
                        style = SakaTheme.typography.body2,
                        color = Color.Gray
                    )
                    if (bank == selectedBank) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            tint = Color(0xFF3629B7)
                        )
                    }
                }
                HorizontalDivider(color = Color(0xFFF2F2F2))
            }
        }
    }
}

@Composable
private fun SectionTitle(title: String) {
    Text(
        text = title,
        style = SakaTheme.typography.caption1,
        color = Color.Gray,
        modifier = Modifier.padding(bottom = 12.dp)
    )
}

@Composable
private fun AccountSelector(selectedAccount: String, onAccountClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .clickable { onAccountClick() }
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = selectedAccount,
            style = SakaTheme.typography.body2.copy(fontWeight = FontWeight.Medium),
            color = Color(0xFF333333)
        )
        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = null,
            tint = Color.LightGray
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TransferPreview() {
    MyApplicationTheme {
        TransferContent(state = TransferState(), onIntent = {}, onBackClick = {})
    }
}
