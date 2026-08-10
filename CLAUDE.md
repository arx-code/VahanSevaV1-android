# VahanSeva - Used Car Marketplace Platform

## Project Overview

VahanSeva is a modern Android application for buying and selling used cars. Built with Kotlin, Jetpack Compose, and MVVM architecture, the platform connects buyers and sellers with a seamless, secure marketplace experience.

**Platform**: Android (Kotlin)  
**Architecture**: MVVM with Clean Architecture layers  
**UI Framework**: Jetpack Compose & Material 3  
**Status**: In Development (v1.0.0)  
**Min SDK**: 24 | **Target SDK**: 36

## Core Features

- **Browse & Search**: Filter and search used car listings by multiple criteria
- **Listings**: Post, manage, and update car listings for sale
- **Favorites**: Save favorite cars to a wishlist
- **Messaging**: Real-time messaging between buyers and sellers
- **User Profiles**: Complete user profiles with ratings and verification
- **Advanced Search**: Filter by price, mileage, fuel type, transmission, location, etc.

## Tech Stack

### Framework & UI
- **Kotlin 2.0.21+** - Modern Android development
- **Jetpack Compose** - Declarative UI framework
- **Material 3** - Design system and components
- **Navigation Compose** - Type-safe navigation

### Data & Storage
- **Room Database** - Local SQLite caching
- **Retrofit 2** - REST API client
- **OkHttp 3** - HTTP client with interceptors
- **Gson** - JSON serialization

### Architecture & DI
- **Hilt** - Dependency injection framework
- **ViewModel** - UI state management
- **StateFlow/Flow** - Reactive data streams
- **Repository Pattern** - Single source of truth

### Async & Reactive
- **Kotlin Coroutines** - Async operations
- **Flow & StateFlow** - Reactive streams
- **Paging 3** - Efficient list pagination

### Testing
- **JUnit 4** - Unit testing
- **Mockito/MockK** - Mocking framework
- **Turbine** - Flow testing
- **Compose Test** - UI component testing

## Project Structure

```
app/src/main/java/com/vahanseva/auto_mall/
├── data/                          # Data layer
│   ├── model/                     # Data classes & entities
│   ├── local/                     # Room DB, DAOs
│   ├── remote/                    # Retrofit API services
│   └── repository/                # Repository implementations
├── presentation/                  # Presentation layer
│   ├── viewmodel/                 # MVVM ViewModels
│   ├── screens/                   # Compose UI screens
│   └── navigation/                # Navigation routing
├── domain/                        # Domain layer (use cases)
├── di/                            # Dependency injection modules
├── ui/theme/                      # Design system (colors, typography)
├── MainActivity.kt                # Entry point
└── VahanSevaApplication.kt        # Application class with Hilt
```

## Key Data Models

### User
- Profile information, ratings, verification status
- Seller metrics (total listings, sold cars)

### Car
- Vehicle details (brand, model, year, mileage, price)
- Fuel type, transmission, condition
- Images, location, featured status
- Seller information

### Message & Conversation
- Real-time messaging between users
- Conversation threads organized by car listing
- Read/unread tracking

### Favorite
- User wishlist of saved cars
- Timestamp tracking

### SearchFilters
- Price range, mileage range
- Brand, model, fuel type, transmission
- Location-based filtering

## API Integration

### Base URL Configuration
Update `BASE_URL` in `di/NetworkModule.kt` with your backend API endpoint.

### Key Endpoints (from DEVELOPMENT.md)
- **Auth**: `/auth/login`, `/auth/register`, `/auth/profile`
- **Cars**: `/cars`, `/cars/{id}`, `/cars/search`, `/cars/featured`
- **Messages**: `/messages`, `/messages/conversations`, `/messages/conversation/{id}`
- **Favorites**: `/favorites`, `/favorites/check`

## Development Workflow

### 1. Adding a New Screen
1. Create ViewModel in `presentation/viewmodel/`
2. Create Composable in `presentation/screens/`
3. Add route to `presentation/navigation/Screen.kt`
4. Add navigation composable to `VahanSevaNavigation.kt`

### 2. Adding Data Model
1. Create entity in `data/model/`
2. Create DAO in `data/local/`
3. Add to `VahanSevaDatabase.kt`
4. Create Repository in `data/repository/`

### 3. API Integration
1. Define interface in `data/remote/`
2. Provide in `di/NetworkModule.kt`
3. Use in Repository

### 4. Testing
- Unit test ViewModels with mock repositories
- Test Repositories with mock DAOs/services
- Test UI components with Compose Test

## Code Standards

### Kotlin Style Guide
- Follow Google's official Kotlin style guide
- 4 spaces indentation
- PascalCase for classes, camelCase for variables/functions
- UPPER_SNAKE_CASE for constants

### File Organization
```kotlin
// 1. Package declaration
// 2. Imports (grouped: kotlin, androidx, com.vahanseva)
// 3. Type aliases
// 4. Class/Interface definitions
```

### Comments & Documentation
- Use meaningful variable/function names over comments
- Document complex business logic
- Add KDoc for public APIs
- Reference related issues/PRs in commit messages

## State Management

Uses **Kotlin Flow** with **MVVM** pattern:
- ViewModels expose `StateFlow<UiState>`
- UI collects state with `collectAsState()`
- No mutable state leakage to UI
- Events handled through ViewModel functions

```kotlin
@HiltViewModel
class MyViewModel @Inject constructor(
    private val repository: MyRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<MyUiState>(MyUiState.Loading)
    val uiState: StateFlow<MyUiState> = _uiState.asStateFlow()
}
```

## Security Considerations

- [ ] Token management with EncryptedSharedPreferences
- [ ] Certificate pinning for HTTPS
- [ ] Input validation for all user input
- [ ] ProGuard/R8 obfuscation for release builds
- [ ] Secure storage of API keys
- [ ] Authentication state verification

## Performance Notes

1. **Paging**: Use Paging 3 for large car listings
2. **Caching**: Room DB caches API responses
3. **Image Loading**: Plan Coil/Glide integration for efficient image handling
4. **Coroutines**: All async operations use coroutines
5. **Compose**: Extract reusable composables, use proper remember/mutableStateOf

## Common Tasks

### Build & Run
```bash
./gradlew build              # Build debug
./gradlew installDebug       # Install on device/emulator
./gradlew test              # Run unit tests
```

### Code Quality
```bash
./gradlew lint              # Run Android lint
./gradlew test              # Run tests
```

### Database Debugging
- Room queries can be inspected via Android Studio Database Inspector
- Use Device File Explorer to access SQLite database

## Next Steps for Implementation

1. **Connect Backend**: Configure `BASE_URL` in `NetworkModule.kt`
2. **Implement UI Screens**: Complete all placeholder screens
3. **Image Handling**: Integrate image upload/download library
4. **Real-time Messaging**: Consider WebSocket for live chat
5. **Error Handling**: Add comprehensive error handling & retry logic
6. **Testing**: Write unit and integration tests
7. **Analytics**: Add Firebase or similar
8. **Release Build**: Configure ProGuard, signing, and version management

## Resources

- [Android Developer Docs](https://developer.android.com)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)
- [Room Database](https://developer.android.com/training/data-storage/room)
- [Hilt Dependency Injection](https://developer.android.com/training/dependency-injection/hilt-android)

## Team

- **Lead Developer**: Amer

## Quick Links

- **README.md**: Project overview and setup
- **DEVELOPMENT.md**: Development guidelines and standards
- **GitHub**: [Add repository URL]

---

**Last Updated**: August 10, 2026  
**Project Version**: 1.0.0
