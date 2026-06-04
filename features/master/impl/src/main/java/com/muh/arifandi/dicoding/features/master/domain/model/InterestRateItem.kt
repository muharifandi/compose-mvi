package com.muh.arifandi.dicoding.features.master.domain.model

/**
 * Created by Muh. Arifandi on 26/05/26.
 * Email : arif76440@gmail.com
 * Project: SakaAndroid
 * File: InterestRateItem
 */
data class InterestRateItem(
    val kind: String,
    val deposit: String,
    val rate: String,
    val isPrimary: Boolean = false
)
