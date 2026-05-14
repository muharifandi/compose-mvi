package com.muh.arifandi.dicoding.features.news.data.network.api

import com.muh.arifandi.dicoding.features.news.data.network.dto.NewsResponse
import com.muh.arifandi.dicoding.features.news.data.network.dto.SourcesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String? = "us",
        @Query("category") category: String? = null,
        @Query("sources") sources: String? = null,
        @Query("q") query: String? = null,
        @Query("pageSize") pageSize: Int? = 20,
        @Query("page") page: Int? = 1
    ): NewsResponse

    @GET("everything")
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("searchIn") searchIn: String? = null,
        @Query("sources") sources: String? = null,
        @Query("domains") domains: String? = null,
        @Query("from") from: String? = null,
        @Query("to") to: String? = null,
        @Query("language") language: String? = "en",
        @Query("sortBy") sortBy: String? = "publishedAt",
        @Query("pageSize") pageSize: Int? = 20,
        @Query("page") page: Int? = 1
    ): NewsResponse

    @GET("top-headlines/sources")
    suspend fun getSources(
        @Query("category") category: String? = null,
        @Query("language") language: String? = null,
        @Query("country") country: String? = null
    ): SourcesResponse
}
