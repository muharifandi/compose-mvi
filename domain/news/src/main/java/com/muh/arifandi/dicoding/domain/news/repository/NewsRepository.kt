/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : domain:news
 * File : NewsRepository.kt
 *
 * Description:
 * Interface kontrak repository untuk abstraksi pengelolaan data berita.
 */

package com.muh.arifandi.dicoding.domain.news.repository

import androidx.paging.PagingData
import com.muh.arifandi.dicoding.core.common.ResultState
import com.muh.arifandi.dicoding.domain.news.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getTopHeadlines(
        category: String?,
        page: Int
    ): Flow<ResultState<List<Article>>>

    fun getPagedTopHeadlines(
        category: String?
    ): Flow<PagingData<Article>>

    fun searchNews(
        query: String,
        page: Int
    ): Flow<ResultState<List<Article>>>

    fun getArticleByUrl(url: String): Flow<Article?>

    fun getAllFavorites(): Flow<List<Article>>
    fun isFavorite(url: String): Flow<Boolean>
    suspend fun saveFavorite(article: Article)
    suspend fun deleteFavorite(url: String)
}
