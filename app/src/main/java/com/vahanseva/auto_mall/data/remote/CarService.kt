package com.vahanseva.auto_mall.data.remote

import com.vahanseva.auto_mall.data.model.Car
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit API service for car listings endpoints
 */
interface CarService {
    @GET("cars")
    suspend fun getCars(
        @Query("page") page: Int,
        @Query("limit") limit: Int = 20,
        @Query("sort") sort: String = "newest"
    ): CarsResponse

    @GET("cars/{carId}")
    suspend fun getCarById(@Path("carId") carId: String): Car

    @POST("cars")
    suspend fun createCar(
        @Body car: Car,
        @Header("Authorization") token: String
    ): Car

    @POST("cars/{carId}")
    suspend fun updateCar(
        @Path("carId") carId: String,
        @Body car: Car,
        @Header("Authorization") token: String
    ): Car

    @DELETE("cars/{carId}")
    suspend fun deleteCar(
        @Path("carId") carId: String,
        @Header("Authorization") token: String
    )

    @GET("cars/search")
    suspend fun searchCars(
        @Query("q") query: String?,
        @Query("brand") brand: String?,
        @Query("model") model: String?,
        @Query("minPrice") minPrice: Double?,
        @Query("maxPrice") maxPrice: Double?,
        @Query("minYear") minYear: Int?,
        @Query("maxYear") maxYear: Int?,
        @Query("fuelType") fuelType: String?,
        @Query("transmission") transmission: String?,
        @Query("location") location: String?,
        @Query("maxMileage") maxMileage: Int?,
        @Query("page") page: Int,
        @Query("limit") limit: Int = 20,
        @Query("sort") sort: String = "newest"
    ): CarsResponse

    @GET("cars/seller/{sellerId}")
    suspend fun getSellerCars(
        @Path("sellerId") sellerId: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int = 20
    ): CarsResponse

    @GET("cars/featured")
    suspend fun getFeaturedCars(
        @Query("limit") limit: Int = 10
    ): List<Car>

    @POST("cars/{carId}/verify")
    suspend fun verifyCar(
        @Path("carId") carId: String,
        @Header("Authorization") token: String
    ): Car

    @POST("cars/{carId}/feature")
    suspend fun featureCar(
        @Path("carId") carId: String,
        @Header("Authorization") token: String
    ): Car
}

data class CarsResponse(
    val cars: List<Car>,
    val page: Int,
    val totalPages: Int,
    val totalCount: Int,
    val hasMore: Boolean
)
