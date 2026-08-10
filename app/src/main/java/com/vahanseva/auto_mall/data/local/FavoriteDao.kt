package com.vahanseva.auto_mall.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vahanseva.auto_mall.data.model.Favorite
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for Favorite entity
 */
@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favorite)

    @Query("SELECT * FROM favorites WHERE user_id = :userId ORDER BY saved_at DESC")
    fun getUserFavorites(userId: String): Flow<List<Favorite>>

    @Query("SELECT * FROM favorites WHERE user_id = :userId AND car_id = :carId")
    fun getFavorite(userId: String, carId: String): Flow<Favorite?>

    @Query("SELECT COUNT(*) FROM favorites WHERE user_id = :userId AND car_id = :carId")
    suspend fun isFavorite(userId: String, carId: String): Int

    @Query("DELETE FROM favorites WHERE user_id = :userId AND car_id = :carId")
    suspend fun removeFavorite(userId: String, carId: String)

    @Delete
    suspend fun deleteFavorite(favorite: Favorite)

    @Query("DELETE FROM favorites WHERE user_id = :userId")
    suspend fun deleteAllUserFavorites(userId: String)
}
