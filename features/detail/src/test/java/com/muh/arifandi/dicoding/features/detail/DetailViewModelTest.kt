package com.muh.arifandi.dicoding.features.detail

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.muh.arifandi.dicoding.core.testing.data.TestArticleData
import com.muh.arifandi.dicoding.domain.news.repository.NewsRepository
import com.muh.arifandi.dicoding.features.detail.state.DetailEffect
import com.muh.arifandi.dicoding.features.detail.state.DetailIntent
import io.mockk.coEvery
import io.mockk.coVerify
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

class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val repository: NewsRepository = mockk(relaxed = true)
    private val testDispatcher = StandardTestDispatcher()
    
    private val dummyArticle = TestArticleData.dummyArticle

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when LoadArticle intent is processed, state should update correctly`() = runTest {
        val url = dummyArticle.url
        coEvery { repository.getArticleByUrl(url) } returns flowOf(dummyArticle)
        coEvery { repository.isFavorite(url) } returns flowOf(true)

        viewModel = DetailViewModel(repository, SavedStateHandle())

        viewModel.state.test {
            assertEquals(null, awaitItem().article)
            viewModel.processIntent(DetailIntent.LoadArticle(url))
            var state = awaitItem()
            while (state.article == null && state.error == null) {
                state = awaitItem()
            }
            assertEquals(dummyArticle, state.article)
            assertEquals(true, state.isFavorite)
        }
    }

    @Test
    fun `when ToggleFavorite is clicked and not favorite, it should call saveFavorite`() = runTest {
        coEvery { repository.getArticleByUrl(any()) } returns flowOf(dummyArticle)
        coEvery { repository.isFavorite(any()) } returns flowOf(false)

        viewModel = DetailViewModel(repository, SavedStateHandle())
        viewModel.processIntent(DetailIntent.LoadArticle(dummyArticle.url))
        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.effect.test {
            viewModel.processIntent(DetailIntent.ToggleFavorite)
            val effect = awaitItem()
            assert(effect is DetailEffect.ShowToast)
            assertEquals("Added to favorites", (effect as DetailEffect.ShowToast).message)
            
            coVerify(exactly = 1) { repository.saveFavorite(any()) }
        }
    }
}
