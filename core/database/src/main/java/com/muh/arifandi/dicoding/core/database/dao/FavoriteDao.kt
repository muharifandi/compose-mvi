/**
 * Created by Muh. Arifandi on 12/05/2026
 * Email : arif76440@gmail.com
 * Project : My Application
 * Module : core:database
 * File : FavoriteDao.kt
 *
 * Description:
 * Data Access Object (DAO) untuk pengelolaan daftar artikel favorit di database local.
 */

package com.muh.arifandi.dicoding.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.muh.arifandi.dicoding.core.database.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorites ORDER BY createdAt DESC")
    fun getAllFavorites(): Flow<List<FavoriteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: FavoriteEntity)

    @Query("DELETE FROM favorites WHERE url = :url")
    suspend fun deleteFavoriteByUrl(url: String)

    @Query("SELECT COUNT(*) FROM favorites WHERE url = :url")
    fun isFavorite(url: String): Flow<Int>

    @Query("SELECT * FROM favorites WHERE url = :url")
    fun getFavoriteByUrl(url: String): Flow<FavoriteEntity?>
}
