package com.vahanseva.auto_mall.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vahanseva.auto_mall.data.model.User
import com.vahanseva.auto_mall.data.repository.AuthRepository
import com.vahanseva.auto_mall.data.repository.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for authentication (login/register)
 */
@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

    private val _authToken = MutableStateFlow<String?>(null)
    val authToken: StateFlow<String?> = _authToken.asStateFlow()

    /**
     * Login user with email and password
     */
    fun login(email: String, password: String) {
        viewModelScope.launch {
            authRepository.login(email, password).collectLatest { result ->
                when (result) {
                    is Result.Loading -> _uiState.value = AuthUiState.Loading
                    is Result.Success -> {
                        _currentUser.value = result.data.user
                        _authToken.value = result.data.token
                        _uiState.value = AuthUiState.Success("Login successful")
                    }
                    is Result.Error -> {
                        _uiState.value = AuthUiState.Error(result.exception.message ?: "Login failed")
                    }
                }
            }
        }
    }

    /**
     * Register new user
     */
    fun register(
        email: String,
        password: String,
        name: String,
        phone: String,
        location: String
    ) {
        viewModelScope.launch {
            authRepository.register(email, password, name, phone, location).collectLatest { result ->
                when (result) {
                    is Result.Loading -> _uiState.value = AuthUiState.Loading
                    is Result.Success -> {
                        _currentUser.value = result.data.user
                        _authToken.value = result.data.token
                        _uiState.value = AuthUiState.Success("Registration successful")
                    }
                    is Result.Error -> {
                        _uiState.value = AuthUiState.Error(result.exception.message ?: "Registration failed")
                    }
                }
            }
        }
    }

    /**
     * Logout user
     */
    fun logout() {
        viewModelScope.launch {
            val token = _authToken.value
            if (token != null) {
                authRepository.logout(token).collectLatest { result ->
                    when (result) {
                        is Result.Success -> {
                            _currentUser.value = null
                            _authToken.value = null
                            _uiState.value = AuthUiState.Idle
                        }
                        is Result.Error -> {
                            _uiState.value = AuthUiState.Error("Logout failed")
                        }
                        is Result.Loading -> {}
                    }
                }
            }
        }
    }

    /**
     * Get current user profile
     */
    fun getProfile() {
        viewModelScope.launch {
            val token = _authToken.value
            if (token != null) {
                authRepository.getProfile(token).collectLatest { result ->
                    when (result) {
                        is Result.Success -> {
                            _currentUser.value = result.data
                        }
                        is Result.Error -> {
                            _uiState.value = AuthUiState.Error("Failed to fetch profile")
                        }
                        is Result.Loading -> {}
                    }
                }
            }
        }
    }

    /**
     * Clear error state
     */
    fun clearError() {
        _uiState.value = AuthUiState.Idle
    }

    /**
     * Check if user is logged in
     */
    fun isLoggedIn(): Boolean = _authToken.value != null
}

/**
 * UI State for authentication
 */
sealed class AuthUiState {
    object Idle : AuthUiState()
    object Loading : AuthUiState()
    data class Success(val message: String) : AuthUiState()
    data class Error(val message: String) : AuthUiState()
}
