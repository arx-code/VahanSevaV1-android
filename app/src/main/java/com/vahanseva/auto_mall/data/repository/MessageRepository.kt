package com.vahanseva.auto_mall.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.vahanseva.auto_mall.data.local.MessageDao
import com.vahanseva.auto_mall.data.local.ConversationDao
import com.vahanseva.auto_mall.data.model.Conversation
import com.vahanseva.auto_mall.data.model.Message
import com.vahanseva.auto_mall.data.remote.MessageService
import com.vahanseva.auto_mall.data.remote.CreateConversationRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Repository for messaging between users
 */
class MessageRepository @Inject constructor(
    private val messageService: MessageService,
    private val messageDao: MessageDao,
    private val conversationDao: ConversationDao
) {
    companion object {
        private const val PAGING_SIZE = 50
    }

    /**
     * Send a message
     */
    fun sendMessage(message: Message, token: String): Flow<Result<Message>> = flow {
        try {
            emit(Result.Loading)
            val sentMessage = messageService.sendMessage(message, "Bearer $token")
            messageDao.insertMessage(sentMessage)
            emit(Result.Success(sentMessage))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    /**
     * Get conversation messages with pagination
     */
    fun getConversationMessages(conversationId: String): Flow<List<Message>> =
        messageDao.getConversationMessages(conversationId)

    /**
     * Get user conversations
     */
    fun getUserConversations(userId: String): Flow<List<Conversation>> =
        conversationDao.getUserConversations(userId)

    /**
     * Get or create conversation with another user for a car
     */
    fun getOrCreateConversation(
        userId: String,
        carId: String?,
        token: String
    ): Flow<Result<Conversation>> = flow {
        try {
            emit(Result.Loading)
            val request = CreateConversationRequest(userId, carId)
            val conversation = messageService.getOrCreateConversation(request, "Bearer $token")
            conversationDao.insertConversation(conversation)
            emit(Result.Success(conversation))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    /**
     * Mark messages in conversation as read
     */
    fun markConversationAsRead(
        conversationId: String,
        userId: String,
        token: String
    ): Flow<Result<Unit>> = flow {
        try {
            emit(Result.Loading)
            messageService.markConversationAsRead(conversationId, "Bearer $token")
            messageDao.markConversationAsRead(conversationId, userId)
            emit(Result.Success(Unit))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    /**
     * Delete a message
     */
    fun deleteMessage(messageId: String, token: String): Flow<Result<Unit>> = flow {
        try {
            emit(Result.Loading)
            messageService.deleteMessage(messageId, "Bearer $token")
            messageDao.deleteMessageById(messageId)
            emit(Result.Success(Unit))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    /**
     * Get conversation for a specific car
     */
    fun getConversationForCar(userId: String, carId: String): Flow<Conversation?> =
        conversationDao.getConversationForCar(userId, carId)

    /**
     * Sync conversations from server
     */
    fun syncConversations(token: String, page: Int = 1): Flow<Result<Unit>> = flow {
        try {
            emit(Result.Loading)
            val response = messageService.getUserConversations(page, 20, "Bearer $token")
            conversationDao.let { dao ->
                response.conversations.forEach { conversation ->
                    dao.insertConversation(conversation)
                }
            }
            emit(Result.Success(Unit))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}
