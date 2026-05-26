/**
 * Created by Muh. Arifandi on 25/05/2026
 * Email : arif76440@gmail.com
 * Project : SakaAndroid
 * File : MasterMapper.kt
 */

package com.muh.arifandi.dicoding.features.master.ui.mapper

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.CompareArrows
import androidx.compose.material.icons.automirrored.filled.ReceiptLong
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.muh.arifandi.dicoding.features.master.domain.model.MenuType

fun String.toComposeColor(): Color {
    return try {
        Color(android.graphics.Color.parseColor(this))
    } catch (e: Exception) {
        Color.Gray
    }
}

fun MenuType.toIcon(): ImageVector {
    return when (this) {
        MenuType.ACCOUNT -> Icons.Default.AccountBalanceWallet
        MenuType.TRANSFER -> Icons.AutoMirrored.Filled.CompareArrows
        MenuType.WITHDRAW -> Icons.Default.Atm
        MenuType.MOBILE -> Icons.Default.Smartphone
        MenuType.BILLS -> Icons.AutoMirrored.Filled.ReceiptLong
        MenuType.SAVINGS -> Icons.Default.Savings
        MenuType.CREDIT -> Icons.Default.CreditCard
        MenuType.QR -> Icons.Default.QrCodeScanner
        MenuType.TOP_UP -> Icons.Default.AddCircle
        MenuType.MORE -> Icons.Default.MoreHoriz
    }
}
