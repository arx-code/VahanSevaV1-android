package com.vahanseva.auto_mall.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Favorite Entity for saved car listings
 */
@Entity(tableName = "favorites")
data class Favorite(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @SerializedName("user_id")
    val userId: String,

    @SerializedName("car_id")
    val carId: String,

    @SerializedName("saved_at")
    val savedAt: Long
)
