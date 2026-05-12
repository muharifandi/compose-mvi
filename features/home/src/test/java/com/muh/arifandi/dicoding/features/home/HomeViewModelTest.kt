package com.muh.arifandi.dicoding.features.home

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import androidx.paging.PagingData
import com.muh.arifandi.dicoding.core.common.ResultState
import com.muh.arifandi.dicoding.core.testing.data.TestArticleData
import com.muh.arifandi.dicoding.domain.news.usecase.GetTopHeadlinesUseCase
import com.muh.arifandi.dicoding.domain.news.usecase.SearchNewsUseCase
import com.muh.arifandi.dicoding.features.home.state.HomeEffect
import com.muh.arifandi.dicoding.features.home.state.HomeIntent
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel
    private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase = mockk()
    private val searchNewsUseCase: SearchNewsUseCase = mockk()

    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        every { getTopHeadlinesUseCase.getPaged(any()) } returns flowOf(PagingData.empty())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when SearchArticle intent is processed, state should update and save to SavedStateHandle`() = runTest {
        val query = "Jetpack"
        val articles = listOf(TestArticleData.dummyArticle)
        val savedStateHandle = SavedStateHandle()
        
        coEvery { searchNewsUseCase(query, 1) } returns flowOf(
            ResultState.Loading,
            ResultState.Success(articles)
        )

        viewModel = HomeViewModel(getTopHeadlinesUseCase, searchNewsUseCase, savedStateHandle)

        viewModel.state.test {
            awaitItem() // Initial
            viewModel.processIntent(HomeIntent.SearchArticle(query))
            
            assertEquals(true, awaitItem().isLoading)
            val successState = awaitItem()
            assertEquals(articles, successState.filteredArticles)
            assertEquals(query, savedStateHandle.get<String>("query"))
        }
    }

    @Test
    fun `when ClickArticle intent is processed, NavigateToDetail effect should be sent`() = runTest {
        val article = TestArticleData.dummyArticle
        viewModel = HomeViewModel(getTopHeadlinesUseCase, searchNewsUseCase, SavedStateHandle())

        viewModel.effect.test {
            viewModel.processIntent(HomeIntent.ClickArticle(article))
            val effect = awaitItem()
            assert(effect is HomeEffect.NavigateToDetail)
            assertEquals(article.url, (effect as HomeEffect.NavigateToDetail).url)
        }
    }
}
