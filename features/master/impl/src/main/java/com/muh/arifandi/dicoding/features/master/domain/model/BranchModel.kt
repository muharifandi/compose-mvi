/**
 * Created by Muh. Arifandi on 25/05/2026.
 * Email : arif76440@gmail.com
 * Project: SakaAndroid
 * File: BranchModel
 */
package com.muh.arifandi.dicoding.features.master.domain.model

data class BranchModel(
    val id: String,
    val name: String,
    val address: String,
    val distance: String,
    val latitude: Double,
    val longitude: Double,
    val imageUrl: String? = null
)
