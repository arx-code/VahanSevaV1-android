package com.vahanseva.auto_mall.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Car Entity for Room Database
 * Represents a used car listing in the marketplace
 */
@Entity(tableName = "cars")
data class Car(
    @PrimaryKey
    @SerializedName("id")
    val id: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("brand")
    val brand: String,

    @SerializedName("model")
    val model: String,

    @SerializedName("year")
    val year: Int,

    @SerializedName("price")
    val price: Double,

    @SerializedName("mileage")
    val mileage: Int, // in kilometers

    @ColumnInfo(name = "fuel_type")
    @SerializedName("fuel_type")
    val fuelType: String, // Petrol, Diesel, Electric, Hybrid

    @SerializedName("transmission")
    val transmission: String, // Manual, Automatic

    @SerializedName("color")
    val color: String,

    @SerializedName("owner_count")
    val ownerCount: Int, // 1st owner, 2nd owner, etc.

    @SerializedName("description")
    val description: String,

    @SerializedName("location")
    val location: String,

    @ColumnInfo(name = "seller_id")
    @SerializedName("seller_id")
    val sellerId: String,

    @SerializedName("seller_name")
    val sellerName: String,

    @SerializedName("images")
    val images: List<String>, // URLs to car images

    @ColumnInfo(name = "is_featured")
    @SerializedName("is_featured")
    val isFeatured: Boolean = false,

    @SerializedName("is_verified")
    val isVerified: Boolean = false,

    @ColumnInfo(name = "created_at")
    @SerializedName("created_at")
    val createdAt: Long,

    @ColumnInfo(name = "updated_at")
    @SerializedName("updated_at")
    val updatedAt: Long,

    // Local-only fields
    var isFavorite: Boolean = false
)
