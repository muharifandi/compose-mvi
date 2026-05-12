/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : domain:news
 * File : GetTopHeadlinesUseCase.kt
 *
 * Description:
 * Use Case untuk mendapatkan berita terpopuler (Top Headlines) dengan dukungan Paging.
 */

package com.muh.arifandi.dicoding.domain.news.usecase

import androidx.paging.PagingData
import com.muh.arifandi.dicoding.domain.news.model.Article
import com.muh.arifandi.dicoding.domain.news.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopHeadlinesUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    fun getPaged(category: String? = null): Flow<PagingData<Article>> {
        return repository.getPagedTopHeadlines(category)
    }

    operator fun invoke(category: String? = null, page: Int = 1) = 
        repository.getTopHeadlines(category, page)
}
