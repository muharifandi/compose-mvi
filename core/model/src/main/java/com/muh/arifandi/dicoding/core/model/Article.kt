/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : core:model
 * File : Article.kt
 *
 * Description:
 * Model domain murni (Pure Kotlin) untuk representasi artikel berita.
 */

package com.muh.arifandi.dicoding.core.model

data class Article(
    val url: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val author: String,
    val publishedAt: String
)
