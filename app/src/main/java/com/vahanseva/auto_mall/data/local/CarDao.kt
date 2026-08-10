package com.vahanseva.auto_mall.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.vahanseva.auto_mall.data.model.Car
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for Car entity
 * Supports pagination, filtering, and search operations
 */
@Dao
interface CarDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCar(car: Car)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCars(cars: List<Car>)

    @Query("SELECT * FROM cars WHERE id = :carId")
    fun getCarById(carId: String): Flow<Car?>

    @Query("SELECT * FROM cars ORDER BY created_at DESC")
    fun getAllCars(): PagingSource<Int, Car>

    @Query("""
        SELECT * FROM cars
        WHERE (:query IS NULL OR title LIKE '%' || :query || '%' OR brand LIKE '%' || :query || '%')
        AND (:brand IS NULL OR brand = :brand)
        AND (:minPrice IS NULL OR price >= :minPrice)
        AND (:maxPrice IS NULL OR price <= :maxPrice)
        AND (:minYear IS NULL OR year >= :minYear)
        AND (:maxYear IS NULL OR year <= :maxYear)
        AND (:fuelType IS NULL OR fuel_type = :fuelType)
        AND (:transmission IS NULL OR transmission = :transmission)
        AND (:location IS NULL OR location = :location)
        AND (:maxMileage IS NULL OR mileage <= :maxMileage)
        ORDER BY
            CASE WHEN :sortBy = 'newest' THEN created_at END DESC,
            CASE WHEN :sortBy = 'price_low' THEN price END ASC,
            CASE WHEN :sortBy = 'price_high' THEN price END DESC,
            CASE WHEN :sortBy = 'mileage_low' THEN mileage END ASC,
            CASE WHEN :sortBy = 'year_new' THEN year END DESC
    """)
    fun searchCars(
        query: String?,
        brand: String?,
        minPrice: Double?,
        maxPrice: Double?,
        minYear: Int?,
        maxYear: Int?,
        fuelType: String?,
        transmission: String?,
        location: String?,
        maxMileage: Int?,
        sortBy: String
    ): PagingSource<Int, Car>

    @Query("SELECT * FROM cars WHERE seller_id = :sellerId")
    fun getCarsBySeller(sellerId: String): Flow<List<Car>>

    @Query("SELECT * FROM cars WHERE is_featured = 1 ORDER BY created_at DESC LIMIT 10")
    fun getFeaturedCars(): Flow<List<Car>>

    @Update
    suspend fun updateCar(car: Car)

    @Delete
    suspend fun deleteCar(car: Car)

    @Query("DELETE FROM cars WHERE id = :carId")
    suspend fun deleteCarById(carId: String)

    @Query("DELETE FROM cars")
    suspend fun deleteAllCars()
}
