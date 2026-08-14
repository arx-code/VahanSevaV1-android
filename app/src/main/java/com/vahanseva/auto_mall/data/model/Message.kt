package com.vahanseva.auto_mall.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Message Entity for chat between buyers and sellers
 */
@Entity(tableName = "messages")
data class Message(
    @PrimaryKey
    @SerializedName("id")
    val id: String,

    @ColumnInfo(name = "conversation_id")
    @SerializedName("conversation_id")
    val conversationId: String,

    @ColumnInfo(name = "sender_id")
    @SerializedName("sender_id")
    val senderId: String,

    @ColumnInfo(name = "receiver_id")
    @SerializedName("receiver_id")
    val receiverId: String,

    @ColumnInfo(name = "car_id")
    @SerializedName("car_id")
    val carId: String?, // Optional: associated car listing

    @SerializedName("message")
    val message: String,

    @SerializedName("timestamp")
    val timestamp: Long,

    @ColumnInfo(name = "is_read")
    @SerializedName("is_read")
    val isRead: Boolean = false,

    @ColumnInfo(name = "message_type")
    @SerializedName("message_type")
    val messageType: MessageType = MessageType.TEXT
)

enum class MessageType {
    TEXT,
    IMAGE,
    CAR_LISTING_SHARE
}

/**
 * Conversation represents a chat thread between two users
 */
@Entity(tableName = "conversations")
data class Conversation(
    @PrimaryKey
    @SerializedName("id")
    val id: String,

    @ColumnInfo(name = "user1_id")
    @SerializedName("user1_id")
    val user1Id: String,

    @ColumnInfo(name = "user2_id")
    @SerializedName("user2_id")
    val user2Id: String,

    @ColumnInfo(name = "car_id")
    @SerializedName("car_id")
    val carId: String?,

    @ColumnInfo(name = "last_message")
    @SerializedName("last_message")
    val lastMessage: String,

    @ColumnInfo(name = "last_message_time")
    @SerializedName("last_message_time")
    val lastMessageTime: Long,

    @ColumnInfo(name = "unread_count")
    @SerializedName("unread_count")
    val unreadCount: Int = 0
)
