package com.muh.arifandi.dicoding.features.bookmark

import app.cash.turbine.test
import com.muh.arifandi.dicoding.core.testing.data.TestArticleData
import com.muh.arifandi.dicoding.domain.news.repository.NewsRepository
import com.muh.arifandi.dicoding.features.bookmark.state.BookmarkEffect
import com.muh.arifandi.dicoding.features.bookmark.state.BookmarkIntent
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

@OptIn(ExperimentalCoroutinesApi::class)
class BookmarkViewModelTest {

    private lateinit var viewModel: BookmarkViewModel
    private val repository: NewsRepository = mockk(relaxed = true)
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when initialized, should load favorites automatically`() = runTest {
        val favorites = listOf(TestArticleData.dummyArticle)
        coEvery { repository.getAllFavorites() } returns flowOf(favorites)

        viewModel = BookmarkViewModel(repository)

        viewModel.state.test {
            var state = awaitItem()
            while (state.favoriteArticles.isEmpty()) {
                state = awaitItem()
            }
            assertEquals(favorites, state.favoriteArticles)
        }
    }

    @Test
    fun `when DeleteFavorite intent is processed, should call repository delete`() = runTest {
        val url = TestArticleData.dummyArticle.url
        viewModel = BookmarkViewModel(repository)

        viewModel.processIntent(BookmarkIntent.DeleteFavorite(url))
        testDispatcher.scheduler.advanceUntilIdle()

        coVerify { repository.deleteFavorite(url) }
    }

    @Test
    fun `when ClickArticle intent is processed, should send NavigateToDetail effect`() = runTest {
        val article = TestArticleData.dummyArticle
        viewModel = BookmarkViewModel(repository)

        viewModel.effect.test {
            viewModel.processIntent(BookmarkIntent.ClickArticle(article))
            val effect = awaitItem()
            assert(effect is BookmarkEffect.NavigateToDetail)
            assertEquals(article.url, (effect as BookmarkEffect.NavigateToDetail).url)
        }
    }
}
