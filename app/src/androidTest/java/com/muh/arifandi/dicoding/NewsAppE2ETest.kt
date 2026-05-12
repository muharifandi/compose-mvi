package com.muh.arifandi.dicoding

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.muh.arifandi.dicoding.core.testing.robot.BookmarkRobot
import com.muh.arifandi.dicoding.core.testing.robot.DetailRobot
import com.muh.arifandi.dicoding.core.testing.robot.HomeRobot
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NewsAppE2ETest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private val homeRobot = HomeRobot(composeTestRule)
    private val detailRobot = DetailRobot(composeTestRule)
    private val bookmarkRobot = BookmarkRobot(composeTestRule)

    @Test
    fun fullAppFlow_SaveToFavorite_And_DeleteFromBookmark() {
        composeTestRule.waitUntil(10000) {
            try {
                homeRobot.verifyHomeScreenVisible()
                true
            } catch (e: Exception) {
                false
            }
        }

        homeRobot.searchNews("NASA")
        
        homeRobot.clickFirstArticle()
        
        detailRobot.verifyDetailScreenVisible()
        detailRobot.clickFavoriteButton()
        
        detailRobot.clickBackButton()
        
        homeRobot.clickBookmarkButton()
        
        bookmarkRobot.verifyFavoritesScreenVisible()
        bookmarkRobot.clickDeleteArticle()
        bookmarkRobot.verifyDeleteDialogVisible()
        bookmarkRobot.clickConfirmDelete()
        
        bookmarkRobot.verifyEmptyState("No favorite articles yet")
    }

    @Test
    fun navigateToAboutAndBack() {
        composeTestRule.waitUntil(10000) {
            try {
                homeRobot.verifyHomeScreenVisible()
                true
            } catch (e: Exception) {
                false
            }
        }

        homeRobot.clickAboutButton()
        
        composeTestRule.onNodeWithText("About Developer").assertExists()
        
        composeTestRule.onNodeWithContentDescription("Back").performClick()
        homeRobot.verifyHomeScreenVisible()
    }
}
