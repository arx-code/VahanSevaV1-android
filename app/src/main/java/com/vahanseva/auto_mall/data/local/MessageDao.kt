package com.vahanseva.auto_mall.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vahanseva.auto_mall.data.model.Conversation
import com.vahanseva.auto_mall.data.model.Message
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for Message entity
 */
@Dao
interface MessageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: Message)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessages(messages: List<Message>)

    @Query("SELECT * FROM messages WHERE id = :messageId")
    fun getMessageById(messageId: String): Flow<Message?>

    @Query("SELECT * FROM messages WHERE conversation_id = :conversationId ORDER BY timestamp DESC")
    fun getConversationMessages(conversationId: String): Flow<List<Message>>

    @Query("UPDATE messages SET is_read = 1 WHERE conversation_id = :conversationId AND receiver_id = :userId")
    suspend fun markConversationAsRead(conversationId: String, userId: String)

    @Delete
    suspend fun deleteMessage(message: Message)

    @Query("DELETE FROM messages WHERE id = :messageId")
    suspend fun deleteMessageById(messageId: String)
}

/**
 * Data Access Object for Conversation entity
 */
@Dao
interface ConversationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConversation(conversation: Conversation)

    @Query("SELECT * FROM conversations WHERE id = :conversationId")
    fun getConversationById(conversationId: String): Flow<Conversation?>

    @Query("""
        SELECT * FROM conversations
        WHERE (user1_id = :userId OR user2_id = :userId)
        ORDER BY last_message_time DESC
    """)
    fun getUserConversations(userId: String): Flow<List<Conversation>>

    @Query("""
        SELECT * FROM conversations
        WHERE (user1_id = :userId OR user2_id = :userId)
        AND car_id = :carId
        LIMIT 1
    """)
    fun getConversationForCar(userId: String, carId: String): Flow<Conversation?>

    @Query("UPDATE conversations SET last_message = :lastMessage, last_message_time = :lastMessageTime WHERE id = :conversationId")
    suspend fun updateLastMessage(conversationId: String, lastMessage: String, lastMessageTime: Long)

    @Delete
    suspend fun deleteConversation(conversation: Conversation)

    @Query("DELETE FROM conversations WHERE id = :conversationId")
    suspend fun deleteConversationById(conversationId: String)
}
