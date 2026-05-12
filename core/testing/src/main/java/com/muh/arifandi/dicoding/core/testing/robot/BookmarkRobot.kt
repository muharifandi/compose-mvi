package com.muh.arifandi.dicoding.core.testing.robot

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick

class BookmarkRobot(private val composeTestRule: ComposeTestRule) {

    fun verifyFavoritesScreenVisible() {
        composeTestRule.onNodeWithText("Favorites").assertIsDisplayed()
    }

    fun verifyEmptyState(message: String) {
        composeTestRule.onNodeWithText(message).assertIsDisplayed()
    }

    fun verifyArticleVisible(title: String) {
        composeTestRule.onNodeWithText(title).assertIsDisplayed()
    }

    fun clickDeleteArticle() {
        composeTestRule.onNodeWithContentDescription("Delete Favorite").performClick()
    }

    fun verifyDeleteDialogVisible() {
        composeTestRule.onNodeWithText("Hapus Favorit").assertIsDisplayed()
    }

    fun clickConfirmDelete() {
        composeTestRule.onNodeWithText("Hapus").performClick()
    }
}
