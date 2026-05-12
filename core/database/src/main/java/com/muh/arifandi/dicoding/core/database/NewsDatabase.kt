/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : core:database
 * File : NewsDatabase.kt
 *
 * Description:
 * Definisi Room Database untuk fitur berita.
 */

package com.muh.arifandi.dicoding.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.muh.arifandi.dicoding.core.database.dao.ArticleDao
import com.muh.arifandi.dicoding.core.database.dao.FavoriteDao
import com.muh.arifandi.dicoding.core.database.entity.ArticleEntity
import com.muh.arifandi.dicoding.core.database.entity.FavoriteEntity

@Database(
    entities = [ArticleEntity::class, FavoriteEntity::class],
    version = 2,
    exportSchema = false
)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
    abstract fun favoriteDao(): FavoriteDao
}
