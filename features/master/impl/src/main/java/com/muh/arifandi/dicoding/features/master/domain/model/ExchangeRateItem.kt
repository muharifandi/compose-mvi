package com.muh.arifandi.dicoding.features.master.domain.model

/**
 * Created by Muh. Arifandi on 26/05/26.
 * Email : arif76440@gmail.com
 * Project: SakaAndroid
 * File: ExchangeRateItem
 */
data class ExchangeRateItem(
    val countryName: String,
    val flagUrl: String,
    val buyRate: String,
    val sellRate: String
)
