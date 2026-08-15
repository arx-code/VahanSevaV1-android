# VahanSeva Development Roadmap

## Project Setup Status ✅

- [x] Git repository initialized
- [x] Project structure created (MVVM + Clean Architecture)
- [x] CLAUDE.md documentation added
- [x] GitHub setup guide created
- [x] Commit history established

## Phase 1: Foundation

### Authentication System
- [x] **Login Screen** - Email/password authentication
- [x] **Register Screen** - New user signup
- [ ] **Forgot Password** - Password recovery
- [ ] **Token Management** - JWT token handling and refresh
- [ ] **Session Management** - User state persistence

**Key ViewModels**: `AuthViewModel`  
**Key Screens**: `LoginScreen`, `RegisterScreen`, `ForgotPasswordScreen`

### Core Navigation
- [x] **Bottom Navigation** - Main navigation structure
- [x] **Navigation Routing** - Complete app navigation flow
- [ ] **Deep Linking** - Handle deep links to specific listings

**Files**: `VahanSevaNavigation.kt`, `Screen.kt`, `MainActivity.kt`

---

## Phase 2: Browse & Search

### Car Listing Browse
- [x] **Home/Browse Screen** - Display car listings with pagination
- [x] **Car List ViewModel** - State management for car listings
- [x] **Paging Implementation** - Infinite scroll with Paging 3
- [x] **Pull to Refresh** - Refresh car listings

**Key ViewModel**: `CarListViewModel`  
**Key UI**: `HomeScreen`, `CarListItem`, `EmptyState`

### Search & Filter
- [x] **Search Screen** - Global car search functionality
- [x] **Filter Screen** - Advanced filtering options
- [x] **Filter Models**:
    - Price range (min/max)
    - Mileage range
    - Brand/Model selection
    - Fuel type (Petrol, Diesel, Electric, Hybrid)
    - Transmission (Manual, Automatic)
    - Location-based search
    - Year range

**Key Files**: `SearchFilters.kt`, `CarService.kt` (API endpoints)

---

## Phase 3: Car Details & Interactions

### Car Detail View
- [x] **Car Detail Screen** - Complete car information
- [x] **Image Gallery** - View multiple car images
- [x] **Seller Information** - Display seller profile/ratings
- [x] **Contact Seller** - Action to message seller
- [ ] **Similar Cars** - Recommendations section

**Key ViewModel**: `CarDetailViewModel`  
**Key Components**: `CarDetailScreen`, `ImageCarousel`, `SellerCard`

### Favorites Management
- [ ] **Add/Remove Favorites** - Toggle favorite status
- [ ] **Favorites Screen** - Wishlist view
- [ ] **Favorite Counter** - Badge showing favorites count
- [ ] **Persistence** - Save favorites locally and sync with API

**Key ViewModel**: `FavoriteViewModel`  
**Key Repository**: `FavoriteRepository`

---

## Phase 4: Messaging System

### Real-time Messaging
- [ ] **Conversations List** - Display active chats
- [ ] **Chat Screen** - Message thread with seller/buyer
- [ ] **Message Input** - Text input and send functionality
- [ ] **Read Receipts** - Mark messages as read
- [ ] **Typing Indicator** - Show when other user is typing

**Key ViewModel**: `MessageViewModel`  
**Key Screens**: `ConversationsScreen`, `ChatScreen`

### Notifications
- [ ] **FCM Setup** - Firebase Cloud Messaging integration
- [ ] **New Message Alerts** - Notify user of new messages
- [ ] **Message Badges** - Unread message count

---

## Phase 5: User Profile & Listings Management 

### User Profile
- [ ] **Profile Screen** - User information display
- [ ] **Edit Profile** - Update user details
- [ ] **Profile Picture Upload** - User avatar management
- [ ] **Ratings & Reviews** - Display user ratings
- [ ] **Seller Statistics** - Total listings, sold cars, rating

**Key ViewModel**: `ProfileViewModel`  
**Key Screens**: `ProfileScreen`, `EditProfileScreen`

### My Listings (Seller Features)
- [ ] **My Listings Screen** - Show user's posted cars
- [ ] **Add Car Listing** - Multi-step form to post new car
    - Step 1: Basic info (brand, model, year, price)
    - Step 2: Details (mileage, fuel, transmission, color)
    - Step 3: Images upload
    - Step 4: Location & description
    - Step 5: Review & submit
- [ ] **Edit Listing** - Modify existing listing
- [ ] **Delete Listing** - Remove car from marketplace
- [ ] **Listing Status** - Active, sold, inactive states

**Key ViewModel**: `MyListingsViewModel`  
**Key Screens**: `MyListingsScreen`, `AddCarScreen`, `ImageUploadScreen`

---

## Phase 6: Image Handling & Upload

### Image Management
- [ ] **Image Picker** - Select multiple images from gallery
- [ ] **Camera Integration** - Take photos directly
- [ ] **Image Compression** - Optimize before upload
- [ ] **Upload Progress** - Show upload percentage
- [ ] **Image Display** - Cache and display efficiently

**Library**: Coil or Glide (to be integrated)  
**Files**: `ImageUploadService`, `ImageCompressionUtil`

---

## Phase 7: Advanced Features 

### Search Analytics
- [ ] **Search History** - Recently searched terms
- [ ] **Popular Searches** - Trending search terms
- [ ] **Search Suggestions** - Auto-complete functionality

### Social Features
- [ ] **Seller Reviews** - Rate and review sellers
- [ ] **Report Listing** - Flag suspicious/fraudulent listings
- [ ] **Blocking Users** - Block communication with users

### Performance Optimization
- [ ] **Offline Mode** - Cache listings for offline browsing
- [ ] **Performance Profiling** - Measure app startup time
- [ ] **Memory Optimization** - Profile and optimize memory usage

---

## Phase 8: Testing & Quality

### Unit Tests
- [ ] ViewModel tests for all major ViewModels
- [ ] Repository tests with mocked services
- [ ] DAO tests for database operations

### Integration Tests
- [ ] Database integration tests
- [ ] API mock tests
- [ ] End-to-end message flow tests

### UI Tests
- [ ] Navigation tests
- [ ] Screen rendering tests
- [ ] User interaction tests

---

## Phase 9: Deployment & Release

### Release Preparation
- [ ] ProGuard/R8 configuration
- [ ] App signing setup
- [ ] Version management (build numbers, version codes)
- [ ] Release notes preparation

### Play Store Release
- [ ] Create app listing on Google Play Console
- [ ] Upload signed APK/AAB
- [ ] Set up privacy policy
- [ ] Configure in-app updates

---

## Wireframe Mapping

### Wireframe → Screen Implementation

**Note**: Please provide your wireframes so we can map them to specific screens and adjust the roadmap accordingly.

Common screens expected:
1. **Splash/Onboarding** → `SplashScreen`, `OnboardingScreen`
2. **Authentication** → `LoginScreen`, `RegisterScreen`, `ForgotPasswordScreen`
3. **Home/Browse** → `HomeScreen`, `CarListScreen`
4. **Search** → `SearchScreen`, `FilterScreen`
5. **Car Detail** → `CarDetailScreen`
6. **Favorites** → `FavoritesScreen`
7. **Messages** → `ConversationsScreen`, `ChatScreen`
8. **Profile** → `ProfileScreen`, `EditProfileScreen`
9. **My Listings** → `MyListingsScreen`, `AddCarScreen`
10. **Settings** → `SettingsScreen`

---

## Development Guidelines

### Branch Naming
```
feature/screen-name          # New features/screens
fix/bug-description          # Bug fixes
refactor/component-name      # Code refactoring
docs/guide-name              # Documentation
```

### Commit Messages
```
feat: Add login screen with email/password authentication
fix: Resolve crash when loading empty car list
docs: Update API integration guide
refactor: Simplify CarRepository logic
```

### Code Review Checklist
- [ ] Follows MVVM pattern
- [ ] Uses Hilt for DI
- [ ] Proper error handling
- [ ] Unit tests included
- [ ] No hardcoded strings (use strings.xml)
- [ ] Proper null safety
- [ ] Performance considerations

### Testing Requirements
- ViewModels: 80%+ coverage
- Repositories: 70%+ coverage
- UI: Critical user paths covered

---

## Resources & References

### Documentation
- [CLAUDE.md](CLAUDE.md) - Project overview and tech stack
- [DEVELOPMENT.md](DEVELOPMENT.md) - Development guidelines
- [GITHUB_SETUP.md](GITHUB_SETUP.md) - GitHub workflow instructions

### External Resources
- [Android Developer Docs](https://developer.android.com)
- [Jetpack Compose Samples](https://github.com/android/compose-samples)
- [Architecture Components Guide](https://developer.android.com/topic/architecture)

---

## Next Steps

1. **Share wireframes** - Upload your wireframes/mockups so we can map features to screens
2. **Set up GitHub repository** - Follow [GITHUB_SETUP.md](GITHUB_SETUP.md)
3. **Configure API endpoint** - Update `BASE_URL` in `NetworkModule.kt`
4. **Start Phase 1** - Begin with authentication system
5. **Regular commits** - Push changes frequently with descriptive messages

---

**Status**: Ready for feature development  
**Last Updated**: August 10, 2026  
**Next Review**: After wireframe review
