package com.vahanseva.auto_mall.di

import com.vahanseva.auto_mall.data.local.*
import com.vahanseva.auto_mall.data.remote.*
import com.vahanseva.auto_mall.data.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module for providing repositories
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        authService: AuthService,
        userService: UserService,
        userDao: UserDao
    ): AuthRepository {
        return AuthRepository(authService, userService, userDao)
    }

    @Provides
    @Singleton
    fun provideCarRepository(
        carService: CarService,
        carDao: CarDao
    ): CarRepository {
        return CarRepository(carService, carDao)
    }

    @Provides
    @Singleton
    fun provideMessageRepository(
        messageService: MessageService,
        messageDao: MessageDao,
        conversationDao: ConversationDao
    ): MessageRepository {
        return MessageRepository(messageService, messageDao, conversationDao)
    }

    @Provides
    @Singleton
    fun provideFavoriteRepository(
        favoriteService: FavoriteService,
        favoriteDao: FavoriteDao
    ): FavoriteRepository {
        return FavoriteRepository(favoriteService, favoriteDao)
    }
}
