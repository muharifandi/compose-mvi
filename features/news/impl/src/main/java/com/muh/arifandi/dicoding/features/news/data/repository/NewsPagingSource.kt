package com.muh.arifandi.dicoding.features.news.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.muh.arifandi.dicoding.features.news.data.mapper.toDomain
import com.muh.arifandi.dicoding.features.news.data.network.api.NewsApiService
import com.muh.arifandi.dicoding.core.model.Article

class NewsPagingSource(
    private val apiService: NewsApiService,
    private val category: String?
) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val position = params.key ?: 1
        return try {
            val response = apiService.getTopHeadlines(
                category = category,
                page = position
            )
            val articles = response.articles.map { it.toDomain() }
            
            LoadResult.Page(
                data = articles,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (articles.isEmpty()) null else position + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
