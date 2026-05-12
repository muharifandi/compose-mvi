/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : features:home
 * File : NewsDatabase.kt
 *
 * Description:
 * Definisi Room Database untuk fitur berita.
 */

package com.muh.arifandi.dicoding.features.home.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.muh.arifandi.dicoding.features.home.data.local.dao.ArticleDao
import com.muh.arifandi.dicoding.features.home.data.local.dao.FavoriteDao
import com.muh.arifandi.dicoding.features.home.data.local.entity.ArticleEntity
import com.muh.arifandi.dicoding.features.home.data.local.entity.FavoriteEntity

@Database(
    entities = [ArticleEntity::class, FavoriteEntity::class],
    version = 2,
    exportSchema = false
)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
    abstract fun favoriteDao(): FavoriteDao
}
