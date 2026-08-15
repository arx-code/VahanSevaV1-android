package com.vahanseva.auto_mall.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import com.vahanseva.auto_mall.data.model.User

/**
 * Type converters for Room database
 * Handles conversion of complex types like List<String> and User object to/from database-compatible formats
 */
object Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromStringList(value: List<String>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toStringList(value: String): List<String>? {
        return if (value.isEmpty()) {
            null
        } else {
            val type = object : TypeToken<List<String>>() {}.type
            gson.fromJson(value, type)
        }
    }

    @TypeConverter
    fun fromUser(user: User?): String? {
        return gson.toJson(user)
    }

    @TypeConverter
    fun toUser(value: String?): User? {
        return gson.fromJson(value, User::class.java)
    }
}
