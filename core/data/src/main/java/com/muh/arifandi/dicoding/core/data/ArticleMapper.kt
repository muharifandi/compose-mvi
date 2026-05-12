/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : core:data
 * File : ArticleMapper.kt
 *
 * Description:
 * Mapper utility untuk mengubah data antara DTO, Entity, dan Domain Model.
 */

package com.muh.arifandi.dicoding.core.data

import com.muh.arifandi.dicoding.core.database.entity.ArticleEntity
import com.muh.arifandi.dicoding.core.database.entity.FavoriteEntity
import com.muh.arifandi.dicoding.core.network.dto.ArticleResponse
import com.muh.arifandi.dicoding.core.model.Article

fun ArticleResponse.toDomain(): Article {
    return Article(
        title = title.orEmpty(),
        description = description.orEmpty(),
        url = url.orEmpty(),
        imageUrl = urlToImage.orEmpty(),
        author = author ?: "Unknown Author",
        publishedAt = publishedAt.orEmpty()
    )
}

fun ArticleResponse.toEntity(category: String? = null): ArticleEntity {
    return ArticleEntity(
        url = url.orEmpty(),
        title = title.orEmpty(),
        description = description.orEmpty(),
        imageUrl = urlToImage.orEmpty(),
        author = author ?: "Unknown Author",
        publishedAt = publishedAt.orEmpty(),
        category = category
    )
}

fun ArticleEntity.toDomain(): Article {
    return Article(
        url = url,
        title = title,
        description = description,
        imageUrl = imageUrl,
        author = author,
        publishedAt = publishedAt
    )
}

fun Article.toFavoriteEntity(): FavoriteEntity {
    return FavoriteEntity(
        url = url,
        title = title,
        description = description,
        imageUrl = imageUrl,
        author = author,
        publishedAt = publishedAt
    )
}

fun FavoriteEntity.toDomain(): Article {
    return Article(
        url = url,
        title = title,
        description = description,
        imageUrl = imageUrl,
        author = author,
        publishedAt = publishedAt
    )
}
