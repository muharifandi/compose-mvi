/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : core:database
 * File : ArticleEntity.kt
 *
 * Description:
 * Entitas database untuk penyimpanan cache artikel berita.
 */

package com.muh.arifandi.dicoding.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class ArticleEntity(
    @PrimaryKey
    val url: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val author: String,
    val publishedAt: String,
    val category: String? = null
)
