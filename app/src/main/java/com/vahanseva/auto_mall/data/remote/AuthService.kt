package com.vahanseva.auto_mall.data.remote

import com.vahanseva.auto_mall.data.model.AuthResponse
import com.vahanseva.auto_mall.data.model.Car
import com.vahanseva.auto_mall.data.model.LoginRequest
import com.vahanseva.auto_mall.data.model.RegisterRequest
import com.vahanseva.auto_mall.data.model.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit API service for authentication endpoints
 */
interface AuthService {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): AuthResponse

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): AuthResponse

    @POST("auth/logout")
    suspend fun logout(@Header("Authorization") token: String)

    @GET("auth/profile")
    suspend fun getProfile(@Header("Authorization") token: String): User

    @POST("auth/refresh-token")
    suspend fun refreshToken(@Query("refresh_token") refreshToken: String): AuthResponse
}

/**
 * Retrofit API service for user endpoints
 */
interface UserService {
    @GET("users/{userId}")
    suspend fun getUserById(
        @Path("userId") userId: String,
        @Header("Authorization") token: String
    ): User

    @POST("users/profile")
    suspend fun updateProfile(
        @Body user: User,
        @Header("Authorization") token: String
    ): User

    @GET("users/{userId}/ratings")
    suspend fun getUserRating(
        @Path("userId") userId: String,
        @Header("Authorization") token: String
    ): UserRatingResponse
}

data class UserRatingResponse(
    val averageRating: Float,
    val totalReviews: Int,
    val soldCars: Int
)
