/**
 * Created by Muh. Arifandi on 07/05/26.
 * Email : arif76440@gmail.com
 * Project: My Application
 * File: AboutScreenTest
 */
package com.muh.arifandi.dicoding.features.about

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.muh.arifandi.dicoding.features.about.state.AboutState
import org.junit.Rule
import org.junit.Test

class AboutScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun aboutScreen_displaysDeveloperInfo() {
        val state = AboutState(
            name = "Muh. Arifandi",
            email = "arif76440@gmail.com"
        )

        composeTestRule.setContent {
            AboutContent(
                state = state,
                onBackClick = {}
            )
        }

        composeTestRule.onNodeWithText("Muh. Arifandi").assertIsDisplayed()
        composeTestRule.onNodeWithText("arif76440@gmail.com").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("about_page").assertIsDisplayed()
    }
}
