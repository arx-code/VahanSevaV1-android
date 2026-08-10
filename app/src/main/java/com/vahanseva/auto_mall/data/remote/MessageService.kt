package com.vahanseva.auto_mall.data.remote

import com.vahanseva.auto_mall.data.model.Conversation
import com.vahanseva.auto_mall.data.model.Message
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit API service for messaging endpoints
 */
interface MessageService {
    @POST("messages")
    suspend fun sendMessage(
        @Body message: Message,
        @Header("Authorization") token: String
    ): Message

    @GET("messages/conversation/{conversationId}")
    suspend fun getConversationMessages(
        @Path("conversationId") conversationId: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int = 50,
        @Header("Authorization") token: String
    ): MessagesResponse

    @POST("messages/conversations")
    suspend fun getOrCreateConversation(
        @Body request: CreateConversationRequest,
        @Header("Authorization") token: String
    ): Conversation

    @GET("messages/conversations")
    suspend fun getUserConversations(
        @Query("page") page: Int,
        @Query("limit") limit: Int = 20,
        @Header("Authorization") token: String
    ): ConversationsResponse

    @GET("messages/conversations/{conversationId}")
    suspend fun getConversation(
        @Path("conversationId") conversationId: String,
        @Header("Authorization") token: String
    ): Conversation

    @POST("messages/{messageId}/read")
    suspend fun markMessageAsRead(
        @Path("messageId") messageId: String,
        @Header("Authorization") token: String
    )

    @POST("messages/conversation/{conversationId}/read")
    suspend fun markConversationAsRead(
        @Path("conversationId") conversationId: String,
        @Header("Authorization") token: String
    )

    @DELETE("messages/{messageId}")
    suspend fun deleteMessage(
        @Path("messageId") messageId: String,
        @Header("Authorization") token: String
    )
}

data class CreateConversationRequest(
    val userId: String,
    val carId: String? = null
)

data class MessagesResponse(
    val messages: List<Message>,
    val page: Int,
    val totalPages: Int,
    val hasMore: Boolean
)

data class ConversationsResponse(
    val conversations: List<Conversation>,
    val page: Int,
    val totalPages: Int,
    val hasMore: Boolean
)
