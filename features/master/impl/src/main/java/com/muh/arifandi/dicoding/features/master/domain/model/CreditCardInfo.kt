/**
 * Created by Muh. Arifandi on 25/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : features:master:impl
 * File : CreditCardInfo.kt
 *
 * Description:
 * Model data informasi kartu kredit.
 * Diubah menjadi Pure Kotlin untuk mematuhi Clean Architecture.
 */

package com.muh.arifandi.dicoding.features.master.domain.model

data class CreditCardInfo(
    val holderName: String,
    val cardType: String,
    val cardNumber: String,
    val balance: String,
    val gradientColorsHex: List<String>, // Menggunakan Hex String
    val backgroundRes: Int? = null
)
