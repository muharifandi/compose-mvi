package com.muh.arifandi.dicoding.domain.news.usecase

import androidx.paging.PagingData
import com.muh.arifandi.dicoding.domain.news.repository.NewsRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Test

class GetTopHeadlinesUseCaseTest {

    private lateinit var getTopHeadlinesUseCase: GetTopHeadlinesUseCase
    private val repository: NewsRepository = mockk()

    @Before
    fun setUp() {
        getTopHeadlinesUseCase = GetTopHeadlinesUseCase(repository)
    }

    @Test
    fun `getPaged should call repository getPagedTopHeadlines`() {
        val category = "business"
        every { repository.getPagedTopHeadlines(category) } returns flowOf(PagingData.empty())

        getTopHeadlinesUseCase.getPaged(category)

        verify { repository.getPagedTopHeadlines(category) }
    }
}
