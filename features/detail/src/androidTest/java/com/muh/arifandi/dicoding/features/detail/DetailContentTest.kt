/**
 * Created by Muh. Arifandi on 07/05/26.
 * Email : arif76440@gmail.com
 * Project: My Application
 * File: DetailContentTest
 */
package com.muh.arifandi.dicoding.features.detail

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.muh.arifandi.dicoding.domain.news.model.Article
import com.muh.arifandi.dicoding.features.detail.state.DetailState
import org.junit.Rule
import org.junit.Test

class DetailContentTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun detailContent_displaysArticleTitle() {
        val article = Article(
            title = "Testing Title",
            description = "Desc",
            url = "https://test.com",
            imageUrl = "https://img.com",
            author = "Author",
            publishedAt = "2024-05-26"
        )
        val state = DetailState(
            article = article,
            url = article.url
        )

        composeTestRule.setContent {
            DetailContent(
                state = state,
                onBackClick = {},
                onFavoriteClick = {}
            )
        }

        // Karena sekarang menggunakan WebView, kita memverifikasi metadata di TopAppBar
        composeTestRule.onNodeWithText("Testing Title").assertExists()
    }
}
