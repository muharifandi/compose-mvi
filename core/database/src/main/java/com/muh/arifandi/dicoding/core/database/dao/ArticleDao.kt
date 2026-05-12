/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : core:database
 * File : ArticleDao.kt
 *
 * Description:
 * Data Access Object (DAO) untuk pengelolaan cache artikel di database local.
 */

package com.muh.arifandi.dicoding.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.muh.arifandi.dicoding.core.database.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Query("SELECT * FROM articles WHERE category = :category")
    fun getArticlesByCategory(category: String?): Flow<List<ArticleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<ArticleEntity>)

    @Query("DELETE FROM articles WHERE category = :category")
    suspend fun deleteArticlesByCategory(category: String?)

    @Query("SELECT * FROM articles WHERE url = :url")
    fun getArticleByUrl(url: String): Flow<ArticleEntity?>
}
