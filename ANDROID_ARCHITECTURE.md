# Vahan Seva Android - Architecture Document

**Version**: 1.0.0  
**Date**: August 10, 2026  
**Architecture Pattern**: MVVM + Clean Architecture  
**Framework**: Jetpack Compose  
**Dependency Injection**: Hilt

---

## Architecture Overview

### Layered Architecture

```
┌──────────────────────────────────────────────┐
│        PRESENTATION LAYER                    │
│  (Jetpack Compose, ViewModels, Navigation)  │
└──────────────┬───────────────────────────────┘
               │
┌──────────────▼───────────────────────────────┐
│        DOMAIN LAYER                          │
│  (Business Logic, Use Cases, Entities)      │
└──────────────┬───────────────────────────────┘
               │
┌──────────────▼───────────────────────────────┐
│        DATA LAYER                            │
│  (Repositories, Data Sources, Models)       │
└──────────────────────────────────────────────┘
```

### Design Principles

1. **Single Responsibility**: Each layer has one purpose
2. **Dependency Inversion**: Depend on abstractions, not implementations
3. **Testability**: Each layer independently testable
4. **Reusability**: Domain and data layers reusable across UIs
5. **Maintainability**: Clear structure, easy to extend

---

## Project Structure

```
app/src/main/java/com/vahanseva/automall/
│
├── core/                                  # Shared infrastructure
│   ├── di/
│   │   ├── AppModule.kt                  # App-level DI
│   │   ├── DatabaseModule.kt             # Room setup
│   │   ├── NetworkModule.kt              # Retrofit setup (future)
│   │   ├── RepositoryModule.kt           # Repository bindings
│   │   └── DispatchersModule.kt          # Coroutine dispatchers
│   │
│   ├── network/
│   │   ├── ApiService.kt                 # (Future) API interface
│   │   ├── interceptors/
│   │   │   ├── AuthInterceptor.kt        # (Future) Auth
│   │   │   └── LoggingInterceptor.kt     # (Future) Logging
│   │   └── dto/                          # Data Transfer Objects
│   │
│   ├── database/
│   │   ├── AppDatabase.kt                # Room database
│   │   └── migrations/                   # DB migrations
│   │
│   ├── constants/
│   │   ├── Constants.kt                  # App constants
│   │   ├── ApiConstants.kt               # (Future) API URLs
│   │   └── FeatureFlags.kt               # Feature toggles
│   │
│   ├── utils/
│   │   ├── DataFormatUtils.kt
│   │   ├── LocationUtils.kt
│   │   ├── DateTimeUtils.kt
│   │   ├── ValidationUtils.kt
│   │   ├── MockDataGenerator.kt
│   │   └── Logger.kt
│   │
│   ├── preferences/
│   │   ├── AppPreferences.kt             # SharedPreferences wrapper
│   │   └── UserPreferences.kt
│   │
│   └── extensions/
│       ├── StringExtensions.kt
│       ├── NumberExtensions.kt
│       ├── DateExtensions.kt
│       └── ComposableExtensions.kt
│
├── domain/                                # Business logic (pure Kotlin)
│   ├── entity/
│   │   ├── User.kt
│   │   ├── Vehicle.kt
│   │   ├── Listing.kt
│   │   ├── Showroom.kt
│   │   ├── Favorite.kt
│   │   ├── Offer.kt
│   │   ├── Conversation.kt
│   │   ├── Message.kt
│   │   ├── Verification.kt
│   │   ├── Report.kt
│   │   ├── Location.kt
│   │   ├── Category.kt
│   │   └── Brand.kt
│   │
│   ├── repository/                       # Repository interfaces
│   │   ├── UserRepository.kt
│   │   ├── VehicleRepository.kt
│   │   ├── ListingRepository.kt
│   │   ├── ShowroomRepository.kt
│   │   ├── FavoriteRepository.kt
│   │   ├── OfferRepository.kt
│   │   ├── ConversationRepository.kt
│   │   ├── VerificationRepository.kt
│   │   ├── LocationRepository.kt
│   │   ├── CategoryRepository.kt
│   │   └── BrandRepository.kt
│   │
│   └── usecase/                          # Use cases (business rules)
│       ├── auth/
│       │   ├── LoginUseCase.kt
│       │   ├── RegisterUseCase.kt
│       │   └── LogoutUseCase.kt
│       │
│       ├── vehicle/
│       │   ├── SearchVehiclesUseCase.kt
│       │   ├── GetVehicleDetailUseCase.kt
│       │   ├── GetCategoryVehiclesUseCase.kt
│       │   └── CompareVehiclesUseCase.kt
│       │
│       ├── listing/
│       │   ├── CreateListingUseCase.kt
│       │   ├── UpdateListingUseCase.kt
│       │   ├── GetUserListingsUseCase.kt
│       │   ├── DeleteListingUseCase.kt
│       │   └── ChangeListingStatusUseCase.kt
│       │
│       ├── favorite/
│       │   ├── SaveVehicleUseCase.kt
│       │   ├── RemoveFavoriteUseCase.kt
│       │   ├── GetFavoritesUseCase.kt
│       │   └── CheckIfFavoritedUseCase.kt
│       │
│       ├── offer/
│       │   ├── MakeOfferUseCase.kt
│       │   ├── RespondToOfferUseCase.kt
│       │   ├── GetReceivedOffersUseCase.kt
│       │   └── GetSentOffersUseCase.kt
│       │
│       ├── messaging/
│       │   ├── SendMessageUseCase.kt
│       │   ├── GetConversationsUseCase.kt
│       │   ├── GetMessagesUseCase.kt
│       │   └── MarkMessageReadUseCase.kt
│       │
│       ├── showroom/
│       │   ├── CreateShowroomUseCase.kt
│       │   ├── GetShowroomUseCase.kt
│       │   └── UpdateShowroomInventoryUseCase.kt
│       │
│       └── verification/
│           ├── VerifyMobileUseCase.kt
│           ├── VerifyEmailUseCase.kt
│           └── VerifyRCUseCase.kt
│
├── data/                                  # Data sources & repositories
│   ├── local/
│   │   ├── dao/
│   │   │   ├── UserDao.kt
│   │   │   ├── VehicleDao.kt
│   │   │   ├── ListingDao.kt
│   │   │   ├── ShowroomDao.kt
│   │   │   ├── FavoriteDao.kt
│   │   │   ├── OfferDao.kt
│   │   │   ├── ConversationDao.kt
│   │   │   ├── MessageDao.kt
│   │   │   ├── VerificationDao.kt
│   │   │   ├── LocationDao.kt
│   │   │   ├── CategoryDao.kt
│   │   │   └── BrandDao.kt
│   │   │
│   │   ├── entity/                       # Room entities
│   │   │   ├── UserEntity.kt
│   │   │   ├── VehicleEntity.kt
│   │   │   ├── ListingEntity.kt
│   │   │   ├── ShowroomEntity.kt
│   │   │   ├── FavoriteEntity.kt
│   │   │   ├── OfferEntity.kt
│   │   │   ├── ConversationEntity.kt
│   │   │   ├── MessageEntity.kt
│   │   │   ├── VerificationEntity.kt
│   │   │   ├── LocationEntity.kt
│   │   │   ├── CategoryEntity.kt
│   │   │   └── BrandEntity.kt
│   │   │
│   │   └── converters/
│   │       ├── DateTimeConverters.kt
│   │       ├── EnumConverters.kt
│   │       └── TypeConverters.kt
│   │
│   ├── remote/                           # (Future) API sources
│   │   ├── dto/                          # API response models
│   │   │   ├── VehicleDto.kt
│   │   │   ├── ListingDto.kt
│   │   │   └── ...
│   │   │
│   │   └── service/
│   │       ├── VehicleService.kt
│   │       ├── ListingService.kt
│   │       └── ...
│   │
│   ├── repository/                       # Repository implementations
│   │   ├── UserRepositoryImpl.kt
│   │   ├── VehicleRepositoryImpl.kt
│   │   ├── ListingRepositoryImpl.kt
│   │   ├── ShowroomRepositoryImpl.kt
│   │   ├── FavoriteRepositoryImpl.kt
│   │   ├── OfferRepositoryImpl.kt
│   │   ├── ConversationRepositoryImpl.kt
│   │   ├── VerificationRepositoryImpl.kt
│   │   ├── LocationRepositoryImpl.kt
│   │   ├── CategoryRepositoryImpl.kt
│   │   └── BrandRepositoryImpl.kt
│   │
│   └── mapper/                           # DTO ↔ Domain mappers
│       ├── UserMapper.kt
│       ├── VehicleMapper.kt
│       ├── ListingMapper.kt
│       └── ...
│
└── presentation/                          # UI Layer (Jetpack Compose)
    ├── navigation/
    │   ├── AppNavigation.kt               # Root navigation
    │   ├── NavRoute.kt                    # Route definitions
    │   ├── BuyerNavigation.kt             # Buyer flows
    │   ├── SellerNavigation.kt            # Seller flows
    │   ├── ShowroomNavigation.kt          # Showroom flows
    │   ├── ProfileNavigation.kt           # Profile flows
    │   └── AuthNavigation.kt              # Auth flows
    │
    ├── theme/
    │   ├── Color.kt                       # Design colors
    │   ├── Typography.kt                  # Text styles
    │   ├── Shapes.kt                      # Shape definitions
    │   ├── Spacing.kt                     # Spacing constants
    │   ├── Theme.kt                       # Theme composition
    │   └── Icons.kt                       # Icon definitions
    │
    ├── components/                        # Reusable Compose components
    │   ├── cards/
    │   │   ├── VehicleCard.kt
    │   │   ├── ShowroomCard.kt
    │   │   ├── OfferCard.kt
    │   │   └── MessageCard.kt
    │   │
    │   ├── buttons/
    │   │   ├── PrimaryButton.kt
    │   │   ├── SecondaryButton.kt
    │   │   ├── ActionButton.kt
    │   │   ├── ChatButton.kt
    │   │   ├── CallButton.kt
    │   │   └── WhatsAppButton.kt
    │   │
    │   ├── inputs/
    │   │   ├── TextInput.kt
    │   │   ├── SearchBar.kt
    │   │   ├── PriceInput.kt
    │   │   ├── CitySelector.kt
    │   │   ├── CategorySelector.kt
    │   │   ├── BrandModelSelector.kt
    │   │   ├── PhotoPicker.kt
    │   │   └── VideoPicker.kt
    │   │
    │   ├── filters/
    │   │   ├── FilterChip.kt
    │   │   ├── PriceRangeSlider.kt
    │   │   ├── YearRangeSlider.kt
    │   │   ├── KMRangeSlider.kt
    │   │   └── MultiSelectFilter.kt
    │   │
    │   ├── display/
    │   │   ├── ImageCarousel.kt
    │   │   ├── VideoPlayer.kt
    │   │   ├── VerificationBadge.kt
    │   │   ├── PriceDisplay.kt
    │   │   ├── LocationBadge.kt
    │   │   ├── SpecificationRow.kt
    │   │   ├── VehicleSpecCard.kt
    │   │   └── SellerInfoCard.kt
    │   │
    │   ├── navigation/
    │   │   ├── BottomNavigationBar.kt
    │   │   ├── TopAppBar.kt
    │   │   ├── BackButton.kt
    │   │   └── TabBar.kt
    │   │
    │   ├── dialogs/
    │   │   ├── ConfirmDialog.kt
    │   │   ├── ReportDialog.kt
    │   │   ├── OfferDialog.kt
    │   │   └── FilterDialog.kt
    │   │
    │   ├── loaders/
    │   │   ├── LoadingIndicator.kt
    │   │   ├── ShimmerEffect.kt
    │   │   └── SkeletonLoader.kt
    │   │
    │   ├── empty/
    │   │   ├── EmptyState.kt
    │   │   ├── NoResultsState.kt
    │   │   └── ErrorState.kt
    │   │
    │   └── common/
    │       ├── Divider.kt
    │       ├── Spacer.kt
    │       ├── Badge.kt
    │       └── Tag.kt
    │
    ├── auth/                              # Authentication screens
    │   ├── AuthViewModel.kt
    │   ├── LoginScreen.kt
    │   ├── RegisterScreen.kt
    │   └── AuthState.kt
    │
    ├── home/                              # Home/Discovery
    │   ├── HomeViewModel.kt
    │   ├── HomeScreen.kt
    │   ├── HomeState.kt
    │   └── components/
    │       ├── CitySelector.kt
    │       ├── QuickCategoryBar.kt
    │       └── FeatureSection.kt
    │
    ├── explore/                           # Search & Explore
    │   ├── ExploreViewModel.kt
    │   ├── ExploreScreen.kt
    │   ├── SearchScreen.kt
    │   ├── FilterScreen.kt
    │   ├── ExploreState.kt
    │   └── components/
    │       ├── FilterPanel.kt
    │       ├── SortOptions.kt
    │       └── ResultsList.kt
    │
    ├── vehicle_detail/                    # Vehicle Detail
    │   ├── VehicleDetailViewModel.kt
    │   ├── VehicleDetailScreen.kt
    │   ├── DetailState.kt
    │   └── components/
    │       ├── PhotoGallery.kt
    │       ├── SpecificationSection.kt
    │       ├── SellerSection.kt
    │       ├── ActionButtons.kt
    │       └── SimilarVehicles.kt
    │
    ├── comparison/                        # Comparison
    │   ├── ComparisonViewModel.kt
    │   ├── ComparisonScreen.kt
    │   ├── ComparisonState.kt
    │   └── components/
    │       ├── ComparisonTable.kt
    │       └── SelectionList.kt
    │
    ├── saved/                             # Saved/Favorites
    │   ├── SavedViewModel.kt
    │   ├── SavedScreen.kt
    │   ├── SavedState.kt
    │   └── components/
    │       ├── FavoritesList.kt
    │       └── ComparisonShortlist.kt
    │
    ├── sell/                              # Selling features
    │   ├── my_listings/
    │   │   ├── MyListingsViewModel.kt
    │   │   ├── MyListingsScreen.kt
    │   │   └── components/
    │   │
    │   ├── add_listing/
    │   │   ├── AddListingViewModel.kt
    │   │   ├── AddListingScreen.kt
    │   │   ├── Step1CategoryScreen.kt
    │   │   ├── Step2BasicInfoScreen.kt
    │   │   ├── Step3PricingScreen.kt
    │   │   ├── Step4PhotosScreen.kt
    │   │   ├── Step5DetailScreen.kt
    │   │   └── PreviewScreen.kt
    │   │
    │   └── inventory/
    │       ├── InventoryViewModel.kt
    │       └── InventoryScreen.kt
    │
    ├── chat/                              # Messaging
    │   ├── ConversationsViewModel.kt
    │   ├── ConversationsScreen.kt
    │   ├── ChatScreen.kt
    │   ├── ChatViewModel.kt
    │   ├── ChatState.kt
    │   └── components/
    │       ├── MessageBubble.kt
    │       ├── MessageInput.kt
    │       └── ConversationItem.kt
    │
    ├── offers/                            # Offers
    │   ├── OfferViewModel.kt
    │   ├── OfferScreen.kt
    │   ├── OfferState.kt
    │   └── components/
    │       ├── OfferForm.kt
    │       └── OfferHistory.kt
    │
    ├── showroom/                          # Showroom features
    │   ├── profile/
    │   │   ├── ShowroomProfileViewModel.kt
    │   │   ├── ShowroomProfileScreen.kt
    │   │   └── components/
    │   │
    │   ├── inventory/
    │   │   ├── ShowroomInventoryViewModel.kt
    │   │   └── ShowroomInventoryScreen.kt
    │   │
    │   └── management/
    │       ├── ShowroomManagementViewModel.kt
    │       └── ShowroomManagementScreen.kt
    │
    ├── profile/                           # User Profile
    │   ├── ProfileViewModel.kt
    │   ├── ProfileScreen.kt
    │   ├── EditProfileScreen.kt
    │   ├── SettingsScreen.kt
    │   ├── ProfileState.kt
    │   └── components/
    │       ├── ProfileHeader.kt
    │       └── MenuItems.kt
    │
    ├── verification/                      # Verification flows
    │   ├── VerificationViewModel.kt
    │   ├── MobileVerificationScreen.kt
    │   ├── EmailVerificationScreen.kt
    │   ├── RCVerificationScreen.kt
    │   └── BusinessVerificationScreen.kt
    │
    └── MainActivity.kt                    # Entry point
```

---

## Data Flow

### User Action → UI Update Flow

```
User Interaction (Compose)
            │
            ▼
ViewModel.Action()
            │
            ▼
UseCase.execute()
            │
            ▼
Repository.get/create/update()
            │
            ▼
Local Data Source (Room)
            │
            ▼
DAO Query/Insert/Update
            │
            ▼
Result/Flow emitted
            │
            ▼
ViewModel collects
            │
            ▼
ViewModel updates State
            │
            ▼
Composable recomposes
            │
            ▼
UI Updated
```

---

## Dependency Injection with Hilt

### Module Organization

```
core/di/
├── AppModule.kt              # Singleton instances
├── DatabaseModule.kt         # Room setup
├── RepositoryModule.kt       # Repository bindings
├── UseCaseModule.kt          # UseCase bindings
├── NetworkModule.kt          # Retrofit (future)
└── DispatchersModule.kt      # Coroutine Dispatchers
```

### Example Pattern

```kotlin
// AppModule.kt
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "vahanseva.db"
        ).build()
    }
}

// RepositoryModule.kt
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideUserRepository(
        userDao: UserDao
    ): UserRepository {
        return UserRepositoryImpl(userDao)
    }
}

// In ViewModel or UseCase
@HiltViewModel
class MyViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    // ...
}
```

---

## State Management with Flow

### ViewModel State Pattern

```kotlin
data class MyUiState(
    val isLoading: Boolean = false,
    val data: List<Vehicle> = emptyList(),
    val error: String? = null,
    val success: String? = null
)

@HiltViewModel
class MyViewModel @Inject constructor(
    private val repository: MyRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(MyUiState())
    val uiState: StateFlow<MyUiState> = _uiState.asStateFlow()
    
    fun loadData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val result = repository.getData()
                _uiState.update { 
                    it.copy(data = result, isLoading = false) 
                }
            } catch (e: Exception) {
                _uiState.update { 
                    it.copy(error = e.message, isLoading = false) 
                }
            }
        }
    }
}

// In Compose
@Composable
fun MyScreen(viewModel: MyViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsState()
    
    LaunchedEffect(Unit) {
        viewModel.loadData()
    }
    
    when {
        state.isLoading -> LoadingScreen()
        state.error != null -> ErrorScreen(state.error)
        state.data.isNotEmpty() -> DataScreen(state.data)
    }
}
```

---

## Repository Pattern

### Abstraction Layer

```kotlin
// Domain - Interface
interface UserRepository {
    suspend fun getUser(id: String): User
    suspend fun saveUser(user: User)
    fun getUserFlow(id: String): Flow<User>
}

// Data - Implementation
class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {
    
    override suspend fun getUser(id: String): User {
        return userDao.getUser(id).toDomainModel()
    }
    
    override suspend fun saveUser(user: User) {
        userDao.insertUser(user.toEntity())
    }
    
    override fun getUserFlow(id: String): Flow<User> {
        return userDao.getUserFlow(id)
            .map { it.toDomainModel() }
    }
}
```

### Future API Integration

When APIs are implemented:

```kotlin
class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val userService: UserService  // New API source
) : UserRepository {
    
    override suspend fun getUser(id: String): User {
        return try {
            // Fetch from API, save to DB
            val user = userService.getUser(id)
            userDao.insertUser(user.toEntity())
            user.toDomainModel()
        } catch (e: Exception) {
            // Fallback to local cache
            userDao.getUser(id).toDomainModel()
        }
    }
}
```

---

## Navigation Structure

### Route Definitions

```kotlin
sealed class NavRoute(val route: String) {
    // Auth
    object Login : NavRoute("login")
    object Register : NavRoute("register")
    
    // Buyer Flow
    object Home : NavRoute("home")
    object Explore : NavRoute("explore")
    object VehicleDetail : NavRoute("vehicle/{id}")
    
    // Seller Flow
    object MyListings : NavRoute("my_listings")
    object AddListing : NavRoute("add_listing")
    
    // Profile
    object Profile : NavRoute("profile")
}
```

### Navigation Graph

```kotlin
@Composable
fun VahanSevaNavigation() {
    NavHost(navController, startDestination = "auth") {
        navigation(startDestination = NavRoute.Login.route, route = "auth") {
            composable(NavRoute.Login.route) { LoginScreen() }
            composable(NavRoute.Register.route) { RegisterScreen() }
        }
        
        navigation(startDestination = NavRoute.Home.route, route = "buyer") {
            composable(NavRoute.Home.route) { HomeScreen() }
            composable(NavRoute.Explore.route) { ExploreScreen() }
            composable(NavRoute.VehicleDetail.route) { VehicleDetailScreen() }
        }
    }
}
```

---

## Testing Strategy

### Unit Testing (ViewModels)

```kotlin
@HiltAndroidTest
class MyViewModelTest {
    
    @get:Rule
    val hiltRule = HiltAndroidRule(this)
    
    @Inject
    lateinit var repository: MyRepository
    
    private lateinit var viewModel: MyViewModel
    
    @Before
    fun setup() {
        hiltRule.inject()
        viewModel = MyViewModel(repository)
    }
    
    @Test
    fun loadData_updates_state() = runTest {
        viewModel.loadData()
        
        advanceUntilIdle()
        val state = viewModel.uiState.value
        
        assertTrue(state.data.isNotEmpty())
        assertFalse(state.isLoading)
    }
}
```

### Repository Testing

```kotlin
@Test
fun getUser_returns_from_dao() = runTest {
    val user = User(id = "1", name = "John")
    userDao.insertUser(user.toEntity())
    
    val result = repository.getUser("1")
    
    assertEquals(user, result)
}
```

---

## Key Architectural Decisions

| Decision | Rationale |
|----------|-----------|
| **MVVM** | Clear separation of concerns, testable, reactive |
| **Clean Architecture** | Independent of frameworks, testable, flexible |
| **Repository Pattern** | Abstraction between data sources and domain |
| **Hilt DI** | Industry standard, reduces boilerplate |
| **Jetpack Compose** | Modern, declarative UI, better testability |
| **Room Database** | Type-safe, observable, handles migrations |
| **Flow/StateFlow** | Reactive, cancellable, cold streams |
| **Use Cases** | Single responsibility, reusable business logic |
| **Mock Data** | No backend dependency, full feature development |

---

## Future Considerations

### API Integration Path

1. Create `RemoteDataSource` implementations
2. Create API DTOs and mappers
3. Update repository to choose between remote/local
4. Add network interceptors (auth, logging)
5. Update error handling for network errors

### Caching Strategy

- Room acts as single source of truth
- API data written to Room on successful fetch
- App works offline with cached data
- Pull-to-refresh triggers fresh fetch
- Stale data handling for older listings

### Offline Support

- Core marketplace features work offline
- Chat/messaging queued locally, synced on reconnect
- Listing creation drafts saved locally

---

**END OF ANDROID ARCHITECTURE DOCUMENT**

*Last Updated: August 10, 2026*  
*Version: 1.0.0*
