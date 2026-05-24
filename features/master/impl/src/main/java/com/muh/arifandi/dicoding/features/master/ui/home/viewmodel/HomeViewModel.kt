package com.muh.arifandi.dicoding.features.master.ui.home.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.CompareArrows
import androidx.compose.material.icons.automirrored.filled.ReceiptLong
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.Color
import com.muh.arifandi.dicoding.core.architecture.mvi.BaseViewModel
import com.muh.arifandi.dicoding.features.master.domain.model.CreditCardInfo
import com.muh.arifandi.dicoding.features.master.domain.model.MasterMenuItem
import com.muh.arifandi.dicoding.features.master.ui.home.state.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.muh.arifandi.dicoding.core.ui.R

@HiltViewModel
class HomeViewModel @Inject constructor() :
    BaseViewModel<HomeState, HomeIntent, HomeEffect>(HomeState()) {

    init {
        processIntent(HomeIntent.LoadHomeData)
    }

    override fun processIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.LoadHomeData -> loadInitialData()
            is HomeIntent.SelectCard -> {
                setState { copy(selectedCardIndex = intent.index) }
                updateMenuItems(intent.index)
            }
            is HomeIntent.ToggleDataVisibility -> {
                setState { copy(isDataVisible = !isDataVisible) }
            }
        }
    }

    private fun loadInitialData() {
        val cards = getInitialCards()
        setState { 
            copy(
                creditCards = cards,
                homeMenuItems = getPrimaryMenus()
            ) 
        }
    }

    private fun updateMenuItems(cardIndex: Int) {
        val menus = when (cardIndex) {
            0 -> getPrimaryMenus()
            1 -> getSecondaryMenus()
            else -> getDefaultMenus()
        }
        setState { copy(homeMenuItems = menus) }
    }

    private fun getInitialCards() = listOf(
        CreditCardInfo(
            "John Smith",
            "Amazon Platinium",
            "4756 •••• •••• 9018",
            "$3.469.52",
            listOf(Color(0xFF0F1B63), Color(0xFF285CDE), Color(0xFF45A6FF)),
            backgroundRes = R.drawable.ic_card_background_blue
        ),
        CreditCardInfo(
            "John Smith",
            "Visa Gold",
            "5214 •••• •••• 1234",
            "$12.850.00",
            listOf(Color(0xFFE91E63), Color(0xFFFF5252)),
            backgroundRes = R.drawable.ic_card_background_pink
        ),
        CreditCardInfo(
            "John Smith",
            "Mastercard Black",
            "3589 •••• •••• 7721",
            "$52.400.15",
            listOf(Color(0xFF2D229E), Color(0xFF4A3AF3)),
            backgroundRes = R.drawable.ic_card_background_blue
        )
    )

    private fun getPrimaryMenus() = listOf(
        MasterMenuItem("Account", Icons.Default.AccountBalanceWallet, Color(0xFF5E5CE6)),
        MasterMenuItem("Transfer", Icons.AutoMirrored.Filled.CompareArrows, Color(0xFFFF2D55)),
        MasterMenuItem("Withdraw", Icons.Default.Atm, Color(0xFF007AFF)),
        MasterMenuItem("Mobile", Icons.Default.Smartphone, Color(0xFFFF9500)),
        MasterMenuItem("Bills", Icons.AutoMirrored.Filled.ReceiptLong, Color(0xFF34C759)),
        MasterMenuItem("Savings", Icons.Default.Savings, Color(0xFF5856D6)),
        MasterMenuItem("Credit", Icons.Default.CreditCard, Color(0xFFFF2D55)),
        MasterMenuItem("QR", Icons.Default.QrCodeScanner, Color(0xFF007AFF)),
        MasterMenuItem("Top Up", Icons.Default.AddCircle, Color(0xFF34C759)),
    )

    private fun getSecondaryMenus() = getPrimaryMenus().reversed()

    private fun getDefaultMenus() = getPrimaryMenus().shuffled()
}
