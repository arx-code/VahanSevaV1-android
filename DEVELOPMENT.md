# VahanSeva Android Project - Development Guide

## Overview

This document covers the development practices, guidelines, and conventions for the VahanSeva Android project.

## Coding Standards

### Kotlin Style Guide
- Follow Google's Kotlin style guide
- Use 4 spaces for indentation
- Names: PascalCase for classes, camelCase for variables/functions
- Constants: UPPER_SNAKE_CASE in companion object

### File Organization
```kotlin
// 1. Package declaration
package com.vahanseva.auto_mall.presentation.viewmodel

// 2. Imports
import androidx.lifecycle.ViewModel
import com.vahanseva.auto_mall.data.model.Car

// 3. File-level comments if needed
/**
 * Description of the file's purpose
 */

// 4. Type aliases if needed
typealias CarCallback = (Car) -> Unit

// 5. Class/Interface definitions
class MyClass { ... }
```

## Architecture Decisions

### MVVM Pattern
- **Model**: Data classes in `data.model`
- **View**: Jetpack Compose screens in `presentation.screens`
- **ViewModel**: StateFlow-based state management in `presentation.viewmodel`

### Repository Pattern
- Single source of truth for data
- Abstracts data sources (local and remote)
- Handles caching logic

### Reactive Programming
- All async operations use Kotlin Flow
- ViewModels expose StateFlow for UI observation
- Repositories return Flow for reactive updates

## Common Tasks

### Adding a New Screen

1. Create ViewModel in `presentation/viewmodel/`:
```kotlin
@HiltViewModel
class MyViewModel @Inject constructor(
    private val repository: MyRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<MyUiState>(MyUiState.Loading)
    val uiState = _uiState.asStateFlow()
    // ... logic
}
```

2. Create UI in `presentation/screens/`:
```kotlin
@Composable
fun MyScreen(navController: NavController) {
    val viewModel: MyViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsState()
    // ... UI
}
```

3. Add route in `presentation/navigation/Screen.kt`:
```kotlin
object MyScreen : Screen("my_screen")
```

4. Add to navigation in `VahanSevaNavigation.kt`:
```kotlin
composable(Screen.MyScreen.route) {
    MyScreen(navController)
}
```

### Adding a New Data Entity

1. Create data class in `data/model/`:
```kotlin
@Entity(tableName = "my_table")
data class MyEntity(
    @PrimaryKey val id: String,
    val name: String
)
```

2. Create DAO in `data/local/`:
```kotlin
@Dao
interface MyDao {
    @Insert
    suspend fun insert(entity: MyEntity)
    
    @Query("SELECT * FROM my_table WHERE id = :id")
    fun getById(id: String): Flow<MyEntity?>
}
```

3. Add to database in `VahanSevaDatabase.kt`:
```kotlin
@Database(entities = [MyEntity::class, ...], version = 1)
abstract class VahanSevaDatabase : RoomDatabase() {
    abstract fun myDao(): MyDao
}
```

4. Create repository in `data/repository/`:
```kotlin
class MyRepository @Inject constructor(
    private val myDao: MyDao
) {
    fun getEntity(id: String) = myDao.getById(id)
}
```

### Adding API Endpoints

1. Define in service interface (`data/remote/`):
```kotlin
interface MyService {
    @GET("my-endpoint/{id}")
    suspend fun getEntity(@Path("id") id: String): MyEntity
}
```

2. Provide in Hilt module (`di/NetworkModule.kt`):
```kotlin
@Provides
@Singleton
fun provideMyService(retrofit: Retrofit): MyService {
    return retrofit.create(MyService::class.java)
}
```

3. Use in repository:
```kotlin
private val service: MyService
// Use: service.getEntity(id)
```

## Testing Guidelines

### ViewModel Tests
```kotlin
@Test
fun testLoadData() = runTest {
    val mockRepository = mockk<MyRepository>()
    coEvery { mockRepository.getData() } returns flowOf(testData)
    
    val viewModel = MyViewModel(mockRepository)
    
    viewModel.data.test {
        assertEquals(testData, awaitItem())
    }
}
```

### Repository Tests
```kotlin
@Test
fun testFetchData() = runTest {
    val mockService = mockk<MyService>()
    val mockDao = mockk<MyDao>()
    
    coEvery { mockService.getData() } returns testData
    
    val repository = MyRepository(mockService, mockDao)
    repository.fetchData().test {
        assertEquals(Result.Loading, awaitItem())
        assertEquals(Result.Success(testData), awaitItem())
    }
}
```

## Error Handling

Use sealed Result class:
```kotlin
sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
    object Loading : Result<Nothing>()
}
```

Handle in ViewModel:
```kotlin
repository.getData().collect { result ->
    _uiState.value = when (result) {
        is Result.Loading -> UiState.Loading
        is Result.Success -> UiState.Success(result.data)
        is Result.Error -> UiState.Error(result.exception.message)
    }
}
```

## Performance Tips

1. **Avoid recomposition**: Use `remember` and `mutableStateOf` properly
2. **Use correct Compose patterns**: Extract composables for reusability
3. **Lazy loading**: Use LazyColumn/LazyRow for lists
4. **Image optimization**: Cache images, use appropriate resolution
5. **Database queries**: Use proper indexes, avoid N+1 queries
6. **Pagination**: Use Paging 3 for large datasets

## Dependency Injection

### Providing Custom Objects
```kotlin
@Module
@InstallIn(SingletonComponent::class)
object CustomModule {
    @Provides
    @Singleton
    fun provideMyService(): MyService {
        return MyServiceImpl()
    }
}
```

### Using Qualifiers
```kotlin
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Remote

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Local

@Provides
@Remote
fun provideRemoteService(): Service = RemoteServiceImpl()

@Provides
@Local
fun provideLocalService(): Service = LocalServiceImpl()
```

## Debugging

### Logging
```kotlin
private val TAG = "MyTag"

Log.d(TAG, "Debug message")
Log.e(TAG, "Error message", throwable)
```

### Debugging Flow
```kotlin
repository.getData()
    .onEach { Log.d(TAG, "Emitted: $it") }
    .catch { Log.e(TAG, "Error", it) }
    .collect { ... }
```

## Release Checklist

- [ ] All tests passing
- [ ] Code review completed
- [ ] ProGuard/R8 rules configured
- [ ] API endpoints verified
- [ ] Database migrations tested
- [ ] UI/UX review completed
- [ ] Performance profiled
- [ ] Security review completed
- [ ] Version updated
- [ ] Release notes prepared

## Resources

- [Android Developer Documentation](https://developer.android.com)
- [Jetpack Compose Documentation](https://developer.android.com/jetpack/compose)
- [Kotlin Coroutines Guide](https://kotlinlang.org/docs/coroutines-overview.html)
- [Room Database Documentation](https://developer.android.com/training/data-storage/room)
- [Hilt Dependency Injection](https://developer.android.com/training/dependency-injection/hilt-android)

---

**Last Updated**: August 10, 2026
