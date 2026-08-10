package com.vahanseva.auto_mall.data.model

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

    @SerializedName("conversation_id")
    val conversationId: String,

    @SerializedName("sender_id")
    val senderId: String,

    @SerializedName("receiver_id")
    val receiverId: String,

    @SerializedName("car_id")
    val carId: String?, // Optional: associated car listing

    @SerializedName("message")
    val message: String,

    @SerializedName("timestamp")
    val timestamp: Long,

    @SerializedName("is_read")
    val isRead: Boolean = false,

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

    @SerializedName("user1_id")
    val user1Id: String,

    @SerializedName("user2_id")
    val user2Id: String,

    @SerializedName("car_id")
    val carId: String?,

    @SerializedName("last_message")
    val lastMessage: String,

    @SerializedName("last_message_time")
    val lastMessageTime: Long,

    @SerializedName("unread_count")
    val unreadCount: Int = 0
)
