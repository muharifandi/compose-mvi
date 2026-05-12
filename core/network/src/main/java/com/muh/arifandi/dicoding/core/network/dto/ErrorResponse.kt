package com.muh.arifandi.dicoding.core.network.dto

import com.google.gson.annotations.SerializedName

/**
 * Created by Foundation Team
 * Representasi objek error dari NewsAPI sesuai dokumentasi.
 */
data class ErrorResponse(
    @SerializedName("status") val status: String,
    @SerializedName("code") val code: String,
    @SerializedName("message") val message: String
)
