# VahanSeva Auto Mall - Android Project Setup

Welcome to VahanSeva, a used car marketplace platform for buying and selling vehicles.

## Project Overview

VahanSeva is a modern Android application built with Kotlin, Jetpack Compose, and MVVM architecture. The app enables users to:
- Browse and search used car listings
- Post and manage car listings for sale
- Save favorite cars
- Message other users (buyers/sellers)
- Manage user profiles and ratings
- Filter cars by various criteria

## Architecture Layers

### 1. **Data Layer** (`data/`)
Handles all data operations:
- **Models**: Data classes for entities (User, Car, Message, Conversation, Favorite)
- **Local**: Room database setup with DAOs for local caching
- **Remote**: Retrofit API services for backend communication
- **Repository**: Implements data access logic and manages data flow

**Key Components:**
- `VahanSevaDatabase`: Room database with all entities
- `CarService`, `AuthService`, `MessageService`, `FavoriteService`: Retrofit interfaces
- `CarRepository`, `AuthRepository`, `MessageRepository`, `FavoriteRepository`: Data access

### 2. **Domain Layer** (`domain/`)
Business logic and model definitions (extendable for use cases)

### 3. **Presentation Layer** (`presentation/`)
UI and user interactions:
- **ViewModels**: State management with StateFlow and MutableStateFlow
- **Screens**: Jetpack Compose UI components
- **Navigation**: App navigation routing

**Key ViewModels:**
- `AuthViewModel`: Login/Register/Profile management
- `CarListViewModel`: Browse and search cars
- `CarDetailViewModel`: Single car details and favorites
- `MessageViewModel`: Chat and messaging
- `FavoriteViewModel`: Wishlist management

### 4. **DI Layer** (`di/`)
Dependency Injection setup using Hilt:
- `DatabaseModule`: Room database and DAOs
- `NetworkModule`: Retrofit, OkHttp, and API services
- `RepositoryModule`: Repository instances

## Project Structure

```
app/src/main/java/com/vahanseva/auto_mall/
├── data/
│   ├── model/
│   │   ├── User.kt
│   │   ├── Car.kt
│   │   ├── Message.kt
│   │   ├── Favorite.kt
│   │   └── SearchFilters.kt
│   ├── local/
│   │   ├── VahanSevaDatabase.kt
│   │   ├── UserDao.kt
│   │   ├── CarDao.kt
│   │   ├── MessageDao.kt
│   │   ├── ConversationDao.kt
│   │   ├── FavoriteDao.kt
│   │   └── Converters.kt
│   ├── remote/
│   │   ├── AuthService.kt
│   │   ├── UserService.kt
│   │   ├── CarService.kt
│   │   ├── MessageService.kt
│   │   └── FavoriteService.kt
│   └── repository/
│       ├── AuthRepository.kt
│       ├── CarRepository.kt
│       ├── MessageRepository.kt
│       └── FavoriteRepository.kt
├── presentation/
│   ├── viewmodel/
│   │   ├── AuthViewModel.kt
│   │   ├── CarListViewModel.kt
│   │   ├── CarDetailViewModel.kt
│   │   ├── MessageViewModel.kt
│   │   └── FavoriteViewModel.kt
│   ├── screens/
│   │   ├── HomeScreen.kt
│   │   ├── PlaceholderScreens.kt
│   │   └── [More screens to be implemented]
│   └── navigation/
│       ├── VahanSevaNavigation.kt
│       └── Screen.kt
├── di/
│   ├── DatabaseModule.kt
│   ├── NetworkModule.kt
│   └── RepositoryModule.kt
├── MainActivity.kt
└── VahanSevaApplication.kt
```

## Technologies Used

### Core Framework
- **Kotlin**: Programming language
- **Jetpack Compose**: UI framework
- **MVVM**: Architecture pattern

### Libraries
- **Jetpack Components**:
  - Compose UI/Material3
  - Navigation Compose
  - ViewModel & Lifecycle
  - Room (Local Database)
  - Paging 3
  - DataStore Preferences

- **Networking**:
  - Retrofit 2
  - OkHttp 3
  - Gson

- **Dependency Injection**:
  - Hilt

- **Coroutines**:
  - Kotlinx Coroutines
  - Flow/StateFlow

- **Testing**:
  - JUnit 4
  - Mockito/MockK
  - Turbine (Flow testing)

## Getting Started

### Prerequisites
- Android Studio (latest version)
- Android SDK 24+ (minSdk)
- Kotlin 2.0.21+
- JDK 11+

### Setup Steps

1. **Clone the project**
   ```bash
   git clone <repository-url>
   cd VahanSevaV1
   ```

2. **Configure API Base URL**
   Edit `di/NetworkModule.kt` and update the `BASE_URL`:
   ```kotlin
   private const val BASE_URL = "https://your-api-url.com/"
   ```

3. **Build the project**
   ```bash
   ./gradlew build
   ```

4. **Run on emulator or device**
   ```bash
   ./gradlew installDebug
   ```

## API Integration

### Auth Endpoints
- `POST /auth/login` - User login
- `POST /auth/register` - User registration
- `POST /auth/logout` - User logout
- `GET /auth/profile` - Get current user profile
- `POST /auth/refresh-token` - Refresh authentication token

### Car Endpoints
- `GET /cars` - List all cars
- `GET /cars/{carId}` - Get car details
- `POST /cars` - Create new car listing
- `POST /cars/{carId}` - Update car listing
- `DELETE /cars/{carId}` - Delete car listing
- `GET /cars/search` - Search cars with filters
- `GET /cars/seller/{sellerId}` - Get seller's cars
- `GET /cars/featured` - Get featured cars

### Messaging Endpoints
- `POST /messages` - Send message
- `GET /messages/conversation/{conversationId}` - Get conversation messages
- `GET /messages/conversations` - Get user conversations
- `POST /messages/conversations` - Get or create conversation
- `POST /messages/conversation/{conversationId}/read` - Mark as read

### Favorite Endpoints
- `POST /favorites` - Add to favorites
- `GET /favorites` - Get user favorites
- `DELETE /favorites` - Remove from favorites
- `GET /favorites/check` - Check if favorited

## Database Schema

### Users Table
```sql
CREATE TABLE users (
  id TEXT PRIMARY KEY,
  email TEXT,
  name TEXT,
  phone TEXT,
  profile_image TEXT,
  location TEXT,
  is_verified BOOLEAN,
  created_at LONG,
  rating FLOAT,
  total_listings INT,
  sold_cars INT
)
```

### Cars Table
```sql
CREATE TABLE cars (
  id TEXT PRIMARY KEY,
  title TEXT,
  brand TEXT,
  model TEXT,
  year INT,
  price DOUBLE,
  mileage INT,
  fuel_type TEXT,
  transmission TEXT,
  color TEXT,
  owner_count INT,
  description TEXT,
  location TEXT,
  seller_id TEXT,
  seller_name TEXT,
  images TEXT, -- JSON array
  is_featured BOOLEAN,
  is_verified BOOLEAN,
  created_at LONG,
  updated_at LONG,
  is_favorite BOOLEAN
)
```

### Messages Table
```sql
CREATE TABLE messages (
  id TEXT PRIMARY KEY,
  conversation_id TEXT,
  sender_id TEXT,
  receiver_id TEXT,
  car_id TEXT,
  message TEXT,
  timestamp LONG,
  is_read BOOLEAN,
  message_type TEXT
)
```

### Conversations Table
```sql
CREATE TABLE conversations (
  id TEXT PRIMARY KEY,
  user1_id TEXT,
  user2_id TEXT,
  car_id TEXT,
  last_message TEXT,
  last_message_time LONG,
  unread_count INT
)
```

### Favorites Table
```sql
CREATE TABLE favorites (
  id INT PRIMARY KEY,
  user_id TEXT,
  car_id TEXT,
  saved_at LONG
)
```

## State Management

The app uses a reactive state management pattern with Kotlin Flow:

```kotlin
// ViewModel exposes state as StateFlow
class MyViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()
    
    // Collect in UI
    val state by uiState.collectAsState()
}
```

## Screens to Implement

### Authentication
- [ ] Login Screen
- [ ] Register Screen
- [ ] Forgot Password Screen

### Browse & Search
- [ ] Car List Screen (with pagination)
- [ ] Car Detail Screen
- [ ] Search/Filter Screen
- [ ] Seller Profile Screen

### User Features
- [ ] Favorites/Wishlist Screen
- [ ] My Listings Screen
- [ ] User Profile Screen
- [ ] Edit Profile Screen

### Messaging
- [ ] Messages/Conversations List Screen
- [ ] Chat Screen (with real-time messages)
- [ ] New Conversation Screen

### Selling
- [ ] Add New Car Screen (multi-step form)
- [ ] Edit Car Listing Screen
- [ ] Manage Listings Screen
- [ ] Upload Car Images Screen

## Testing Strategy

### Unit Tests
- ViewModel tests with mock repositories
- Repository tests with mock DAOs and services
- UI state tests with Turbine

### Integration Tests
- Database operations (Room)
- API calls (Retrofit mocks)

### UI Tests
- Compose component tests
- Navigation tests

## Performance Optimization

1. **Paging**: Car listings use Paging 3 for efficient loading
2. **Caching**: Room database caches API responses
3. **Image Loading**: Consider using Coil or Glide for images
4. **Coroutines**: All async operations use coroutines
5. **Flow**: Reactive data streams reduce unnecessary recompositions

## Security Considerations

- [ ] Implement token refresh mechanism
- [ ] Store tokens securely (EncryptedSharedPreferences)
- [ ] Add certificate pinning for HTTPS
- [ ] Validate all API responses
- [ ] Implement input validation
- [ ] Use ProGuard/R8 for release builds

## Next Steps

1. **Connect to Backend**: Update `BASE_URL` in `NetworkModule.kt`
2. **Implement UI Screens**: Complete all placeholder screens with real UI
3. **Add Image Handling**: Implement image upload/download
4. **Real-time Messaging**: Consider WebSocket for live chat
5. **User Authentication**: Implement proper token management
6. **Error Handling**: Add comprehensive error handling and retry logic
7. **Analytics**: Add Firebase or similar analytics
8. **Testing**: Write unit and integration tests

## Build Variants

```gradle
buildTypes {
    debug {
        debuggable true
        minifyEnabled false
    }
    release {
        debuggable false
        minifyEnabled true
        proguardFiles(...)
    }
}
```

## Troubleshooting

### Build Issues
- **Hilt Errors**: Ensure `@HiltAndroidApp` is on Application class
- **Room Errors**: Check all @Entity classes have proper annotations
- **Kotlin Errors**: Verify Kotlin version matches in build.gradle files

### Runtime Issues
- **API Connection**: Check `BASE_URL` configuration
- **Database Issues**: Check device storage permissions
- **Navigation Errors**: Verify route names in Screen.kt

## Contributors

Amer - Lead Developer

## License

[Add your license information here]

## Support

For issues or questions, please create an issue in the repository or contact the development team.

---

**Last Updated**: August 10, 2026  
**Project Version**: 1.0.0  
**Min SDK**: 24 | **Target SDK**: 36
