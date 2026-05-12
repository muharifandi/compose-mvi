package com.muh.arifandi.dicoding.core.testing.robot

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.ComposeTestRule

/**
 * Robot Pattern untuk mengabstraksi interaksi UI di HomeScreen.
 * Meningkatkan keterbacaan dan pemeliharaan tes UI.
 */
class HomeRobot(private val composeTestRule: ComposeTestRule) {

    fun verifyNewsAppTitleVisible() {
        composeTestRule.onNodeWithText("News App").assertIsDisplayed()
    }

    fun enterSearchQuery(query: String) {
        composeTestRule.onNodeWithText("Search news...").performTextInput(query)
    }

    fun submitSearch() {
        composeTestRule.onNodeWithText("Search news...").performImeAction()
    }

    fun verifyHomeScreenVisible() {
        composeTestRule.onNodeWithText("News App").assertIsDisplayed()
    }

    fun searchNews(query: String) {
        composeTestRule.onNodeWithText("Search news...").performTextInput(query)
        composeTestRule.onNodeWithText("Search news...").performImeAction()
    }

    fun clickAboutButton() {
        composeTestRule.onNodeWithContentDescription("about_page").performClick()
    }

    fun clickBookmarkButton() {
        composeTestRule.onNodeWithContentDescription("bookmarks").performClick()
    }

    fun clickFirstArticle() {
        composeTestRule.onAllNodesWithTag("article_item")
            .onFirst()
            .performClick()
    }

    fun verifyArticleVisible(title: String) {
        composeTestRule.onNodeWithText(title).assertExists()
    }

    fun verifyLoadingVisible() {
        composeTestRule.onNodeWithTag("loading_view").assertIsDisplayed()
    }
}

fun HomeRobot.verifyEmptyStateVisible() {
}
