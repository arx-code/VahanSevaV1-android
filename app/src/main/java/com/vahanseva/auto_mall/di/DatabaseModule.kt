package com.vahanseva.auto_mall.di

import android.content.Context
import com.vahanseva.auto_mall.data.local.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module for providing Room database and DAOs
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideVahanSevaDatabase(
        @ApplicationContext context: Context
    ): VahanSevaDatabase {
        return VahanSevaDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideUserDao(database: VahanSevaDatabase): UserDao {
        return database.userDao()
    }

    @Provides
    @Singleton
    fun provideCarDao(database: VahanSevaDatabase): CarDao {
        return database.carDao()
    }

    @Provides
    @Singleton
    fun provideMessageDao(database: VahanSevaDatabase): MessageDao {
        return database.messageDao()
    }

    @Provides
    @Singleton
    fun provideConversationDao(database: VahanSevaDatabase): ConversationDao {
        return database.conversationDao()
    }

    @Provides
    @Singleton
    fun provideFavoriteDao(database: VahanSevaDatabase): FavoriteDao {
        return database.favoriteDao()
    }
}
