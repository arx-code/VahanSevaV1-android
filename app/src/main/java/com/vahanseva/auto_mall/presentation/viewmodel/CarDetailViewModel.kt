package com.vahanseva.auto_mall.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vahanseva.auto_mall.data.model.Car
import com.vahanseva.auto_mall.data.repository.CarRepository
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
 * ViewModel for car detail screen
 * Manages single car details and favorite status
 */
@HiltViewModel
class CarDetailViewModel @Inject constructor(
    private val carRepository: CarRepository,
    private val favoriteRepository: FavoriteRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val carId: String = savedStateHandle.get<String>("carId") ?: ""

    private val _uiState = MutableStateFlow<CarDetailUiState>(CarDetailUiState.Loading)
    val uiState: StateFlow<CarDetailUiState> = _uiState.asStateFlow()

    private val _car = MutableStateFlow<Car?>(null)
    val car: StateFlow<Car?> = _car.asStateFlow()

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite.asStateFlow()

    init {
        loadCarDetails()
    }

    /**
     * Load car details
     */
    private fun loadCarDetails() {
        viewModelScope.launch {
            _uiState.value = CarDetailUiState.Loading
            try {
                carRepository.getCarById(carId).collectLatest { car ->
                    if (car != null) {
                        _car.value = car
                        _isFavorite.value = car.isFavorite
                        _uiState.value = CarDetailUiState.Success
                    } else {
                        _uiState.value = CarDetailUiState.Error("Car not found")
                    }
                }
            } catch (e: Exception) {
                _uiState.value = CarDetailUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    /**
     * Toggle favorite status
     */
    fun toggleFavorite(userId: String, token: String) {
        viewModelScope.launch {
            val currentStatus = _isFavorite.value
            if (currentStatus) {
                // Remove from favorites
                favoriteRepository.removeFavorite(userId, carId, token).collectLatest { result ->
                    when (result) {
                        is Result.Success -> _isFavorite.value = false
                        is Result.Error -> _uiState.value = CarDetailUiState.Error("Failed to remove favorite")
                        is Result.Loading -> {}
                    }
                }
            } else {
                // Add to favorites
                favoriteRepository.addFavorite(carId, token).collectLatest { result ->
                    when (result) {
                        is Result.Success -> _isFavorite.value = true
                        is Result.Error -> _uiState.value = CarDetailUiState.Error("Failed to add favorite")
                        is Result.Loading -> {}
                    }
                }
            }
        }
    }

    /**
     * Refresh car details
     */
    fun refreshCarDetails() {
        loadCarDetails()
    }
}

/**
 * UI State for car detail screen
 */
sealed class CarDetailUiState {
    object Loading : CarDetailUiState()
    object Success : CarDetailUiState()
    data class Error(val message: String) : CarDetailUiState()
}
