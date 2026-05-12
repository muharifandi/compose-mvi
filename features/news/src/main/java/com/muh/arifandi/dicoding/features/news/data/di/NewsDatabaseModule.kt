package com.muh.arifandi.dicoding.features.news.data.di

import android.content.Context
import androidx.room.Room
import com.muh.arifandi.dicoding.features.news.data.database.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsDatabaseModule {

    @Provides
    @Singleton
    fun provideNewsDatabase(@ApplicationContext context: Context): NewsDatabase {
        return Room.databaseBuilder(
            context,
            NewsDatabase::class.java,
            "news_db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideArticleDao(database: NewsDatabase) = database.articleDao()
}
