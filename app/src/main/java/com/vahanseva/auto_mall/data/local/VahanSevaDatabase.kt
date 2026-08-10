package com.vahanseva.auto_mall.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vahanseva.auto_mall.data.model.Car
import com.vahanseva.auto_mall.data.model.Conversation
import com.vahanseva.auto_mall.data.model.Favorite
import com.vahanseva.auto_mall.data.model.Message
import com.vahanseva.auto_mall.data.model.User

/**
 * Room Database for VahanSeva app
 * Contains all entities and DAOs
 */
@Database(
    entities = [
        User::class,
        Car::class,
        Message::class,
        Conversation::class,
        Favorite::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class VahanSevaDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun carDao(): CarDao
    abstract fun messageDao(): MessageDao
    abstract fun conversationDao(): ConversationDao
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        private const val DATABASE_NAME = "vahanseva_db"

        @Volatile
        private var instance: VahanSevaDatabase? = null

        fun getInstance(context: Context): VahanSevaDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): VahanSevaDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                VahanSevaDatabase::class.java,
                DATABASE_NAME
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}
