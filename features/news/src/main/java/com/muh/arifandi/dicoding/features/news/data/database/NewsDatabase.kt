package com.muh.arifandi.dicoding.features.news.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.muh.arifandi.dicoding.features.news.data.database.dao.ArticleDao
import com.muh.arifandi.dicoding.features.news.data.database.entity.ArticleEntity

@Database(
    entities = [ArticleEntity::class],
    version = 3,
    exportSchema = false
)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}
