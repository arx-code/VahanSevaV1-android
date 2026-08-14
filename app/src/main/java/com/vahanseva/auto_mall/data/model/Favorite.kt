package com.vahanseva.auto_mall.data.model

import androidx.room.ColumnInfo
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

    @ColumnInfo(name = "user_id")
    @SerializedName("user_id")
    val userId: String,

    @ColumnInfo(name = "car_id")
    @SerializedName("car_id")
    val carId: String,

    @ColumnInfo(name = "saved_at")
    @SerializedName("saved_at")
    val savedAt: Long
)
