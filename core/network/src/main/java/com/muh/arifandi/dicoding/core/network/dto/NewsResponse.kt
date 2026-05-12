/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : core:network
 * File : NewsResponse.kt
 *
 * Description:
 * Data Transfer Object (DTO) untuk representasi respon list berita dari API eksternal.
 */

package com.muh.arifandi.dicoding.core.network.dto

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("totalResults")
    val totalResults: Int? = null,
    @SerializedName("articles")
    val articles: List<ArticleResponse> = emptyList()
)
