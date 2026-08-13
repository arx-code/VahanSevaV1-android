# Mock Data Specification for Vahan Seva Auto-Mall

## Overview
This document defines the mock data structure used for development and testing of the Vahan Seva Auto-Mall application during Phase 1 and Phase 2.

## Data Models

### Vehicle
```kotlin
data class Vehicle(
    val id: String,
    val title: String,
    val price: Long, // in rupees
    val year: Int,
    val kmDriven: Int,
    val fuelType: FuelType,
    val transmission: Transmission,
    val owner: Owner,
    val location: String,
    val city: String,
    val images: List<String>, // URLs
    val features: List<Feature>,
    val description: String,
    val listedAt: Long, // timestamp
    val sellerType: SellerType,
    val contactNumber: String
)
```

### Enumerations
```kotlin
enum class FuelType {
    PETROL, DIESEL, ELECTRIC, HYBRID, CNG, LPG
}

enum class Transmission {
    MANUAL, AUTOMATIC
}

enum class Owner {
    FIRST, SECOND, THIRD, FOURTH_PLUS
}

enum class SellerType {
    INDIVIDUAL, DEALER, SHOWROOM
}

enum class Feature {
    AC, POWER_STEERING, CENTRAL_LOCKING, ELECTRIC_WINDOWS,
    ABS, AIRBAGS, SUNROOF, LEATHER_SEATS, NAVIGATION,
    REAR_CAMERA, SENSORS, ALLOY_WHEELS
}
```

### Showroom
```kotlin
data class Showroom(
    val id: String,
    val name: String,
    val location: String,
    val city: String,
    val rating: Float, // 0-5
    val totalReviews: Int,
    val vehicles: List<Vehicle>,
    val contactNumber: String,
    val timings: String,
    val amenities: List<String>
)
```

### User Profile
```kotlin
data class UserProfile(
    val id: String,
    val name: String,
    val email: String,
    val phone: String,
    val profileImage: String?, // URL
    val savedVehicles: List<String>, // vehicle IDs
    val savedShowrooms: List<String>, // showroom IDs
    val totalListings: Int,
    val memberSince: Long
)
```

### Chat Message
```kotlin
data class ChatMessage(
    val id: String,
    val senderId: String,
    val receiverId: String,
    val vehicleId: String,
    val message: String,
    val timestamp: Long,
    val isRead: Boolean
)
```

## Mock Data Generators

### Sample Vehicles (10 items)
1. **Maruti Swift 2022** - ₹6,50,000 - 15,000 km - Petrol - Manual - First Owner
2. **Honda City 2021** - ₹11,20,000 - 22,000 km - Petrol - Automatic - First Owner  
3. **Hyundai Verna 2020** - ₹9,80,000 - 35,000 km - Diesel - Manual - Second Owner
4. **Toyota Innova Crysta 2019** - ₹18,50,000 - 48,000 km - Diesel - Automatic - First Owner
5. **Mahindra XUV700 2023** - ₹24,00,000 - 8,000 km - Diesel - Automatic - First Owner
6. **Tata Nexon EV 2022** - ₹14,50,000 - 12,000 km - Electric - Automatic - First Owner
7. **BMW 3 Series 2021** - ₹38,00,000 - 18,000 km - Petrol - Automatic - First Owner
8. **Maruti Baleno 2022** - ₹7,80,000 - 12,000 km - Petrol - Manual - First Owner
9. **Kia Seltos 2020** - ₹15,20,000 - 28,000 km - Petrol - Automatic - Second Owner
10. **Toyota Fortuner 2018** - ₹32,00,000 - 65,000 km - Diesel - Automatic - First Owner

### Sample Showrooms (3 items)
1. **Maruti Arena - Karol Bagh** - New Delhi - 4.8��⭐ (124 reviews)
2. **Honda Showroom - Connaught Place** - New Delhi - 4.6��⭐ (89 reviews)  
3. **Toyota Bharat - Gurgaon** - Gurgaon - 4.9��⭐ (203 reviews)

### Sample Users
1. **Rahul Sharma** - rahul@example.com - +91 98765 43210
2. **Priya Patel** - priya@example.com - +91 98765 43211
3. **Amit Singh** - amit@example.com - +91 98765 43212

## Usage Instructions

### In Composables
```kotlin
@Composable
fun VehicleListScreen(viewModel: VehicleViewModel = hiltViewModel()) {
    val vehicles by viewModel.vehicles.collectAsState()
    LazyColumn {
        items(vehicles) { vehicle ->
            VehicleCard(vehicle = vehicle)
        }
    }
}
```

### In ViewModels (Mock Implementation)
```kotlin
@HiltViewModel
class VehicleViewModel @Inject constructor(
    @RepositoryImpl private val repository: VehicleRepository
) : ViewModel() {
    
    val vehicles = MutableStateFlow<List<Vehicle>>(emptyList())
    
    init {
        viewModelScope.launch {
            // Load mock data
            val mockData = MockDataGenerator.generateVehicles(10)
            vehicles.value = mockData
        }
    }
}
```

## Image Placeholders
Use these placeholder URLs for vehicle images:
- `https://via.placeholder.com/400x300?text=Vehicle+Image`
- `https://picsum.photos/seed/vehicle1/400/300`
- `https://source.unsplash.com/random/400x300/?car,vehicle`

## Data Refresh Strategy
- Mock data loads once on app startup
- Pull-to-refresh simulates API call (same data)
- Infinite scroll loads more mock vehicles (up to 50 total)
- Cache persists during app session

## Testing Considerations
- All IDs are deterministic for reproducible tests
- Prices follow realistic Indian market ranges
- Years range from 2018-2024
- KM driven correlates with vehicle age
- Locations cover major Indian cities

## Extending Mock Data
To add new vehicle types:
1. Add to `vehicles` list in MockDataGenerator
2. Ensure unique ID generation
3. Follow price/km/year correlation patterns
4. Add appropriate features based on vehicle class

---
*Last Updated: 2026-08-10*
*Version: 1.0*
*Status: Ready for Phase 2 Implementation*