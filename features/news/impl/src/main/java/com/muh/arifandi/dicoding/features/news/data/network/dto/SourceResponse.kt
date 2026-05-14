package com.muh.arifandi.dicoding.features.news.data.network.dto

import com.google.gson.annotations.SerializedName

data class SourcesResponse(
    @SerializedName("status") val status: String,
    @SerializedName("sources") val sources: List<SourceDetailResponse>
)

data class SourceDetailResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("url") val url: String,
    @SerializedName("category") val category: String,
    @SerializedName("language") val language: String,
    @SerializedName("country") val country: String
)
