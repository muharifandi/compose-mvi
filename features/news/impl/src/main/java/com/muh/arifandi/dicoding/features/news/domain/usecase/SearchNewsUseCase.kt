package com.muh.arifandi.dicoding.features.news.domain.usecase

import com.muh.arifandi.dicoding.features.news.domain.repository.NewsRepository
import javax.inject.Inject

class SearchNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(query: String, page: Int = 1) = 
        repository.searchNews(query = query, page = page)
}
