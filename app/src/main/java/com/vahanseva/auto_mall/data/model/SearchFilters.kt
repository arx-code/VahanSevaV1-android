package com.vahanseva.auto_mall.data.model

/**
 * Search filters for car listings
 */
data class SearchFilters(
    val query: String = "",
    val brand: String? = null,
    val model: String? = null,
    val minPrice: Double? = null,
    val maxPrice: Double? = null,
    val minYear: Int? = null,
    val maxYear: Int? = null,
    val fuelType: String? = null,
    val transmission: String? = null,
    val location: String? = null,
    val maxMileage: Int? = null,
    val ownerCount: Int? = null,
    val sortBy: SortBy = SortBy.NEWEST
)

enum class SortBy {
    NEWEST,
    OLDEST,
    PRICE_LOW_TO_HIGH,
    PRICE_HIGH_TO_LOW,
    MILEAGE_LOW_TO_HIGH,
    YEAR_NEW_TO_OLD
}
