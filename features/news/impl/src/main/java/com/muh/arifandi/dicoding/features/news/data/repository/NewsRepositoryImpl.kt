package com.muh.arifandi.dicoding.features.news.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.muh.arifandi.dicoding.core.model.ResultState
import com.muh.arifandi.dicoding.core.common.repository.BaseRepository
import com.muh.arifandi.dicoding.core.network.util.SafeApiCall
import com.muh.arifandi.dicoding.features.news.data.database.dao.ArticleDao
import com.muh.arifandi.dicoding.features.news.data.mapper.toDomain
import com.muh.arifandi.dicoding.features.news.data.database.entity.ArticleEntity
import com.muh.arifandi.dicoding.features.news.data.network.api.NewsApiService
import com.muh.arifandi.dicoding.features.news.domain.model.Article
import com.muh.arifandi.dicoding.features.news.domain.model.Source
import com.muh.arifandi.dicoding.features.news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val apiService: NewsApiService,
    private val articleDao: ArticleDao,
    private val safeApiCall: SafeApiCall
) : BaseRepository(), NewsRepository, NewsPagingRepository {

    override fun getTopHeadlines(
        category: String?,
        country: String?,
        query: String?,
        page: Int
    ): Flow<ResultState<List<Article>>> = 
        safeApiCall.flow {
            apiService.getTopHeadlines(
                category = category,
                country = country,
                query = query,
                page = page
            ).articles.map { it.toDomain() }
        }

    override fun getPagedTopHeadlines(category: String?): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { NewsPagingSource(apiService, category) }
        ).flow
    }

    override fun searchNews(
        query: String,
        language: String?,
        sortBy: String?,
        page: Int
    ): Flow<ResultState<List<Article>>> =
        safeApiCall.flow {
            apiService.searchNews(
                query = query,
                language = language,
                sortBy = sortBy,
                page = page
            ).articles.map { it.toDomain() }
        }

    override fun getSources(
        category: String?,
        language: String?,
        country: String?
    ): Flow<ResultState<List<Source>>> =
        safeApiCall.flow {
            apiService.getSources(
                category = category,
                language = language,
                country = country
            ).sources.map { it.toDomain() }
        }

    override fun getArticleByUrl(url: String): Flow<Article?> {
        // Implementation logic
        return articleDao.getAllArticles().map { list -> list.find { it.url == url }?.toDomain() }
    }

    override fun getAllFavorites(): Flow<List<Article>> =
        articleDao.getAllArticles().map { list -> list.map { it.toDomain() } }

    override fun isFavorite(url: String): Flow<Boolean> =
        articleDao.getAllArticles().map { list -> list.any { it.url == url } }

    override suspend fun saveFavorite(article: Article) {
        // Logic to save favorite
    }

    override suspend fun deleteFavorite(url: String) {
        // Logic to delete favorite
    }
}
