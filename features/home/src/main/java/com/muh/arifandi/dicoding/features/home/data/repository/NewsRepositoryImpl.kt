/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : features:home
 * File : NewsRepositoryImpl.kt
 *
 * Description:
 * Implementasi repository untuk fitur berita yang menangani sinkronisasi data 
 * antara API (Remote) dan Database (Local).
 */

package com.muh.arifandi.dicoding.features.home.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.muh.arifandi.dicoding.core.common.ResultState
import com.muh.arifandi.dicoding.features.home.data.local.dao.ArticleDao
import com.muh.arifandi.dicoding.features.home.data.local.dao.FavoriteDao
import com.muh.arifandi.dicoding.features.home.data.mapper.toDomain
import com.muh.arifandi.dicoding.features.home.data.mapper.toEntity
import com.muh.arifandi.dicoding.features.home.data.mapper.toFavoriteEntity
import com.muh.arifandi.dicoding.features.home.data.remote.api.NewsApiService
import com.muh.arifandi.dicoding.domain.news.model.Article
import com.muh.arifandi.dicoding.domain.news.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val apiService: NewsApiService,
    private val articleDao: ArticleDao,
    private val favoriteDao: FavoriteDao
) : NewsRepository {

    override fun getTopHeadlines(
        category: String?,
        page: Int
    ): Flow<ResultState<List<Article>>> = flow {
        emit(ResultState.Loading)
        try {
            val response = apiService.getTopHeadlines(
                category = category,
                page = page
            ).articles

            val entities = response.map { it.toEntity(category) }

            if (page == 1) {
                articleDao.deleteArticlesByCategory(category)
            }
            articleDao.insertArticles(entities)
            
            emit(ResultState.Success(response.map { it.toDomain() }))

        } catch (e: Exception) {
            emit(ResultState.Error(e.message ?: "Unknown Error"))
        }
    }

    override fun getPagedTopHeadlines(
        category: String?
    ): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                NewsPagingSource(apiService, articleDao, category)
            }
        ).flow
    }

    override fun searchNews(
        query: String,
        page: Int
    ): Flow<ResultState<List<Article>>> = flow {
        emit(ResultState.Loading)
        try {
            val response = apiService.searchNews(
                query = query,
                page = page
            ).articles

            articleDao.insertArticles(response.map { it.toEntity("search") })

            emit(ResultState.Success(response.map { it.toDomain() }))
        } catch (e: Exception) {
            emit(ResultState.Error(e.message ?: "Unknown Error"))
        }
    }

    @OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
    override fun getArticleByUrl(url: String): Flow<Article?> {
        val cachedFlow = articleDao.getArticleByUrl(url).map { it?.toDomain() }
        val favoriteFlow = favoriteDao.getFavoriteByUrl(url).map { it?.toDomain() }
        
        return combine(cachedFlow, favoriteFlow) { cached, favorite ->
            cached ?: favorite
        }.distinctUntilChanged()
    }

    override fun getAllFavorites(): Flow<List<Article>> {
        return favoriteDao.getAllFavorites().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun isFavorite(url: String): Flow<Boolean> {
        return favoriteDao.isFavorite(url).map { it > 0 }
    }

    override suspend fun saveFavorite(article: Article) {
        favoriteDao.insertFavorite(article.toFavoriteEntity())
    }

    override suspend fun deleteFavorite(url: String) {
        favoriteDao.deleteFavoriteByUrl(url)
    }
}
