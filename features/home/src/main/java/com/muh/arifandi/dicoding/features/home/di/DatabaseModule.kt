/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : features:home
 * File : DatabaseModule.kt
 *
 * Description:
 * Module Hilt untuk menyediakan instance database dan DAO spesifik fitur berita.
 */

package com.muh.arifandi.dicoding.features.home.di

import android.content.Context
import androidx.room.Room
import com.muh.arifandi.dicoding.features.home.data.local.NewsDatabase
import com.muh.arifandi.dicoding.features.home.data.local.dao.ArticleDao
import com.muh.arifandi.dicoding.features.home.data.local.dao.FavoriteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NewsDatabase {
        return Room.databaseBuilder(
            context,
            NewsDatabase::class.java,
            "news.db"
        )
        .fallbackToDestructiveMigration()
        .build()
    }

    @Provides
    @Singleton
    fun provideArticleDao(database: NewsDatabase): ArticleDao {
        return database.articleDao()
    }

    @Provides
    @Singleton
    fun provideFavoriteDao(database: NewsDatabase): FavoriteDao {
        return database.favoriteDao()
    }
}
