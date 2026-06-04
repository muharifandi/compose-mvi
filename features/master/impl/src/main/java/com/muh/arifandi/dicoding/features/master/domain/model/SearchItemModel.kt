package com.muh.arifandi.dicoding.features.master.domain.model

/**
 * Created by Muh. Arifandi on 25/05/26.
 * Email : arif76440@gmail.com
 * Project: SakaAndroid
 * File: SearchItemModel
 */
data class SearchItemModel(
    val title: String,
    val description: String,
    val imageRes: Int,
    val targetRoute: String? = null
)