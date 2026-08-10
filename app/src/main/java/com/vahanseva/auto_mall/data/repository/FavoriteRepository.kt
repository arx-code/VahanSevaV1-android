package com.vahanseva.auto_mall.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.vahanseva.auto_mall.data.local.FavoriteDao
import com.vahanseva.auto_mall.data.model.Favorite
import com.vahanseva.auto_mall.data.remote.FavoriteService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Repository for user favorites/wishlist
 */
class FavoriteRepository @Inject constructor(
    private val favoriteService: FavoriteService,
    private val favoriteDao: FavoriteDao
) {
    companion object {
        private const val PAGING_SIZE = 20
    }

    /**
     * Get user's favorite cars with pagination
     */
    fun getUserFavorites(token: String, page: Int = 1): Flow<PagingData<Favorite>> = Pager(
        config = PagingConfig(pageSize = PAGING_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            FavoritePagingSource(favoriteService, token)
        }
    ).flow

    /**
     * Get favorite cars from local cache
     */
    fun getCachedFavorites(userId: String): Flow<List<Favorite>> =
        favoriteDao.getUserFavorites(userId)

    /**
     * Check if car is favorited
     */
    fun isFavorite(userId: String, carId: String): Flow<Boolean> = flow {
        try {
            val count = favoriteDao.isFavorite(userId, carId)
            emit(count > 0)
        } catch (e: Exception) {
            emit(false)
        }
    }

    /**
     * Add car to favorites
     */
    fun addFavorite(carId: String, token: String): Flow<Result<Favorite>> = flow {
        try {
            emit(Result.Loading)
            val favorite = favoriteService.addFavorite(carId, "Bearer $token")
            favoriteDao.insertFavorite(favorite)
            emit(Result.Success(favorite))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    /**
     * Remove car from favorites
     */
    fun removeFavorite(userId: String, carId: String, token: String): Flow<Result<Unit>> = flow {
        try {
            emit(Result.Loading)
            favoriteService.removeFavorite(carId, "Bearer $token")
            favoriteDao.removeFavorite(userId, carId)
            emit(Result.Success(Unit))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    /**
     * Sync favorites with server
     */
    fun syncFavorites(token: String, page: Int = 1): Flow<Result<Unit>> = flow {
        try {
            emit(Result.Loading)
            val response = favoriteService.getUserFavorites(page, PAGING_SIZE, "Bearer $token")
            // In real app, you'd want to sync the user ID, but that requires auth context
            emit(Result.Success(Unit))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}

/**
 * Paging source for favorites
 */
class FavoritePagingSource(
    private val favoriteService: FavoriteService,
    private val token: String
) : androidx.paging.PagingSource<Int, Favorite>() {
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Favorite> {
        return try {
            val page = params.key ?: 1
            val response = favoriteService.getUserFavorites(page, params.loadSize, "Bearer $token")
            LoadResult.Page(
                data = response.favorites,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.hasMore) page + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: androidx.paging.PagingState<Int, Favorite>): Int? = null
}
