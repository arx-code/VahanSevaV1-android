package com.vahanseva.auto_mall.data.remote

import com.vahanseva.auto_mall.data.model.Favorite
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Retrofit API service for favorites/wishlist endpoints
 */
interface FavoriteService {
    @POST("favorites")
    suspend fun addFavorite(
        @Query("carId") carId: String,
        @Header("Authorization") token: String
    ): Favorite

    @GET("favorites")
    suspend fun getUserFavorites(
        @Query("page") page: Int,
        @Query("limit") limit: Int = 20,
        @Header("Authorization") token: String
    ): FavoritesResponse

    @DELETE("favorites")
    suspend fun removeFavorite(
        @Query("carId") carId: String,
        @Header("Authorization") token: String
    )

    @GET("favorites/check")
    suspend fun isFavorite(
        @Query("carId") carId: String,
        @Header("Authorization") token: String
    ): IsFavoriteResponse
}

data class FavoritesResponse(
    val favorites: List<Favorite>,
    val page: Int,
    val totalPages: Int,
    val hasMore: Boolean
)

data class IsFavoriteResponse(
    val isFavorite: Boolean
)
