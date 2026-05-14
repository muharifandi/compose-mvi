package com.muh.arifandi.dicoding.features.news.domain.usecase

import com.muh.arifandi.dicoding.core.model.ResultState
import com.muh.arifandi.dicoding.features.news.domain.model.Article
import com.muh.arifandi.dicoding.features.news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopHeadlinesUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(category: String?, page: Int): Flow<ResultState<List<Article>>> {
        return repository.getTopHeadlines(category = category, page = page)
    }
}
