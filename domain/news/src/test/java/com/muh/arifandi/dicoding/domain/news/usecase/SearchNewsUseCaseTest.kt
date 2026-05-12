package com.muh.arifandi.dicoding.domain.news.usecase

import com.muh.arifandi.dicoding.core.common.ResultState
import com.muh.arifandi.dicoding.domain.news.repository.NewsRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Test

class SearchNewsUseCaseTest {

    private lateinit var searchNewsUseCase: SearchNewsUseCase
    private val repository: NewsRepository = mockk()

    @Before
    fun setUp() {
        searchNewsUseCase = SearchNewsUseCase(repository)
    }

    @Test
    fun `invoke should call repository searchNews`() {
        val query = "Android"
        val page = 1
        every { repository.searchNews(query, page) } returns flowOf(ResultState.Success(emptyList()))

        searchNewsUseCase(query, page)

        verify { repository.searchNews(query, page) }
    }
}
