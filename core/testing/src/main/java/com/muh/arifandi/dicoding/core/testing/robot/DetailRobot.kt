package com.muh.arifandi.dicoding.core.testing.robot

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.ComposeTestRule

class DetailRobot(private val composeTestRule: ComposeTestRule) {

    fun verifyDetailScreenVisible() {
        composeTestRule.onNodeWithText("Article Detail").assertIsDisplayed()
    }

    fun verifyArticleTitle(title: String) {
        composeTestRule.onNodeWithText(title).assertIsDisplayed()
    }

    fun clickFavoriteButton() {
        composeTestRule.onNodeWithContentDescription("Favorite").performClick()
    }

    fun clickBackButton() {
        composeTestRule.onNodeWithContentDescription("Back").performClick()
    }
}
