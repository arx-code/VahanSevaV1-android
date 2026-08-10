package com.vahanseva.auto_mall.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vahanseva.auto_mall.data.model.Conversation
import com.vahanseva.auto_mall.data.model.Message
import com.vahanseva.auto_mall.data.model.MessageType
import com.vahanseva.auto_mall.data.repository.MessageRepository
import com.vahanseva.auto_mall.data.repository.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

/**
 * ViewModel for messaging/chat screen
 */
@HiltViewModel
class MessageViewModel @Inject constructor(
    private val messageRepository: MessageRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val conversationId: String = savedStateHandle.get<String>("conversationId") ?: ""

    private val _uiState = MutableStateFlow<MessageUiState>(MessageUiState.Loading)
    val uiState: StateFlow<MessageUiState> = _uiState.asStateFlow()

    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages: StateFlow<List<Message>> = _messages.asStateFlow()

    private val _conversations = MutableStateFlow<List<Conversation>>(emptyList())
    val conversations: StateFlow<List<Conversation>> = _conversations.asStateFlow()

    init {
        if (conversationId.isNotEmpty()) {
            loadConversationMessages()
        }
    }

    /**
     * Load messages for a conversation
     */
    private fun loadConversationMessages() {
        viewModelScope.launch {
            _uiState.value = MessageUiState.Loading
            try {
                messageRepository.getConversationMessages(conversationId).collectLatest { messages ->
                    _messages.value = messages
                    _uiState.value = MessageUiState.Success
                }
            } catch (e: Exception) {
                _uiState.value = MessageUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    /**
     * Load user's conversations
     */
    fun loadConversations(userId: String) {
        viewModelScope.launch {
            try {
                messageRepository.getUserConversations(userId).collectLatest { conversations ->
                    _conversations.value = conversations
                }
            } catch (e: Exception) {
                _uiState.value = MessageUiState.Error("Failed to load conversations")
            }
        }
    }

    /**
     * Send a message
     */
    fun sendMessage(
        senderId: String,
        receiverId: String,
        messageText: String,
        carId: String?,
        token: String
    ) {
        viewModelScope.launch {
            val message = Message(
                id = UUID.randomUUID().toString(),
                conversationId = conversationId,
                senderId = senderId,
                receiverId = receiverId,
                carId = carId,
                message = messageText,
                timestamp = System.currentTimeMillis(),
                isRead = false,
                messageType = MessageType.TEXT
            )

            messageRepository.sendMessage(message, token).collectLatest { result ->
                when (result) {
                    is Result.Success -> {
                        // Message sent successfully, reload messages
                        loadConversationMessages()
                    }
                    is Result.Error -> {
                        _uiState.value = MessageUiState.Error("Failed to send message")
                    }
                    is Result.Loading -> {}
                }
            }
        }
    }

    /**
     * Create or get conversation with another user
     */
    fun getOrCreateConversation(userId: String, carId: String?, token: String) {
        viewModelScope.launch {
            messageRepository.getOrCreateConversation(userId, carId, token).collectLatest { result ->
                when (result) {
                    is Result.Success -> {
                        // Conversation created or retrieved
                        loadConversationMessages()
                    }
                    is Result.Error -> {
                        _uiState.value = MessageUiState.Error("Failed to create conversation")
                    }
                    is Result.Loading -> {}
                }
            }
        }
    }

    /**
     * Mark conversation as read
     */
    fun markConversationAsRead(userId: String, token: String) {
        viewModelScope.launch {
            messageRepository.markConversationAsRead(conversationId, userId, token).collectLatest { result ->
                when (result) {
                    is Result.Success -> loadConversationMessages()
                    is Result.Error -> {}
                    is Result.Loading -> {}
                }
            }
        }
    }

    /**
     * Delete a message
     */
    fun deleteMessage(messageId: String, token: String) {
        viewModelScope.launch {
            messageRepository.deleteMessage(messageId, token).collectLatest { result ->
                when (result) {
                    is Result.Success -> loadConversationMessages()
                    is Result.Error -> _uiState.value = MessageUiState.Error("Failed to delete message")
                    is Result.Loading -> {}
                }
            }
        }
    }
}

/**
 * UI State for messaging screen
 */
sealed class MessageUiState {
    object Loading : MessageUiState()
    object Success : MessageUiState()
    data class Error(val message: String) : MessageUiState()
}
