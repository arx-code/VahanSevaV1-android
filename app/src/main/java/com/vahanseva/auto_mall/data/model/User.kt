package com.vahanseva.auto_mall.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * User Entity for Room Database
 * Represents a user in the marketplace (buyer or seller)
 */
@Entity(tableName = "users")
data class User(
    @PrimaryKey
    @SerializedName("id")
    val id: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("phone")
    val phone: String,

    @SerializedName("profile_image")
    val profileImage: String? = null,

    @SerializedName("location")
    val location: String,

    @SerializedName("is_verified")
    val isVerified: Boolean = false,

    @SerializedName("created_at")
    val createdAt: Long,

    @SerializedName("rating")
    val rating: Float = 0f, // Average rating from other users

    @SerializedName("total_listings")
    val totalListings: Int = 0,

    @SerializedName("sold_cars")
    val soldCars: Int = 0
)

/**
 * Authentication request/response models
 */
data class LoginRequest(
    val email: String,
    val password: String
)

data class RegisterRequest(
    val email: String,
    val password: String,
    val name: String,
    val phone: String,
    val location: String
)

data class AuthResponse(
    @SerializedName("user")
    val user: User,

    @SerializedName("token")
    val token: String,

    @SerializedName("refresh_token")
    val refreshToken: String
)
