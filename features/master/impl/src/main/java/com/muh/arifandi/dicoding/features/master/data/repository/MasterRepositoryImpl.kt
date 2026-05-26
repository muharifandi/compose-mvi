/**
 * Created by Muh. Arifandi on 25/05/2026
 * Email : arif76440@gmail.com
 * Project : SakaAndroid
 * File : MasterRepositoryImpl.kt
 */

package com.muh.arifandi.dicoding.features.master.data.repository

import com.muh.arifandi.dicoding.core.ui.R
import com.muh.arifandi.dicoding.features.master.domain.model.CreditCardInfo
import com.muh.arifandi.dicoding.features.master.domain.model.MasterMenuItem
import com.muh.arifandi.dicoding.features.master.domain.model.MenuType
import com.muh.arifandi.dicoding.features.master.domain.repository.MasterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MasterRepositoryImpl @Inject constructor() : MasterRepository {

    override fun getCreditCards(): Flow<List<CreditCardInfo>> = flow {
        emit(
            listOf(
                CreditCardInfo(
                    "John Smith",
                    "Amazon Platinium",
                    "4756 •••• •••• 9018",
                    "$3.469.52",
                    listOf("#0F1B63", "#285CDE", "#45A6FF"),
                    backgroundRes = R.drawable.ic_card_background_blue
                ),
                CreditCardInfo(
                    "John Smith",
                    "Visa Gold",
                    "5214 •••• •••• 1234",
                    "$12.850.00",
                    listOf("#E91E63", "#FF5252"),
                    backgroundRes = R.drawable.ic_card_background_pink
                ),
                CreditCardInfo(
                    "John Smith",
                    "Mastercard Black",
                    "3589 •••• •••• 7721",
                    "$52.400.15",
                    listOf("#2D229E", "#4A3AF3"),
                    backgroundRes = R.drawable.ic_card_background_blue
                )
            )
        )
    }

    override fun getMenuItems(cardIndex: Int): Flow<List<MasterMenuItem>> = flow {
        val primaryMenus = listOf(
            MasterMenuItem(MenuType.ACCOUNT, "Account", "#5E5CE6"),
            MasterMenuItem(MenuType.TRANSFER, "Transfer", "#FF2D55"),
            MasterMenuItem(MenuType.WITHDRAW, "Withdraw", "#007AFF"),
            MasterMenuItem(MenuType.MOBILE, "Mobile", "#FF9500"),
            MasterMenuItem(MenuType.BILLS, "Bills", "#34C759"),
            MasterMenuItem(MenuType.SAVINGS, "Savings", "#5856D6"),
            MasterMenuItem(MenuType.CREDIT, "Credit", "#FF2D55"),
            MasterMenuItem(MenuType.QR, "QR", "#007AFF"),
            MasterMenuItem(MenuType.TOP_UP, "Top Up", "#34C759"),
        )
        
        val menus = when (cardIndex) {
            0 -> primaryMenus
            1 -> primaryMenus.reversed()
            else -> primaryMenus.shuffled()
        }
        emit(menus)
    }
}
