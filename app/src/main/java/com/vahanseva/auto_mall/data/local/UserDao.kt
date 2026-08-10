package com.vahanseva.auto_mall.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.vahanseva.auto_mall.data.model.User
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for User entity
 */
@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM users WHERE id = :userId")
    fun getUserById(userId: String): Flow<User?>

    @Query("SELECT * FROM users WHERE email = :email")
    fun getUserByEmail(email: String): Flow<User?>

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM users WHERE id = :userId")
    suspend fun deleteUserById(userId: String)
}
