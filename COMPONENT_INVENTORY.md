# Component Inventory - Vahan Seva Auto-Mall

**Version**: 1.0.0  
**Date**: August 10, 2026  
**Total Components**: 35+ reusable Compose components  
**Framework**: Jetpack Compose + Material 3

---

## Component Categories

```
presentation/components/
├── cards/           # Card components (6)
├── buttons/         # Button variants (7)
├── inputs/          # Input & selector components (8)
├── display/         # Display-only components (9)
├── navigation/      # Navigation elements (4)
├── dialogs/         # Modal dialogs (4)
├── loaders/         # Loading states (3)
├── empty/           # Empty states (3)
└── common/          # Utility components (4)
```

---

## 🃏 Cards (6 components)

### 1. VehicleCard
**Purpose**: Primary vehicle listing display  
**Location**: `components/cards/VehicleCard.kt`

**Props**:
```kotlin
@Composable
fun VehicleCard(
    vehicle: Vehicle,
    onCardClick: (String) -> Unit,
    onFavoriteClick: (String) -> Unit,
    isFavorite: Boolean = false,
    modifier: Modifier = Modifier
)
```

**Features**:
- 1:1 aspect ratio image
- Brand + Model as title
- Year, Fuel, Transmission metadata (dot-separated)
- KM driven display
- Price in ₹ format (prominent)
- Location with icon
- Favorite button (heart icon)
- Rounded corners (24px)
- Shadow-sm elevation

**Used In**: HomeScreen, ExploreScreen, SavedScreen, SearchResults

---

### 2. ShowroomCard
**Purpose**: Showroom preview card  
**Location**: `components/cards/ShowroomCard.kt`

**Props**:
```kotlin
@Composable
fun ShowroomCard(
    showroom: Showroom,
    onCardClick: (String) -> Unit,
    modifier: Modifier = Modifier
)
```

**Features**:
- Showroom logo/image
- Showroom name + verification badge
- Location
- Vehicle count
- Categories served
- Rounded corners
- Click to view profile

**Used In**: HomeScreen, ExploreScreen

---

### 3. OfferCard
**Purpose**: Display offer (sent/received)  
**Location**: `components/cards/OfferCard.kt`

**Props**:
```kotlin
@Composable
fun OfferCard(
    offer: Offer,
    offerType: OfferType, // SENT or RECEIVED
    onCardClick: (String) -> Unit,
    onAccept: ((String) -> Unit)? = null,
    onReject: ((String) -> Unit)? = null,
    modifier: Modifier = Modifier
)
```

**Features**:
- Vehicle thumbnail + basic info
- Listed price vs. offered price
- Offer message
- Status badge (Pending/Accepted/Rejected)
- Accept/Reject buttons (for received offers)
- Timestamp

**Used In**: MyOffersScreen, ProfileScreen

---

### 4. MessageCard
**Purpose**: Conversation preview in chat list  
**Location**: `components/cards/MessageCard.kt`

**Props**:
```kotlin
@Composable
fun MessageCard(
    conversation: Conversation,
    onCardClick: (String) -> Unit,
    modifier: Modifier = Modifier
)
```

**Features**:
- Sender avatar
- Sender name + verification badge
- Last message preview (truncated)
- Vehicle thumbnail (context)
- Unread count badge
- Timestamp
- Click to open chat

**Used In**: ConversationsListScreen

---

### 5. ListingCard
**Purpose**: Seller's own listing (My Listings)  
**Location**: `components/cards/ListingCard.kt`

**Props**:
```kotlin
@Composable
fun ListingCard(
    listing: Listing,
    onEdit: (String) -> Unit,
    onDelete: (String) -> Unit,
    onChangeStatus: (String, ListingStatus) -> Unit,
    modifier: Modifier = Modifier
)
```

**Features**:
- Similar to VehicleCard but with management actions
- Status badge (Available/Reserved/Sold/Draft)
- View count, favorite count, offer count
- Edit/Delete actions
- Status change dropdown

**Used In**: MyListingsScreen, SellScreen

---

### 6. SellerInfoCard
**Purpose**: Seller information on vehicle detail  
**Location**: `components/cards/SellerInfoCard.kt`

**Props**:
```kotlin
@Composable
fun SellerInfoCard(
    seller: User,
    showroom: Showroom? = null,
    onChatClick: () -> Unit,
    onCallClick: () -> Unit,
    onWhatsAppClick: () -> Unit,
    modifier: Modifier = Modifier
)
```

**Features**:
- Seller/Showroom name
- Verification badges
- Location
- Rating (if available)
- Chat, Call, WhatsApp buttons
- Rounded container

**Used In**: VehicleDetailScreen

---

## 🔘 Buttons (7 components)

### 1. PrimaryButton
**Props**:
```kotlin
@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    loading: Boolean = false,
    modifier: Modifier = Modifier
)
```

**Style**: Primary color, pill-shaped, white text, 48dp height

---

### 2. SecondaryButton
**Props**: Same as PrimaryButton

**Style**: White background, primary border, primary text

---

### 3. TextButton
**Props**: Same as PrimaryButton (no border, transparent background)

---

### 4. ChatButton
**Props**:
```kotlin
@Composable
fun ChatButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
)
```

**Style**: Icon + label, primary container background

---

### 5. CallButton
**Props**: Same as ChatButton

**Style**: Green icon + "Call" label

---

### 6. WhatsAppButton
**Props**: Same as ChatButton

**Style**: WhatsApp green icon + label

---

### 7. FAB (Floating Action Button)
**Props**:
```kotlin
@Composable
fun VahanSevaFAB(
    icon: ImageVector,
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
)
```

**Style**: Primary color, elevated (shadow-lg), icon + label

---

## 📝 Inputs (8 components)

### 1. SearchBar
**Location**: `components/inputs/SearchBar.kt`

**Props**:
```kotlin
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    placeholder: String = "Search vehicles...",
    modifier: Modifier = Modifier
)
```

**Features**:
- Leading search icon
- Rounded (24px)
- Shadow-sm
- Focus border (primary color)
- Clear button (when text present)

**Used In**: HomeScreen, ExploreScreen

---

### 2. TextInput
**Props**:
```kotlin
@Composable
fun TextInput(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String = "",
    error: String? = null,
    enabled: Boolean = true,
    leadingIcon: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier
)
```

**Style**: Standard Material 3 text field, rounded corners

---

### 3. PriceInput
**Props**: Similar to TextInput but with ₹ prefix and number formatting

---

### 4. CitySelector
**Props**:
```kotlin
@Composable
fun CitySelector(
    selectedCity: String?,
    onCitySelected: (String) -> Unit,
    modifier: Modifier = Modifier
)
```

**Features**:
- Dropdown or bottom sheet
- Search within cities
- Location icon
- GPS auto-detect option

**Used In**: HomeScreen, AddListingScreen

---

### 5. CategorySelector
**Props**:
```kotlin
@Composable
fun CategorySelector(
    categories: List<Category>,
    selectedCategory: Category?,
    onCategorySelected: (Category) -> Unit,
    modifier: Modifier = Modifier
)
```

**Style**: Horizontal scrolling chips or grid

---

### 6. BrandModelSelector
**Props**:
```kotlin
@Composable
fun BrandModelSelector(
    vehicleType: VehicleType,
    selectedBrand: String?,
    selectedModel: String?,
    onBrandSelected: (String) -> Unit,
    onModelSelected: (String) -> Unit,
    modifier: Modifier = Modifier
)
```

**Features**:
- Two-step selection (brand first, then model)
- Searchable dropdowns

---

### 7. PhotoPicker
**Props**:
```kotlin
@Composable
fun PhotoPicker(
    photos: List<Uri>,
    onPhotosSelected: (List<Uri>) -> Unit,
    onPhotoRemoved: (Uri) -> Unit,
    maxPhotos: Int = 10,
    modifier: Modifier = Modifier
)
```

**Features**:
- Grid of selected photos
- Add photo button (camera + gallery)
- Reorder photos (drag & drop)
- Remove photo
- Primary photo indicator

**Used In**: AddListingScreen (Step 4)

---

### 8. VideoPicker
**Props**: Similar to PhotoPicker but for single video or YouTube link

---

## 🖼️ Display Components (9 components)

### 1. ImageCarousel
**Props**:
```kotlin
@Composable
fun ImageCarousel(
    images: List<String>,
    modifier: Modifier = Modifier,
    onImageClick: (Int) -> Unit = {}
)
```

**Features**:
- Horizontal pager
- Page indicators (dots)
- Swipe to change
- Click to full-screen
- Zoom support (full-screen mode)

**Used In**: VehicleDetailScreen

---

### 2. VideoPlayer
**Props**:
```kotlin
@Composable
fun VideoPlayer(
    videoUrl: String,
    thumbnail: String?,
    modifier: Modifier = Modifier
)
```

**Features**:
- YouTube embed or local video
- Thumbnail before play
- Play/pause controls

---

### 3. VerificationBadge
**Props**:
```kotlin
@Composable
fun VerificationBadge(
    verificationType: VerificationType, // MOBILE, EMAIL, RC, BUSINESS
    modifier: Modifier = Modifier
)
```

**Style**: Small badge with checkmark icon + label

---

### 4. PriceDisplay
**Props**:
```kotlin
@Composable
fun PriceDisplay(
    price: Double,
    currency: String = "₹",
    style: PriceStyle = PriceStyle.LARGE,
    modifier: Modifier = Modifier
)
```

**Features**:
- Formatted with commas (Indian numbering)
- Large/medium/small variants
- Primary color (teal)

---

### 5. LocationBadge
**Props**:
```kotlin
@Composable
fun LocationBadge(
    location: String,
    icon: Boolean = true,
    modifier: Modifier = Modifier
)
```

**Style**: Icon + text, compact display

---

### 6. SpecificationRow
**Props**:
```kotlin
@Composable
fun SpecificationRow(
    label: String,
    value: String,
    modifier: Modifier = Modifier
)
```

**Style**: Label (left, secondary text) + Value (right, primary text)

---

### 7. VehicleSpecCard
**Props**:
```kotlin
@Composable
fun VehicleSpecCard(
    specifications: Map<String, String>,
    modifier: Modifier = Modifier
)
```

**Features**:
- Grid of SpecificationRow items
- Rounded container
- Section headers

**Used In**: VehicleDetailScreen

---

### 8. StatusBadge
**Props**:
```kotlin
@Composable
fun StatusBadge(
    status: ListingStatus, // AVAILABLE, RESERVED, SOLD, DRAFT
    modifier: Modifier = Modifier
)
```

**Style**: Colored pill badge with appropriate color per status

---

### 9. MetadataRow
**Props**:
```kotlin
@Composable
fun MetadataRow(
    items: List<String>, // ["2019", "Diesel", "Automatic"]
    separator: String = "•",
    modifier: Modifier = Modifier
)
```

**Style**: Horizontal text with dot separators

---

## 🧭 Navigation (4 components)

### 1. BottomNavigationBar
**Props**:
```kotlin
@Composable
fun BottomNavigationBar(
    currentRoute: String,
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier
)
```

**Features**:
- 5 tabs: Home, Explore, Sell, Saved, Profile
- Active state (secondary color + scale animation)
- Badge support (for unread messages)

---

### 2. TopAppBar
**Props**:
```kotlin
@Composable
fun TopAppBar(
    title: String,
    onBackClick: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    modifier: Modifier = Modifier
)
```

**Features**:
- Blur background (80% opacity)
- Back button (conditional)
- Title
- Trailing actions (icons)

---

### 3. BackButton
**Props**:
```kotlin
@Composable
fun BackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
)
```

**Style**: Icon button with back arrow

---

### 4. TabBar
**Props**:
```kotlin
@Composable
fun TabBar(
    tabs: List<String>,
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
)
```

**Style**: Material 3 tabs with indicator

---

## 💬 Dialogs (4 components)

### 1. ConfirmDialog
**Props**:
```kotlin
@Composable
fun ConfirmDialog(
    title: String,
    message: String,
    confirmText: String = "Confirm",
    cancelText: String = "Cancel",
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
)
```

---

### 2. ReportDialog
**Props**:
```kotlin
@Composable
fun ReportDialog(
    reportType: ReportType, // LISTING, USER, SHOWROOM, MESSAGE
    onSubmit: (ReportReason, String) -> Unit,
    onDismiss: () -> Unit
)
```

**Features**:
- Reason selection (dropdown)
- Optional details (text area)
- Submit button

---

### 3. OfferDialog
**Props**:
```kotlin
@Composable
fun OfferDialog(
    listingPrice: Double,
    onSubmit: (Double, String) -> Unit,
    onDismiss: () -> Unit
)
```

**Features**:
- Listed price display
- Offer price input
- Optional message
- Submit button

**Used In**: VehicleDetailScreen

---

### 4. FilterDialog
**Props**:
```kotlin
@Composable
fun FilterDialog(
    currentFilters: SearchFilters,
    onApplyFilters: (SearchFilters) -> Unit,
    onDismiss: () -> Unit
)
```

**Features**:
- Multiple filter sections
- Price range slider
- Year range slider
- KM range slider
- Multi-select (fuel type, transmission)
- Apply/Reset buttons

**Used In**: ExploreScreen

---

## ⏳ Loaders (3 components)

### 1. LoadingIndicator
**Props**:
```kotlin
@Composable
fun LoadingIndicator(
    modifier: Modifier = Modifier
)
```

**Style**: Circular progress indicator (primary color)

---

### 2. ShimmerEffect
**Props**:
```kotlin
@Composable
fun ShimmerEffect(
    modifier: Modifier = Modifier
)
```

**Features**:
- Animated gradient shimmer
- For skeleton loaders

---

### 3. SkeletonLoader
**Props**:
```kotlin
@Composable
fun SkeletonVehicleCard(
    modifier: Modifier = Modifier
)
```

**Features**:
- Vehicle card shape with shimmer
- Multiple variants (card, list item, detail)

---

## 📭 Empty States (3 components)

### 1. EmptyState
**Props**:
```kotlin
@Composable
fun EmptyState(
    icon: ImageVector,
    title: String,
    message: String,
    actionLabel: String? = null,
    onActionClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
)
```

**Style**: Centered, icon + title + message + optional button

---

### 2. NoResultsState
**Props**: Same as EmptyState with search-specific defaults

---

### 3. ErrorState
**Props**:
```kotlin
@Composable
fun ErrorState(
    error: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
)
```

**Features**:
- Error icon
- Error message
- Retry button

---

## 🛠️ Common Utilities (4 components)

### 1. Divider
**Props**: Standard Material Divider with theme color

---

### 2. Spacer
**Props**:
```kotlin
@Composable
fun VerticalSpacer(height: Dp)

@Composable
fun HorizontalSpacer(width: Dp)
```

---

### 3. Badge
**Props**:
```kotlin
@Composable
fun Badge(
    count: Int,
    modifier: Modifier = Modifier
)
```

**Style**: Small circular badge with count (for notifications)

---

### 4. Tag
**Props**:
```kotlin
@Composable
fun Tag(
    text: String,
    color: Color = MaterialTheme.colorScheme.primary,
    modifier: Modifier = Modifier
)
```

**Style**: Small pill-shaped tag

---

## Component Usage Summary

| Component | Used In (# of screens) | Priority |
|-----------|------------------------|----------|
| VehicleCard | 5 screens | HIGH |
| PrimaryButton | 10+ screens | HIGH |
| SearchBar | 2 screens | HIGH |
| TopAppBar | 15+ screens | HIGH |
| BottomNavigationBar | 1 (main scaffold) | HIGH |
| ImageCarousel | 1 screen | HIGH |
| TextInput | 10+ screens | HIGH |
| LoadingIndicator | All screens | HIGH |
| EmptyState | 8 screens | MEDIUM |
| ShowroomCard | 2 screens | MEDIUM |
| OfferCard | 2 screens | MEDIUM |
| FilterDialog | 1 screen | MEDIUM |
| PhotoPicker | 1 screen | MEDIUM |
| Others | Specific use cases | LOW-MEDIUM |

---

## Implementation Priority

### Phase 1 (Foundation)
1. Buttons (Primary, Secondary, Text)
2. TextInput
3. LoadingIndicator
4. TopAppBar
5. BottomNavigationBar

### Phase 2 (Core Cards)
6. VehicleCard
7. ShowroomCard
8. EmptyState

### Phase 3 (Search & Browse)
9. SearchBar
10. FilterDialog
11. CategorySelector
12. CitySelector

### Phase 4 (Detail & Interaction)
13. ImageCarousel
14. SellerInfoCard
15. OfferDialog
16. MessageCard

### Phase 5 (Selling)
17. PhotoPicker
18. ListingCard
19. StatusBadge

### Phase 6 (Polish)
20. All remaining components

---

**END OF COMPONENT INVENTORY**

*Last Updated: August 10, 2026*  
*Version: 1.0.0*  
*Total Components: 48*
