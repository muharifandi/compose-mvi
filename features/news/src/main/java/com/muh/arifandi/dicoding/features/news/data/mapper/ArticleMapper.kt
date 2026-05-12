package com.muh.arifandi.dicoding.features.news.data.mapper

import com.muh.arifandi.dicoding.features.news.data.database.entity.ArticleEntity
import com.muh.arifandi.dicoding.features.news.data.network.dto.ArticleResponse
import com.muh.arifandi.dicoding.features.news.data.network.dto.SourceDetailResponse
import com.muh.arifandi.dicoding.features.news.domain.model.Article
import com.muh.arifandi.dicoding.features.news.domain.model.Source

fun ArticleResponse.toDomain() = Article(
    author = author,
    content = content,
    description = description,
    publishedAt = publishedAt,
    sourceName = source?.name,
    title = title,
    url = url,
    urlToImage = urlToImage
)

fun SourceDetailResponse.toDomain() = Source(
    id = id,
    name = name,
    description = description,
    url = url,
    category = category,
    language = language,
    country = country
)

fun ArticleEntity.toDomain() = Article(
    author = author,
    content = content,
    description = description,
    publishedAt = publishedAt,
    sourceName = sourceName,
    title = title,
    url = url,
    urlToImage = urlToImage
)

fun Article.toEntity() = ArticleEntity(
    author = author,
    content = content,
    description = description,
    publishedAt = publishedAt,
    sourceName = sourceName,
    title = title,
    url = url,
    urlToImage = urlToImage
)
