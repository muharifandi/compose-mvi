package com.muh.arifandi.dicoding.core.testing.data

import com.muh.arifandi.dicoding.core.model.Article

object TestArticleData {
    val dummyArticle = Article(
        title = "Android Jetpack",
        description = "Modern Android Development",
        url = "https://developer.android.com",
        imageUrl = "https://image.com",
        author = "Google",
        publishedAt = "2024-05-26T10:00:00Z"
    )
}
