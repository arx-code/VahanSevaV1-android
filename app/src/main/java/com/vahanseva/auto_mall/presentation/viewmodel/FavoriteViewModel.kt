package com.vahanseva.auto_mall.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vahanseva.auto_mall.data.model.Favorite
import com.vahanseva.auto_mall.data.repository.FavoriteRepository
import com.vahanseva.auto_mall.data.repository.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for user's favorite cars
 */
@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<FavoriteUiState>(FavoriteUiState.Loading)
    val uiState: StateFlow<FavoriteUiState> = _uiState.asStateFlow()

    private val _favorites = MutableStateFlow<List<Favorite>>(emptyList())
    val favorites: StateFlow<List<Favorite>> = _favorites.asStateFlow()

    /**
     * Load user's favorite cars
     */
    fun loadFavorites(userId: String) {
        viewModelScope.launch {
            _uiState.value = FavoriteUiState.Loading
            try {
                favoriteRepository.getCachedFavorites(userId).collectLatest { favorites ->
                    _favorites.value = favorites
                    _uiState.value = FavoriteUiState.Success
                }
            } catch (e: Exception) {
                _uiState.value = FavoriteUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    /**
     * Add car to favorites
     */
    fun addFavorite(carId: String, token: String) {
        viewModelScope.launch {
            favoriteRepository.addFavorite(carId, token).collectLatest { result ->
                when (result) {
                    is Result.Success -> {
                        _uiState.value = FavoriteUiState.Success
                    }
                    is Result.Error -> {
                        _uiState.value = FavoriteUiState.Error("Failed to add favorite")
                    }
                    is Result.Loading -> {}
                }
            }
        }
    }

    /**
     * Remove car from favorites
     */
    fun removeFavorite(userId: String, carId: String, token: String) {
        viewModelScope.launch {
            favoriteRepository.removeFavorite(userId, carId, token).collectLatest { result ->
                when (result) {
                    is Result.Success -> {
                        _uiState.value = FavoriteUiState.Success
                        loadFavorites(userId)
                    }
                    is Result.Error -> {
                        _uiState.value = FavoriteUiState.Error("Failed to remove favorite")
                    }
                    is Result.Loading -> {}
                }
            }
        }
    }

    /**
     * Check if car is favorited
     */
    fun isFavorite(userId: String, carId: String): Boolean {
        var result = false
        viewModelScope.launch {
            favoriteRepository.isFavorite(userId, carId).collectLatest { isFavorite ->
                result = isFavorite
            }
        }
        return result
    }

    /**
     * Sync favorites with server
     */
    fun syncFavorites(token: String) {
        viewModelScope.launch {
            favoriteRepository.syncFavorites(token).collectLatest { result ->
                when (result) {
                    is Result.Success -> {}
                    is Result.Error -> {
                        _uiState.value = FavoriteUiState.Error("Failed to sync favorites")
                    }
                    is Result.Loading -> {}
                }
            }
        }
    }
}

/**
 * UI State for favorites screen
 */
sealed class FavoriteUiState {
    object Loading : FavoriteUiState()
    object Success : FavoriteUiState()
    data class Error(val message: String) : FavoriteUiState()
}
