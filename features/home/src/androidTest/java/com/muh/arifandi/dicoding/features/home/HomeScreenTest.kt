/**
 * Created by Muh. Arifandi on 07/05/26.
 * Email : arif76440@gmail.com
 * Project: My Application
 * File: HomeScreenTest
 */
package com.muh.arifandi.dicoding.features.home

import androidx.compose.ui.test.junit4.createComposeRule
import com.muh.arifandi.dicoding.core.testing.robot.HomeRobot
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val homeRobot = HomeRobot(composeTestRule)

    @Test
    fun whenScreenLaunched_shouldDisplayAppTitle() {
        composeTestRule.setContent {
            HomeScreen(
                onNavigateToDetail = {},
                onNavigateToAbout = {},
                onNavigateToBookmark = {}
            )
        }
        
        homeRobot.verifyNewsAppTitleVisible()
    }
}
