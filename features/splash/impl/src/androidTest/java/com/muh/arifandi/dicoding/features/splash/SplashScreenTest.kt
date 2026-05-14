/**
 * Created by Muh. Arifandi on 07/05/26.
 * Email : arif76440@gmail.com
 * Project: My Application
 * File: SplashScreenTest
 */
package com.muh.arifandi.dicoding.features.splash

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.NavController
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test

class SplashScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun splashScreen_displaysAppTitle() {
        val navController: NavController = mockk(relaxed = true)
        
        composeTestRule.setContent {
            SplashScreen(navController = navController)
        }

        composeTestRule.onNodeWithText("ArifNews").assertIsDisplayed()
    }
}
