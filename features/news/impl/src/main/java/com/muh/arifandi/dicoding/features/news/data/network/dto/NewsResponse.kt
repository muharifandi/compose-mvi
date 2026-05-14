package com.muh.arifandi.dicoding.features.news.data.network.dto

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("articles") val articles: List<ArticleResponse>,
    @SerializedName("status") val status: String,
    @SerializedName("totalResults") val totalResults: Int
)

data class ArticleResponse(
    @SerializedName("author") val author: String?,
    @SerializedName("content") val content: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("publishedAt") val publishedAt: String?,
    @SerializedName("source") val source: SourceResponse?,
    @SerializedName("title") val title: String?,
    @SerializedName("url") val url: String,
    @SerializedName("urlToImage") val urlToImage: String?
)

data class SourceResponse(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?
)
