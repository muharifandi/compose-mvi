/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : core:network
 * File : ArticleResponse.kt
 *
 * Description:
 * Data Transfer Object (DTO) untuk representasi artikel dari API eksternal.
 */

package com.muh.arifandi.dicoding.core.network.dto

import com.google.gson.annotations.SerializedName

data class ArticleResponse(
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("urlToImage")
    val urlToImage: String? = null,
    @SerializedName("author")
    val author: String? = null,
    @SerializedName("publishedAt")
    val publishedAt: String? = null
)
