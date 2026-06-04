/**
 * Created by Muh. Arifandi on 25/05/2026
 * Email : arif76440@gmail.com
 * Project : SakaAndroid
 * Module : features:transfer:api
 */
package com.muh.arifandi.dicoding.features.transfer.api

import kotlinx.serialization.Serializable

@Serializable
data object TransferDestination

@Serializable
data class TransferConfirmDestination(
    val fromAccount: String,
    val toName: String,
    val beneficiaryBank: String = "US bank",
    val cardNumber: String,
    val amount: String,
    val fee: String = "10$",
    val content: String
)
