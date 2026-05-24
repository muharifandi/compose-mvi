/**
 * Created by Muh. Arifandi on 25/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : features:master:impl
 * File : CreditCardInfo.kt
 */

package com.muh.arifandi.dicoding.features.master.domain.model

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class CreditCardInfo(
    val holderName: String,
    val cardType: String,
    val cardNumber: String,
    val balance: String,
    val gradientColors: List<Color>,
    @DrawableRes val backgroundRes: Int? = null
)
