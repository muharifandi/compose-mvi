package com.muh.arifandi.dicoding.features.transfer.domain.model

data class Beneficiary(
    val id: String,
    val name: String,
    val imageUrl: String,
    val cardNumber: String = ""
)
