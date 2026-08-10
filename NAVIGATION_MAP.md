# Navigation Map - Vahan Seva Auto-Mall

**Version**: 1.0.0  
**Date**: August 10, 2026  
**Status**: Complete Navigation Hierarchy

---

## Overall Navigation Structure

```
┌─────────────────────────────────────┐
│         Application Root            │
├─────────────────────────────────────┤
│                                     │
├─ Authentication Flow (Stack 1)      │
│  ├─ Splash/Onboarding              │
│  ├─ Login                           │
│  └─ Register                        │
│                                     │
├─ Main App (Stack 2 - After Auth)   │
│  ├─ Bottom Navigation (5 Tabs)      │
│  │  ├─ Home Tab                    │
│  │  ├─ Explore Tab                 │
│  │  ├─ Sell Tab                    │
│  │  ├─ Saved Tab                   │
│  │  └─ Profile Tab                 │
│  │                                 │
│  └─ Modal Stacks (Overlays)        │
│     ├─ Chat/Messaging              │
│     ├─ Vehicle Detail              │
│     ├─ Comparison                  │
│     └─ Other Modals                │
│                                     │
└─────────────────────────────────────┘
```

---

## Screen Hierarchy

### 1. Authentication Stack

```
splash_screen
    │
    ├─→ login_screen
    │   └─→ forgot_password (optional)
    │       └─→ back to login
    │
    └─→ register_screen
        └─→ email_verification
            └─→ success → navigate to home
```

**Screens**:
- `SplashScreen` - App branding, initialization
- `LoginScreen` - Mobile/Email/Google login
- `RegisterScreen` - Account creation
- `ForgotPasswordScreen` - Password recovery
- `EmailVerificationScreen` - Email confirmation

**Flow Logic**:
```kotlin
sealed class NavRoute(val route: String) {
    // Auth routes
    object Splash : NavRoute("splash")
    object Login : NavRoute("login")
    object Register : NavRoute("register")
    object ForgotPassword : NavRoute("forgot_password")
    object EmailVerification : NavRoute("email_verification")
}
```

---

### 2. Home Tab

```
home_screen (ENTRY POINT)
    │
    ├─→ city_selector (Dialog/Modal)
    │   └─→ back to home with city selected
    │
    ├─→ category_click
    │   └─→ navigate to explore (with category pre-selected)
    │
    ├─→ vehicle_card_click
    │   └─→ vehicle_detail_modal
    │
    └─→ showroom_card_click
        └─→ showroom_profile_modal
```

**Screens**:
- `HomeScreen` - Main discovery experience
  - City selector
  - Quick category shortcuts
  - Available vehicles carousel
  - Popular vehicles section
  - Showrooms nearby section

**Components**:
- CitySelector (Dialog)
- VehicleCard (clickable)
- ShowroomCard (clickable)
- CategoryPill (clickable)

---

### 3. Explore Tab

```
explore_screen (ENTRY POINT)
    │
    ├─→ search_bar_focus
    │   └─→ search_results_screen (or stays in explore with overlay)
    │
    ├─→ filter_button
    │   └─→ filter_modal/bottom_sheet
    │       ├─→ location_filter
    │       ├─→ price_filter
    │       ├─→ year_filter
    │       ├─→ fuel_type_filter
    │       ├─→ transmission_filter
    │       ├─→ km_filter
    │       └─→ seller_type_filter
    │
    ├─→ sort_dropdown
    │   └─→ sort_options_bottom_sheet
    │
    ├─→ category_selector
    │   └─→ category_picker
    │
    ├─→ vehicle_card_click
    │   └─→ vehicle_detail_screen
    │
    └─→ save_icon_click
        └─→ add_to_favorites (local state update)
```

**Screens**:
- `ExploreScreen` - Main search/browse interface
- `SearchScreen` - Search results display
- `FilterScreen` - Advanced filters (Bottom Sheet)
- `SortScreen` - Sort options (Bottom Sheet)
- `CategoryPickerScreen` - Category selection

**Navigation Definition**:
```kotlin
object Explore : NavRoute("explore")
object Search : NavRoute("search/{query}")
object FilterModal : NavRoute("filter")
object SortModal : NavRoute("sort")
object CategoryPicker : NavRoute("category_picker")
```

---

### 4. Vehicle Detail Flow

```
vehicle_detail_screen (Modal from Home or Explore)
    │
    ├─→ photo_gallery
    │   └─→ full_screen_photo_viewer
    │
    ├─→ save_button
    │   └─→ toggle_favorite (local state)
    │
    ├─→ share_button
    │   └─→ share_modal
    │       ├─→ WhatsApp
    │       ├─→ Facebook
    │       ├─→ Twitter
    │       ├─→ SMS
    │       ├─→ Email
    │       └─→ Copy Link
    │
    ├─→ compare_button
    │   └─→ comparison_screen (navigate to Saved tab comparison)
    │
    ├─→ chat_button
    │   └─→ chat_screen (Modal or navigate)
    │
    ├─→ call_button
    │   └─→ native_phone_app
    │
    ├─→ whatsapp_button
    │   └─→ native_whatsapp_app
    │
    ├─→ make_offer_button
    │   └─→ offer_modal
    │       ├─→ enter_price
    │       ├─→ add_message
    │       └─→ submit_offer
    │
    └─→ seller_card_click
        └─→ showroom_profile_screen
```

**Screens**:
- `VehicleDetailScreen` - Complete vehicle information
- `FullScreenPhotoViewer` - Photo gallery
- `ShareModal` - Share options
- `OfferModal` - Make offer form
- `ChatScreen` - Messaging

---

### 5. Saved Tab

```
saved_screen (ENTRY POINT)
    │
    ├─→ favorites_tab
    │   ├─→ vehicle_card_click
    │   │   └─→ vehicle_detail_screen
    │   │
    │   ├─→ remove_favorite
    │   │   └─→ remove_from_saved
    │   │
    │   └─→ add_to_comparison
    │       └─→ update_comparison_list
    │
    └─→ comparison_tab
        ├─→ comparison_table
        │   └─→ scroll_horizontally
        │
        ├─→ vehicle_card_click
        │   └─→ vehicle_detail_screen
        │
        ├─→ remove_from_comparison
        │   └─→ update_comparison_list
        │
        └─→ clear_comparison
            └─→ clear_all
```

**Screens**:
- `SavedScreen` - Favorites and comparison
  - FavoritesTab - Saved vehicles
  - ComparisonTab - Comparison shortlist

**Tabs**:
- Favorites (default)
- Comparison

---

### 6. Sell Tab (Buyer Listing Creation)

```
sell_screen (ENTRY POINT)
    │
    ├─→ my_listings_view
    │   ├─→ available_vehicles
    │   │   └─→ listing_card_click → edit_listing_screen
    │   │
    │   ├─→ reserved_vehicles
    │   │   └─→ listing_card_click → listing_detail_screen
    │   │
    │   ├─→ sold_vehicles
    │   │   └─→ listing_card_click → listing_detail_screen (read-only)
    │   │
    │   └─→ draft_vehicles
    │       └─→ listing_card_click → continue_listing (resume multi-step)
    │
    └─→ add_listing_button
        └─→ add_listing_flow
            ├─→ step_1_category_screen
            │   └─→ next → step_2
            │
            ├─→ step_2_basic_info_screen
            │   ├─→ next → step_3
            │   └─→ back → step_1
            │
            ├─→ step_3_pricing_screen
            │   ├─→ next → step_4
            │   └─→ back → step_2
            │
            ├─→ step_4_photos_screen
            │   ├─→ next → step_5
            │   ├─→ back → step_3
            │   └─→ photo_picker/camera
            │
            ├─→ step_5_details_screen
            │   ├─→ next → preview
            │   └─→ back → step_4
            │
            ├─→ preview_listing_screen
            │   ├─→ edit (go back to any step)
            │   ├─→ publish
            │   └─→ save_draft
            │
            └─→ success_screen
                └─→ close → my_listings_view
```

**Screens**:
- `SellScreen` - Main sell tab
- `MyListingsScreen` - User's listings
- `AddListingScreen` - Multi-step form
  - Step 1: Category Selection
  - Step 2: Basic Info (Brand, Model, Year, etc.)
  - Step 3: Pricing & Location
  - Step 4: Photos & Video
  - Step 5: Additional Details
- `PreviewListingScreen` - Review before publish
- `SuccessScreen` - Listing published

---

### 7. Profile Tab

```
profile_screen (ENTRY POINT)
    │
    ├─→ profile_info_section
    │   └─→ edit_profile_button
    │       └─→ edit_profile_screen
    │           ├─→ edit_name
    │           ├─→ edit_bio
    │           ├─→ edit_photo
    │           │   └─→ photo_picker/camera
    │           ├─→ save
    │           └─→ back
    │
    ├─→ my_vehicles_section
    │   └─→ view_all
    │       └─→ my_vehicles_list_screen
    │
    ├─→ my_listings_section
    │   └─→ view_all
    │       └─→ my_listings_screen
    │
    ├─→ my_offers_section
    │   └─→ view_all
    │       └─→ my_offers_screen
    │           ├─→ sent_offers_tab
    │           │   └─→ offer_card_click → offer_detail
    │           │
    │           └─→ received_offers_tab
    │               └─→ offer_card_click → respond_to_offer_screen
    │
    ├─→ messages_section
    │   └─→ view_all
    │       └─→ conversations_list_screen
    │           └─→ conversation_click
    │               └─→ chat_screen
    │
    ├─→ my_showroom_section (if seller/showroom)
    │   └─→ showroom_profile_screen
    │       ├─→ edit_showroom
    │       ├─→ inventory_management
    │       └─→ leads_tracking
    │
    ├─→ verification_section
    │   ├─→ mobile_verification_button
    │   │   └─→ mobile_verification_screen
    │   │
    │   ├─→ email_verification_button
    │   │   └─→ email_verification_screen
    │   │
    │   ├─→ rc_verification_button
    │   │   └─→ rc_verification_screen
    │   │
    │   └─→ business_verification_button (if showroom)
    │       └─→ business_verification_screen
    │
    ├─→ account_settings_button
    │   └─→ settings_screen
    │       ├─→ change_password
    │       ├─→ notification_preferences
    │       ├─→ privacy_settings
    │       ├─→ about
    │       └─→ logout
    │
    └─→ logout
        └─→ return_to_auth_stack
```

**Screens**:
- `ProfileScreen` - Main profile view
- `EditProfileScreen` - Edit user info
- `MyVehiclesScreen` - List of owned vehicles
- `MyListingsScreen` - Active listings
- `MyOffersScreen` - Sent/received offers
- `ConversationsListScreen` - Chat conversations
- `ShowroomProfileScreen` - Showroom info (if applicable)
- `MobileVerificationScreen` - Mobile verification flow
- `EmailVerificationScreen` - Email verification flow
- `RCVerificationScreen` - RC verification flow
- `BusinessVerificationScreen` - Business verification (showroom)
- `SettingsScreen` - App settings

---

## Route Definitions

```kotlin
sealed class NavRoute(val route: String) {
    // Auth
    object Splash : NavRoute("splash")
    object Login : NavRoute("login")
    object Register : NavRoute("register")
    
    // Bottom Tabs
    object Home : NavRoute("home")
    object Explore : NavRoute("explore")
    object Sell : NavRoute("sell")
    object Saved : NavRoute("saved")
    object Profile : NavRoute("profile")
    
    // Home flows
    object CitySelector : NavRoute("city_selector")
    
    // Explore flows
    object SearchResults : NavRoute("search/{query}")
    object FilterModal : NavRoute("filter")
    object SortModal : NavRoute("sort")
    
    // Shared modals
    object VehicleDetail : NavRoute("vehicle/{id}")
    object ShowroomProfile : NavRoute("showroom/{id}")
    object Chat : NavRoute("chat/{conversationId}")
    object ConversationsList : NavRoute("conversations")
    
    // Comparison
    object Comparison : NavRoute("comparison")
    
    // Sell flows
    object MyListings : NavRoute("my_listings")
    object AddListing : NavRoute("add_listing")
    object AddListingStep1 : NavRoute("add_listing/step1")
    object AddListingStep2 : NavRoute("add_listing/step2")
    object AddListingStep3 : NavRoute("add_listing/step3")
    object AddListingStep4 : NavRoute("add_listing/step4")
    object AddListingStep5 : NavRoute("add_listing/step5")
    object PreviewListing : NavRoute("preview_listing/{draftId}")
    
    // Profile flows
    object EditProfile : NavRoute("edit_profile")
    object MyVehicles : NavRoute("my_vehicles")
    object MyOffers : NavRoute("my_offers")
    object ShowroomManagement : NavRoute("showroom_management")
    object Settings : NavRoute("settings")
    
    // Verification
    object MobileVerification : NavRoute("verify/mobile")
    object EmailVerification : NavRoute("verify/email")
    object RCVerification : NavRoute("verify/rc")
    object BusinessVerification : NavRoute("verify/business")
}
```

---

## Navigation Implementation Pattern

```kotlin
@Composable
fun VahanSevaNavigation() {
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = "auth") {
        // Authentication Graph
        navigation(startDestination = NavRoute.Splash.route, route = "auth") {
            composable(NavRoute.Splash.route) { SplashScreen(navController) }
            composable(NavRoute.Login.route) { LoginScreen(navController) }
            composable(NavRoute.Register.route) { RegisterScreen(navController) }
        }
        
        // Main App Graph (with bottom nav)
        navigation(startDestination = NavRoute.Home.route, route = "main") {
            composable(NavRoute.Home.route) { HomeScreen(navController) }
            composable(NavRoute.Explore.route) { ExploreScreen(navController) }
            composable(NavRoute.Sell.route) { SellScreen(navController) }
            composable(NavRoute.Saved.route) { SavedScreen(navController) }
            composable(NavRoute.Profile.route) { ProfileScreen(navController) }
            
            // Nested flows
            composable(NavRoute.VehicleDetail.route) { 
                VehicleDetailScreen(navController) 
            }
            composable(NavRoute.Chat.route) { 
                ChatScreen(navController) 
            }
            // ... other flows
        }
    }
}
```

---

## Deep Linking

**Public Share URLs**:
```
vahansevaautomall.com/vehicle/12345
vahansevaautomall.com/showroom/67890
```

**Intent Filters** (AndroidManifest.xml):
```xml
<activity android:name=".MainActivity">
    <intent-filter>
        <action android:name="android.intent.action.VIEW" />
        <category android:name="android.intent.category.DEFAULT" />
        <category android:name="android.intent.category.BROWSABLE" />
        <data
            android:scheme="https"
            android:host="vahansevaautomall.com"
            android:pathPrefix="/vehicle" />
    </intent-filter>
</activity>
```

---

## Tab Navigation (Bottom Navigation)

```kotlin
data class BottomNavItem(
    val route: String,
    val label: String,
    val icon: @Composable () -> Unit
)

val bottomNavItems = listOf(
    BottomNavItem("home", "Home", { HomeIcon() }),
    BottomNavItem("explore", "Explore", { SearchIcon() }),
    BottomNavItem("sell", "Sell", { AddIcon() }),
    BottomNavItem("saved", "Saved", { HeartIcon() }),
    BottomNavItem("profile", "Profile", { ProfileIcon() })
)

@Composable
fun BottomNavigationBar(
    currentRoute: String,
    onNavigate: (String) -> Unit
) {
    NavigationBar {
        bottomNavItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = { onNavigate(item.route) },
                label = { Text(item.label) },
                icon = item.icon
            )
        }
    }
}
```

---

## Navigation State Preservation

**State saving across tab switches**:
```kotlin
val navController = rememberNavController()

NavHost(
    navController = navController,
    startDestination = "home",
    modifier = Modifier.fillMaxSize()
) {
    composable("home") { /* ... */ }
    composable("explore") { /* ... */ }
    // Each screen maintains its own state
}

// Bottom nav preserves backstack per tab
```

---

## Gesture Navigation

- **Back button**: Navigate to previous screen
- **Swipe back**: Android back gesture (if enabled)
- **Bottom nav**: Direct navigation to tab destination

---

**END OF NAVIGATION MAP**

*Last Updated: August 10, 2026*  
*Version: 1.0.0*
