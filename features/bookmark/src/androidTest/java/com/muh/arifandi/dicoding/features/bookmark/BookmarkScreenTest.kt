package com.muh.arifandi.dicoding.features.bookmark

import androidx.compose.ui.test.junit4.createComposeRule
import com.muh.arifandi.dicoding.core.testing.data.TestArticleData
import com.muh.arifandi.dicoding.core.testing.robot.BookmarkRobot
import com.muh.arifandi.dicoding.features.bookmark.state.BookmarkState
import org.junit.Rule
import org.junit.Test

class BookmarkScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val robot = BookmarkRobot(composeTestRule)

    @Test
    fun bookmarkContent_displaysFavorites() {
        val articles = listOf(TestArticleData.dummyArticle)
        val state = BookmarkState(favoriteArticles = articles)

        composeTestRule.setContent {
            BookmarkContent(
                state = state,
                onBackClick = {},
                onArticleClick = {},
                onDeleteClick = {}
            )
        }

        robot.verifyFavoritesScreenVisible()
        robot.verifyArticleVisible(articles[0].title)
    }

    @Test
    fun bookmarkContent_displaysEmptyState() {
        val state = BookmarkState(favoriteArticles = emptyList())

        composeTestRule.setContent {
            BookmarkContent(
                state = state,
                onBackClick = {},
                onArticleClick = {},
                onDeleteClick = {}
            )
        }

        robot.verifyEmptyState("No favorite articles yet")
    }

    @Test
    fun bookmarkContent_showsDeleteDialog() {
        val articles = listOf(TestArticleData.dummyArticle)
        val state = BookmarkState(favoriteArticles = articles)

        composeTestRule.setContent {
            BookmarkContent(
                state = state,
                onBackClick = {},
                onArticleClick = {},
                onDeleteClick = {}
            )
        }

        robot.clickDeleteArticle()
        robot.verifyDeleteDialogVisible()
    }
}
