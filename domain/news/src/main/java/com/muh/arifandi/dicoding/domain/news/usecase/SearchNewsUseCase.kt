/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : domain:news
 * File : SearchNewsUseCase.kt
 *
 * Description:
 * Use Case untuk melakukan pencarian berita berdasarkan kata kunci.
 */

package com.muh.arifandi.dicoding.domain.news.usecase

import com.muh.arifandi.dicoding.domain.news.repository.NewsRepository
import javax.inject.Inject

class SearchNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(query: String, page: Int = 1) = 
        repository.searchNews(query, page)
}
