/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : core:data
 * File : NewsPagingSource.kt
 *
 * Description:
 * Implementasi PagingSource untuk mendukung pagination berita dari API.
 */

package com.muh.arifandi.dicoding.core.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.muh.arifandi.dicoding.core.network.api.NewsApiService
import com.muh.arifandi.dicoding.core.database.dao.ArticleDao
import com.muh.arifandi.dicoding.core.model.Article

internal class NewsPagingSource(
    private val apiService: NewsApiService,
    private val articleDao: ArticleDao,
    private val category: String?
) : PagingSource<Int, Article>() {

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val position = params.key ?: 1
        return try {
            val response = apiService.getTopHeadlines(
                category = category,
                page = position,
                pageSize = params.loadSize
            ).articles

            articleDao.insertArticles(response.map { it.toEntity(category) })

            val articles = response.map { it.toDomain() }

            LoadResult.Page(
                data = articles,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (articles.isEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
