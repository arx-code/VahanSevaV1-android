package com.vahanseva.auto_mall.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.vahanseva.auto_mall.data.model.Car
import com.vahanseva.auto_mall.data.model.SearchFilters
import com.vahanseva.auto_mall.data.repository.CarRepository
import com.vahanseva.auto_mall.data.repository.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for car listing screen
 * Manages car list, search, and filter state
 */
@HiltViewModel
class CarListViewModel @Inject constructor(
    private val carRepository: CarRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<CarListUiState>(CarListUiState.Loading)
    val uiState: StateFlow<CarListUiState> = _uiState.asStateFlow()

    private val _cars = MutableStateFlow<PagingData<Car>>(PagingData.empty())
    val cars: StateFlow<PagingData<Car>> = _cars.asStateFlow()

    private val _searchFilters = MutableStateFlow(SearchFilters())
    val searchFilters: StateFlow<SearchFilters> = _searchFilters.asStateFlow()

    private val _featuredCars = MutableStateFlow<List<Car>>(emptyList())
    val featuredCars: StateFlow<List<Car>> = _featuredCars.asStateFlow()

    init {
        loadCars()
        loadFeaturedCars()
    }

    /**
     * Load all cars with pagination
     */
    private fun loadCars() {
        viewModelScope.launch {
            _uiState.value = CarListUiState.Loading
            try {
                carRepository.getAllCars()
                    .cachedIn(viewModelScope)
                    .collectLatest { pagingData ->
                        _cars.value = pagingData
                        _uiState.value = CarListUiState.Success
                    }
            } catch (e: Exception) {
                _uiState.value = CarListUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    /**
     * Load featured cars
     */
    private fun loadFeaturedCars() {
        viewModelScope.launch {
            carRepository.getFeaturedCars().collectLatest { featured ->
                _featuredCars.value = featured
            }
        }
    }

    /**
     * Search cars with filters
     */
    fun searchCars(filters: SearchFilters) {
        viewModelScope.launch {
            _searchFilters.value = filters
            _uiState.value = CarListUiState.Loading
            try {
                carRepository.searchCars(filters)
                    .cachedIn(viewModelScope)
                    .collectLatest { pagingData ->
                        _cars.value = pagingData
                        _uiState.value = CarListUiState.Success
                    }
            } catch (e: Exception) {
                _uiState.value = CarListUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    /**
     * Refresh car list from server
     */
    fun refreshCars() {
        viewModelScope.launch {
            carRepository.refreshCars().collectLatest { result ->
                when (result) {
                    is Result.Loading -> _uiState.value = CarListUiState.Loading
                    is Result.Success -> {
                        _uiState.value = CarListUiState.Success
                        loadCars()
                    }
                    is Result.Error -> _uiState.value = CarListUiState.Error(result.exception.message ?: "Unknown error")
                }
            }
        }
    }

    /**
     * Update search filter
     */
    fun updateFilters(filters: SearchFilters) {
        _searchFilters.value = filters
    }

    /**
     * Clear all filters
     */
    fun clearFilters() {
        _searchFilters.value = SearchFilters()
        loadCars()
    }
}

/**
 * UI State for car list screen
 */
sealed class CarListUiState {
    object Loading : CarListUiState()
    object Success : CarListUiState()
    data class Error(val message: String) : CarListUiState()
}
