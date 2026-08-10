package com.vahanseva.auto_mall.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.vahanseva.auto_mall.data.local.CarDao
import com.vahanseva.auto_mall.data.model.Car
import com.vahanseva.auto_mall.data.model.SearchFilters
import com.vahanseva.auto_mall.data.remote.CarService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Repository for Car listings
 * Handles data from both local database and remote API
 */
class CarRepository @Inject constructor(
    private val carService: CarService,
    private val carDao: CarDao
) {
    companion object {
        private const val PAGING_SIZE = 20
    }

    /**
     * Get paginated list of all cars
     */
    fun getAllCars(): Flow<PagingData<Car>> = Pager(
        config = PagingConfig(pageSize = PAGING_SIZE, enablePlaceholders = false),
        pagingSourceFactory = { carDao.getAllCars() }
    ).flow

    /**
     * Search and filter cars with pagination
     */
    fun searchCars(filters: SearchFilters): Flow<PagingData<Car>> = Pager(
        config = PagingConfig(pageSize = PAGING_SIZE, enablePlaceholders = false),
        pagingSourceFactory = {
            carDao.searchCars(
                query = if (filters.query.isEmpty()) null else filters.query,
                brand = filters.brand,
                minPrice = filters.minPrice,
                maxPrice = filters.maxPrice,
                minYear = filters.minYear,
                maxYear = filters.maxYear,
                fuelType = filters.fuelType,
                transmission = filters.transmission,
                location = filters.location,
                maxMileage = filters.maxMileage,
                sortBy = filters.sortBy.name.lowercase()
            )
        }
    ).flow

    /**
     * Get car details by ID
     */
    fun getCarById(carId: String): Flow<Car?> = carDao.getCarById(carId)

    /**
     * Get all cars by seller
     */
    fun getSellerCars(sellerId: String): Flow<List<Car>> = carDao.getCarsBySeller(sellerId)

    /**
     * Get featured cars
     */
    fun getFeaturedCars(): Flow<List<Car>> = carDao.getFeaturedCars()

    /**
     * Fetch cars from remote API and cache locally
     */
    fun refreshCars(page: Int = 1): Flow<Result<Unit>> = flow {
        try {
            emit(Result.Loading)
            val response = carService.getCars(page, PAGING_SIZE)
            carDao.insertCars(response.cars)
            emit(Result.Success(Unit))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    /**
     * Create a new car listing
     */
    fun createCar(car: Car, token: String): Flow<Result<Car>> = flow {
        try {
            emit(Result.Loading)
            val createdCar = carService.createCar(car, "Bearer $token")
            carDao.insertCar(createdCar)
            emit(Result.Success(createdCar))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    /**
     * Update car listing
     */
    fun updateCar(carId: String, car: Car, token: String): Flow<Result<Car>> = flow {
        try {
            emit(Result.Loading)
            val updatedCar = carService.updateCar(carId, car, "Bearer $token")
            carDao.updateCar(updatedCar)
            emit(Result.Success(updatedCar))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    /**
     * Delete car listing
     */
    fun deleteCar(carId: String, token: String): Flow<Result<Unit>> = flow {
        try {
            emit(Result.Loading)
            carService.deleteCar(carId, "Bearer $token")
            carDao.deleteCarById(carId)
            emit(Result.Success(Unit))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    /**
     * Verify car listing (admin only)
     */
    fun verifyCar(carId: String, token: String): Flow<Result<Car>> = flow {
        try {
            emit(Result.Loading)
            val verifiedCar = carService.verifyCar(carId, "Bearer $token")
            carDao.updateCar(verifiedCar)
            emit(Result.Success(verifiedCar))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}

/**
 * Result wrapper for async operations
 */
sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
    object Loading : Result<Nothing>()
}
