package com.muh.arifandi.dicoding.features.news.domain.repository

import com.muh.arifandi.dicoding.core.model.ResultState
import com.muh.arifandi.dicoding.core.model.Article
import com.muh.arifandi.dicoding.features.news.domain.model.Source
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getTopHeadlines(
        category: String? = null,
        country: String? = "us",
        query: String? = null,
        page: Int = 1
    ): Flow<ResultState<List<Article>>>

    fun searchNews(
        query: String,
        language: String? = "en",
        sortBy: String? = "publishedAt",
        page: Int = 1
    ): Flow<ResultState<List<Article>>>

    fun getSources(
        category: String? = null,
        language: String? = null,
        country: String? = null
    ): Flow<ResultState<List<Source>>>

    fun getArticleByUrl(url: String): Flow<Article?>
    fun getAllFavorites(): Flow<List<Article>>
    fun isFavorite(url: String): Flow<Boolean>
    suspend fun saveFavorite(article: Article)
    suspend fun deleteFavorite(url: String)
}
