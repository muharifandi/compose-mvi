package com.muh.arifandi.dicoding.features.news.data.repository

import androidx.paging.PagingData
import com.muh.arifandi.dicoding.features.news.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsPagingRepository {
    fun getPagedTopHeadlines(category: String? = null): Flow<PagingData<Article>>
}
