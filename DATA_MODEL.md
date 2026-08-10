# Data Model - Vahan Seva Auto-Mall

**Version**: 1.0.0  
**Date**: August 10, 2026  
**Database**: Room (SQLite)  
**Schema Version**: 1

---

## Entity Relationship Diagram

```
User (1) ──→ (M) Listing
 │                  │
 ├─→ (M) Favorite ──┤
 │                  │
 ├─→ (M) Offer ─────┤
 │                  │
 ├─→ (M) Conversation
 │      │
 │      └─→ (M) Message
 │
 ├─→ (1) Showroom
 │
 └─→ (M) Verification

Listing (1) ──→ (M) Vehicle
      │
      ├─→ (M) Media (Photos/Videos)
      └─→ (M) InventoryItem (for showrooms)

Showroom (1) ──→ (M) InventoryItem
         │
         └─→ (M) Verification

Offer (M) ──→ (1) Listing
    │
    └─→ (1) User (buyer)

Favorite (M) ──→ (1) User
       │
       └─→ (1) Listing

Conversation (2) ──→ (M) Message
         │
         └─→ (1) Listing (context)

Report (M) ──→ (1) User (reporter)
     │
     └─→ (Polymorphic) Reported Entity
```

---

## 🗄️ Core Entities

### 1. USER

**Table**: `users`

```sql
CREATE TABLE users (
    id TEXT PRIMARY KEY,
    name TEXT NOT NULL,
    email TEXT,
    phone TEXT,
    profilePhotoUrl TEXT,
    bio TEXT,
    location TEXT,
    createdAt INTEGER NOT NULL,
    updatedAt INTEGER NOT NULL,
    isVerified BOOLEAN DEFAULT 0,
    mobileVerified BOOLEAN DEFAULT 0,
    emailVerified BOOLEAN DEFAULT 0
);
```

**Kotlin Entity**:
```kotlin
@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val email: String?,
    val phone: String?,
    val profilePhotoUrl: String?,
    val bio: String?,
    val location: String?,
    val createdAt: Long,
    val updatedAt: Long,
    val isVerified: Boolean = false,
    val mobileVerified: Boolean = false,
    val emailVerified: Boolean = false
)
```

**Domain Model**:
```kotlin
data class User(
    val id: String,
    val name: String,
    val email: String?,
    val phone: String?,
    val profilePhotoUrl: String?,
    val bio: String?,
    val location: String?,
    val createdAt: Long,
    val updatedAt: Long,
    val isVerified: Boolean = false,
    val mobileVerified: Boolean = false,
    val emailVerified: Boolean = false,
    val verificationBadges: List<VerificationBadge> = emptyList()
)
```

---

### 2. VEHICLE

**Table**: `vehicles`

```sql
CREATE TABLE vehicles (
    id TEXT PRIMARY KEY,
    category TEXT NOT NULL,          -- CAR, BIKE, TRUCK, etc.
    brand TEXT NOT NULL,
    model TEXT NOT NULL,
    year INTEGER NOT NULL,
    fuelType TEXT NOT NULL,          -- PETROL, DIESEL, ELECTRIC, etc.
    transmission TEXT NOT NULL,      -- MANUAL, AUTOMATIC
    kmDriven INTEGER,
    owners INTEGER,
    registrationNumber TEXT,
    color TEXT,
    engineSize TEXT,
    condition TEXT,                  -- EXCELLENT, GOOD, FAIR, POOR
    previousAccidents BOOLEAN,
    insuranceValid BOOLEAN,
    pucValid BOOLEAN,
    createdAt INTEGER NOT NULL
);
```

---

### 3. LISTING

**Table**: `listings`

```sql
CREATE TABLE listings (
    id TEXT PRIMARY KEY,
    vehicleId TEXT NOT NULL,
    sellerId TEXT NOT NULL,
    showroomId TEXT,
    price REAL NOT NULL,
    description TEXT,
    city TEXT NOT NULL,
    locality TEXT,
    latitude REAL,
    longitude REAL,
    status TEXT NOT NULL,            -- DRAFT, PENDING, PUBLISHED, AVAILABLE, RESERVED, SOLD
    views INTEGER DEFAULT 0,
    saves INTEGER DEFAULT 0,
    createdAt INTEGER NOT NULL,
    updatedAt INTEGER NOT NULL,
    publishedAt INTEGER,
    FOREIGN KEY(vehicleId) REFERENCES vehicles(id),
    FOREIGN KEY(sellerId) REFERENCES users(id),
    FOREIGN KEY(showroomId) REFERENCES showrooms(id)
);
```

---

### 4. MEDIA

**Table**: `media`

```sql
CREATE TABLE media (
    id TEXT PRIMARY KEY,
    listingId TEXT NOT NULL,
    type TEXT NOT NULL,              -- PHOTO, VIDEO
    url TEXT NOT NULL,
    youtubeUrl TEXT,
    thumbnailUrl TEXT,
    isPrimary BOOLEAN DEFAULT 0,
    position INTEGER,
    createdAt INTEGER NOT NULL,
    FOREIGN KEY(listingId) REFERENCES listings(id)
);
```

---

### 5. SHOWROOM

**Table**: `showrooms`

```sql
CREATE TABLE showrooms (
    id TEXT PRIMARY KEY,
    ownerId TEXT NOT NULL,
    name TEXT NOT NULL,
    description TEXT,
    logoUrl TEXT,
    address TEXT NOT NULL,
    city TEXT NOT NULL,
    phone TEXT,
    website TEXT,
    isVerified BOOLEAN DEFAULT 0,
    createdAt INTEGER NOT NULL,
    updatedAt INTEGER NOT NULL,
    FOREIGN KEY(ownerId) REFERENCES users(id)
);
```

---

### 6. INVENTORY_ITEM

**Table**: `inventory_items`

```sql
CREATE TABLE inventory_items (
    id TEXT PRIMARY KEY,
    showroomId TEXT NOT NULL,
    listingId TEXT NOT NULL,
    status TEXT NOT NULL,            -- AVAILABLE, RESERVED, SOLD
    addedAt INTEGER NOT NULL,
    FOREIGN KEY(showroomId) REFERENCES showrooms(id),
    FOREIGN KEY(listingId) REFERENCES listings(id)
);
```

---

### 7. FAVORITE

**Table**: `favorites`

```sql
CREATE TABLE favorites (
    id TEXT PRIMARY KEY,
    userId TEXT NOT NULL,
    listingId TEXT NOT NULL,
    createdAt INTEGER NOT NULL,
    FOREIGN KEY(userId) REFERENCES users(id),
    FOREIGN KEY(listingId) REFERENCES listings(id),
    UNIQUE(userId, listingId)
);
```

---

### 8. OFFER

**Table**: `offers`

```sql
CREATE TABLE offers (
    id TEXT PRIMARY KEY,
    listingId TEXT NOT NULL,
    buyerId TEXT NOT NULL,
    offeredPrice REAL NOT NULL,
    message TEXT,
    status TEXT NOT NULL,            -- PENDING, ACCEPTED, REJECTED, COUNTERED
    createdAt INTEGER NOT NULL,
    respondedAt INTEGER,
    FOREIGN KEY(listingId) REFERENCES listings(id),
    FOREIGN KEY(buyerId) REFERENCES users(id)
);
```

---

### 9. CONVERSATION

**Table**: `conversations`

```sql
CREATE TABLE conversations (
    id TEXT PRIMARY KEY,
    buyerId TEXT NOT NULL,
    sellerId TEXT NOT NULL,
    listingId TEXT,
    lastMessageAt INTEGER NOT NULL,
    createdAt INTEGER NOT NULL,
    FOREIGN KEY(buyerId) REFERENCES users(id),
    FOREIGN KEY(sellerId) REFERENCES users(id),
    FOREIGN KEY(listingId) REFERENCES listings(id),
    UNIQUE(buyerId, sellerId, listingId)
);
```

---

### 10. MESSAGE

**Table**: `messages`

```sql
CREATE TABLE messages (
    id TEXT PRIMARY KEY,
    conversationId TEXT NOT NULL,
    senderId TEXT NOT NULL,
    receiverId TEXT NOT NULL,
    content TEXT NOT NULL,
    isRead BOOLEAN DEFAULT 0,
    createdAt INTEGER NOT NULL,
    FOREIGN KEY(conversationId) REFERENCES conversations(id),
    FOREIGN KEY(senderId) REFERENCES users(id),
    FOREIGN KEY(receiverId) REFERENCES users(id)
);
```

---

### 11. VERIFICATION

**Table**: `verifications`

```sql
CREATE TABLE verifications (
    id TEXT PRIMARY KEY,
    userId TEXT NOT NULL,
    type TEXT NOT NULL,              -- MOBILE, EMAIL, RC, BUSINESS
    value TEXT,
    documentUrl TEXT,
    status TEXT NOT NULL,            -- PENDING, VERIFIED, REJECTED
    createdAt INTEGER NOT NULL,
    verifiedAt INTEGER,
    FOREIGN KEY(userId) REFERENCES users(id)
);
```

---

### 12. REPORT

**Table**: `reports`

```sql
CREATE TABLE reports (
    id TEXT PRIMARY KEY,
    reporterId TEXT NOT NULL,
    reportedType TEXT NOT NULL,      -- LISTING, USER, SHOWROOM, MESSAGE
    reportedId TEXT NOT NULL,
    reason TEXT NOT NULL,
    details TEXT,
    status TEXT DEFAULT 'OPEN',      -- OPEN, INVESTIGATING, RESOLVED
    createdAt INTEGER NOT NULL,
    FOREIGN KEY(reporterId) REFERENCES users(id)
);
```

---

## 📋 DAOs (Data Access Objects)

### UserDao
```kotlin
@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)
    
    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun getUser(userId: String): UserEntity?
    
    @Query("SELECT * FROM users WHERE id = :userId")
    fun getUserFlow(userId: String): Flow<UserEntity?>
    
    @Update
    suspend fun updateUser(user: UserEntity)
    
    @Delete
    suspend fun deleteUser(user: UserEntity)
}
```

### VehicleDao
```kotlin
@Dao
interface VehicleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVehicle(vehicle: VehicleEntity)
    
    @Query("SELECT * FROM vehicles WHERE id = :vehicleId")
    suspend fun getVehicle(vehicleId: String): VehicleEntity?
    
    @Query("SELECT * FROM vehicles WHERE category = :category")
    fun getVehiclesByCategory(category: String): Flow<List<VehicleEntity>>
}
```

### ListingDao
```kotlin
@Dao
interface ListingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListing(listing: ListingEntity)
    
    @Query("""
        SELECT listings.*, vehicles.*, COUNT(DISTINCT favorites.id) as saveCount
        FROM listings
        JOIN vehicles ON listings.vehicleId = vehicles.id
        LEFT JOIN favorites ON listings.id = favorites.listingId
        WHERE listings.city = :city
        GROUP BY listings.id
        LIMIT :limit OFFSET :offset
    """)
    suspend fun getListingsByCity(
        city: String,
        limit: Int,
        offset: Int
    ): List<ListingWithVehicleAndSaveCount>
    
    @Query("SELECT * FROM listings WHERE id = :listingId")
    suspend fun getListing(listingId: String): ListingEntity?
    
    @Update
    suspend fun updateListing(listing: ListingEntity)
}
```

### Similar DAOs for other entities...

---

## 🔄 Repository Interfaces

```kotlin
// domain/repository/UserRepository.kt
interface UserRepository {
    suspend fun getUser(userId: String): User?
    fun getUserFlow(userId: String): Flow<User?>
    suspend fun saveUser(user: User)
    suspend fun updateUser(user: User)
}

// domain/repository/ListingRepository.kt
interface ListingRepository {
    suspend fun getListings(
        city: String,
        filters: SearchFilters,
        page: Int,
        pageSize: Int = 20
    ): List<Listing>
    
    suspend fun getListing(listingId: String): Listing?
    suspend fun saveListing(listing: Listing)
    suspend fun updateListingStatus(listingId: String, status: ListingStatus)
    suspend fun searchListings(query: String): List<Listing>
    fun getListingsFlow(city: String): Flow<List<Listing>>
}

// domain/repository/FavoriteRepository.kt
interface FavoriteRepository {
    suspend fun addFavorite(userId: String, listingId: String)
    suspend fun removeFavorite(userId: String, listingId: String)
    suspend fun isFavorited(userId: String, listingId: String): Boolean
    fun getFavoritesFlow(userId: String): Flow<List<Listing>>
}

// Similar for OfferRepository, ConversationRepository, etc.
```

---

## 💾 Database Setup

```kotlin
@Database(
    entities = [
        UserEntity::class,
        VehicleEntity::class,
        ListingEntity::class,
        MediaEntity::class,
        ShowroomEntity::class,
        InventoryItemEntity::class,
        FavoriteEntity::class,
        OfferEntity::class,
        ConversationEntity::class,
        MessageEntity::class,
        VerificationEntity::class,
        ReportEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun vehicleDao(): VehicleDao
    abstract fun listingDao(): ListingDao
    // ... other DAOs
    
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        
        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "vahanseva.db"
                )
                .fallbackToDestructiveMigration()
                .build()
                .also { INSTANCE = it }
            }
        }
    }
}
```

---

## 🔗 Type Converters

```kotlin
class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Long? = value
    
    @TypeConverter
    fun dateToTimestamp(date: Long?): Long? = date
    
    @TypeConverter
    fun fromListingStatus(status: ListingStatus?): String? =
        status?.name
    
    @TypeConverter
    fun toListingStatus(value: String?): ListingStatus? =
        value?.let { ListingStatus.valueOf(it) }
    
    // Similar converters for other enums and complex types
}
```

---

## 📝 Enums

```kotlin
enum class VehicleCategory {
    CAR, BIKE, SCOOTER, AUTO_RICKSHAW, TRUCK, VAN, BUS, TRACTOR, SUV, COMMERCIAL, OTHER
}

enum class FuelType {
    PETROL, DIESEL, ELECTRIC, HYBRID, CNG, LPG
}

enum class Transmission {
    MANUAL, AUTOMATIC
}

enum class ListingStatus {
    DRAFT, PENDING_REVIEW, PUBLISHED, AVAILABLE, RESERVED, SOLD, ARCHIVED, REJECTED, SUSPENDED, EXPIRED
}

enum class OfferStatus {
    PENDING, ACCEPTED, REJECTED, COUNTERED, EXPIRED
}

enum class VerificationType {
    MOBILE, EMAIL, RC, BUSINESS
}

enum class VerificationStatus {
    PENDING, VERIFIED, REJECTED
}

enum class ReportType {
    LISTING, USER, SHOWROOM, MESSAGE
}

enum class ReportReason {
    FAKE_LISTING, SCAM, INCORRECT_INFO, DUPLICATE, ALREADY_SOLD, OFFENSIVE, SUSPICIOUS_SELLER, OTHER
}
```

---

## 🔍 Important Queries

```kotlin
// Get featured listings for home screen
@Query("""
    SELECT * FROM listings
    WHERE status = 'AVAILABLE'
    AND city = :city
    ORDER BY publishedAt DESC
    LIMIT 20
""")
suspend fun getFeaturedListings(city: String): List<Listing>

// Search with filters
@Query("""
    SELECT * FROM listings
    WHERE status = 'AVAILABLE'
    AND city = :city
    AND price BETWEEN :minPrice AND :maxPrice
    AND (:category IS NULL OR :category = (SELECT category FROM vehicles WHERE vehicles.id = listings.vehicleId))
    ORDER BY publishedAt DESC
    LIMIT :limit OFFSET :offset
""")
suspend fun searchListings(
    city: String,
    minPrice: Double,
    maxPrice: Double,
    category: String?,
    limit: Int,
    offset: Int
): List<Listing>

// Get user's listings
@Query("""
    SELECT * FROM listings
    WHERE sellerId = :userId
    ORDER BY updatedAt DESC
""")
fun getUserListings(userId: String): Flow<List<Listing>>

// Get received offers
@Query("""
    SELECT * FROM offers
    WHERE listingId IN (
        SELECT id FROM listings WHERE sellerId = :userId
    )
    ORDER BY createdAt DESC
""")
fun getReceivedOffers(userId: String): Flow<List<Offer>>
```

---

**END OF DATA MODEL**

*Last Updated: August 10, 2026*  
*Version: 1.0.0*  
*Status: Ready for Implementation*
