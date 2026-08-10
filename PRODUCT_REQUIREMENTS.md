# Vahan Seva Auto-Mall - Product Requirements Document (PRD)

**Version**: 1.0.0  
**Date**: August 10, 2026  
**Status**: V1 Scope Finalized  
**Project Type**: Real Production Application

---

## Executive Summary

**Vahan Seva Auto-Mall is NOT a used-car app** — it's a **vehicle commerce platform** designed for the entire vehicle lifecycle.

### Core Vision
Build a digital marketplace and information ecosystem for all types of vehicles in India.

### Problem Statement
Today's vehicle buyers waste time visiting multiple dealers and markets without knowing what inventory is actually available. Sellers struggle to reach potential buyers efficiently.

### Solution
A digital marketplace where users can "know what's available before you visit" — enabling discovery, comparison, and connection before physical visits.

### Target Market
- **Geography**: India-first, starting with Tier-3 and smaller cities/towns
- **Scalable**: State → District → City → Locality → Pincode
- **Long-term**: Tier-3 → Tier-2 → Tier-1 → Nationwide

---

## Product Positioning

### Core Promise
"Know what's available before you visit."

### Long-term Journey
```
Discover → Compare → Verify → Connect → Buy → Manage → Sell → Repeat
```

### NOT a Classifieds App
This is a **vehicle commerce platform** that will eventually support:
- Marketplace (V1)
- Vehicle verification services
- Financing integration
- Insurance marketplace
- Inspection services
- Ownership transfer
- Vehicle lifecycle management

---

## Vehicle Coverage

### Initial Categories (Extensible)
1. 🚗 Cars
2. 🏍️ Motorcycles
3. 🛵 Scooters
4. 🛺 Auto-rickshaws
5. 🚚 Trucks
6. 🚐 Vans
7. 🚌 Buses
8. 🚜 Tractors
9. 🚙 SUVs
10. ⚙️ Commercial vehicles
11. ➕ Other vehicles

**Architecture Requirement**: Category system must be extensible.

---

## User Personas

### 1. 👤 Buyer (Primary User)

**Can do WITHOUT account:**
- Browse marketplace
- Search vehicles
- Apply filters
- View vehicle details
- View showroom profiles
- Compare vehicles
- Share listings

**Requires account for:**
- ❤️ Save/favorite vehicles
- 💬 Chat with sellers
- 📞 Track contact actions (Call/WhatsApp)
- 💰 Make offers

**User Goals:**
- Find available vehicles quickly
- Compare options efficiently
- Verify vehicle information
- Connect with trusted sellers
- Make informed decisions

### 2. 👤 Individual Seller

**Capabilities:**
- Register and verify account (mobile/email)
- List vehicles for sale
- Upload multiple photos + optional video
- Manage listing states (Available/Reserved/Sold)
- Receive and manage enquiries
- Receive and respond to offers
- Track listing performance (views, saves, contacts)

**User Goals:**
- Reach more buyers
- Manage listings easily
- Receive qualified leads
- Track listing performance

### 3. 🏢 Showroom / Dealer

**Capabilities:**
- Create showroom identity/brand
- Build public showroom profile
- Manage complete inventory (multiple vehicles)
- Publish and update vehicles
- Receive and manage leads
- Track offers across inventory
- Get verified (business verification badge)
- Manage showroom information

**User Goals:**
- Establish digital presence
- Showcase complete inventory
- Manage vehicles efficiently
- Build trust through verification
- Generate quality leads

---

## Account Philosophy

### Browsing = No Friction
Guest users can fully explore the marketplace without creating an account.

### Account Required For
- ❤️ Saving favorites
- 💬 Messaging
- 💰 Making offers
- 📞 Platform-tracked contact
- 🚗 Selling vehicles
- 🏢 Managing showroom

**Rationale**: Keep marketplace friction low while enabling engagement tracking where needed.

---

## Authentication (V1 Scope)

### V1 Implementation: **Mock Authentication**
- **UI fully implemented**
- **Local storage only** (no backend)
- **Session management** (local)
- **User state** stored locally

### Supported Methods (UI Only)
- 📱 Mobile number
- 📧 Email address
- 🔵 Google Sign-In (UI prepared, mock in V1)

### Identity Linking
Architecture should support linking multiple identity providers to one unified user account (prevents duplicate accounts).

---

## Application Structure

### Bottom Navigation (5 Tabs)

```
┌─────────────────────────────────────────┐
│           Screen Content                │
├─────────────────────────────────────────┤
│ Home │ Explore │ Sell │ Saved │ Profile │
└─────────────────────────────────────────┘
```

#### 1. **Home** 🏠
- Discovery experience
- City selection (GPS auto-detect + manual)
- Quick category access
- Available vehicles nearby
- Popular vehicles
- Featured showrooms
- Personalized recommendations (later)

#### 2. **Explore** 🔍
- Search bar
- Category browsing
- Filter interface
- Search results
- Sort options

#### 3. **Sell** ➕
- Quick entry to listing creation
- Seller dashboard
- My listings management

#### 4. **Saved** ❤️
- Favorite vehicles
- Comparison shortlist
- Recently viewed (later)

#### 5. **Profile** 👤
- My Vehicles (for buyers who become sellers)
- My Listings
- My Offers (sent/received)
- Messages
- My Showroom (if applicable)
- Verification status
- Account settings

---

## Core Features (V1 Scope)

### 🔍 Discovery & Search

#### Home Experience
```
Good morning 👋

Find your next vehicle

🔍 Search vehicles...

📍 [Detected City] ▼

[Category Pills: Cars | Bikes | Trucks | SUVs | More]

────────────────

Available near you
[Vehicle Card Grid]

────────────────

Popular vehicles
[Vehicle Card List]

────────────────

Showrooms near you
[Showroom Card List]
```

#### Search & Filters

**Search by:**
- Vehicle name/brand/model
- Keywords

**Basic Filters:**
- 📍 Location (City)
- 🚗 Vehicle type/category
- 🏷️ Brand
- 📦 Model
- 💰 Price range
- 📅 Year range
- ⛽ Fuel type (Petrol, Diesel, Electric, Hybrid, CNG, LPG)
- 🛠️ KM driven range
- ⚙️ Transmission (Manual, Automatic)
- 👤 Seller type (Individual, Showroom)

**Sort Options:**
- Latest
- Price: Low to High
- Price: High to Low
- KM: Low to High
- Year: Newest First
- Year: Oldest First

**V1 Implementation**: Local filtering on mock data (architecture ready for backend search)

---

### 🚗 Vehicle Listing

#### Required Information
- Vehicle type/category
- Brand
- Model
- Year of manufacture
- Fuel type
- KM driven / Usage
- Registration number
- Asking price
- Location (City + locality)
- Photos (minimum 3, recommended 8-10)

#### Optional Information
- Variant
- Transmission type
- Number of previous owners
- Insurance validity
- PUC (Pollution Under Control) validity
- Service history
- Accident history
- Overall condition
- Detailed description
- Video (YouTube link or upload)
- Additional specifications (color, engine size, etc.)

**Dynamic Form**: Form fields change based on vehicle category selected.

---

### 📸 Media Requirements

#### Photos
- **Minimum**: 3 photos
- **Recommended**: 8-10 photos
- **Suggested angles**:
  - Front view
  - Rear view
  - Left side
  - Right side
  - Interior dashboard
  - Interior seats
  - Engine bay
  - Odometer reading
  - Documents (RC, insurance)
  - Any damage/issues

**V1 Implementation**: Local storage, mock upload

#### Video
- **Optional**
- **Options**:
  - Upload local video (mock in V1)
  - YouTube link
  - Support for other platform links

---

### 🔖 Vehicle Detail Page

**Most important screen in the application.**

```
┌────────────────────────────────────┐
│ [Photo Gallery / Carousel]         │
├────────────────────────────────────┤
│ BMW X5                             │
│ ₹45,00,000                         │
│                                    │
│ 2019 • Diesel • Automatic          │
│ 52,000 km driven                   │
│ 📍 Pune, Maharashtra               │
│                                    │
│ ❤️ Save    ↗️ Share    ⚖️ Compare │
├────────────────────────────────────┤
│                                    │
│ Vehicle Information                │
│ ┌──────────────────────────────┐  │
│ │ Brand:        BMW             │  │
│ │ Model:        X5              │  │
│ │ Year:         2019            │  │
│ │ Fuel:         Diesel          │  │
│ │ Transmission: Automatic       │  │
│ │ KM Driven:    52,000          │  │
│ │ Owners:       1st Owner       │  │
│ │ Insurance:    Valid till 2027 │  │
│ │ PUC:          Valid           │  │
│ └──────────────────────────────┘  │
│                                    │
│ Condition & Description            │
│ [Condition details]                │
│                                    │
│ Seller Information                 │
│ ┌──────────────────────────────┐  │
│ │ ABC Motors                    │  │
│ │ ✓ Verified Showroom           │  │
│ │ 📍 Pune                       │  │
│ └──────────────────────────────┘  │
│                                    │
│ [💬 Chat] [📞 Call] [🟢 WhatsApp] │
│                                    │
│ [💰 Make an Offer]                 │
│                                    │
│ Similar Vehicles                   │
│ [Vehicle Cards]                    │
└────────────────────────────────────┘
```

---

### ⚖️ Vehicle Comparison

**V1 includes comparison feature.**

**Capabilities:**
- Select 2-4 vehicles
- Side-by-side comparison
- Compare across vehicle categories (where meaningful)

**Comparison Attributes:**
- Price
- Year
- KM driven
- Fuel type
- Transmission
- Number of owners
- Location
- Key specifications
- Seller type

**UX:**
- Add to comparison from listing cards
- Access from Saved tab
- Comparison shortlist visible
- Remove from comparison
- Clear all

---

### ❤️ Save / Favorite

**Capabilities:**
- Save unlimited vehicles
- Access from Saved tab
- Quick toggle on/off
- Visual indicator on cards

**Future Enhancements** (Post-V1):
- Price change notifications
- Vehicle sold notifications
- Similar vehicle alerts
- Listing updated notifications

---

### ↗️ Sharing

**Every public vehicle gets a shareable URL:**
```
vahansevaautomall.com/vehicle/[listing-id]
```

**Share Preview:**
```
🚗 BMW X5
₹45L • 2019 • Diesel
📍 Pune
View on Vahan Seva Auto-Mall
```

**Channels:**
- WhatsApp
- Facebook
- Twitter
- Instagram
- SMS
- Email
- Copy link

**Important**: Recipients should be able to view listing without installing the app (web view).

---

### 💬 Chat System

**V1 Implementation**: Real-time chat UI (mock data, local storage)

**Chat Features:**
- Buyer ↔ Individual Seller
- Buyer ↔ Showroom
- Conversation list
- Message threads
- Unread count badges

**Pre-filled Message:**
When opening chat, auto-generate:
```
"Hi, I'm interested in your [Brand Model Year] listed on Vahan Seva Auto-Mall. Is it still available?"
```
User can edit before sending.

**Message Context:**
- Link to vehicle listing
- Vehicle thumbnail in conversation
- Quick vehicle info

---

### 📞 Contact Options

**From Vehicle Detail:**
```
[💬 Chat] [📞 Call] [🟢 WhatsApp]
```

**Behavior:**
- **Chat**: Opens in-app messaging
- **Call**: Opens phone dialer with seller's number
- **WhatsApp**: Opens WhatsApp with pre-filled message (just links in V1)

**Analytics Tracking** (for future monetization):
- Listing views
- Save actions
- Chat initiated
- Call button clicked
- WhatsApp button clicked
- Offers sent

---

### 💰 Offers System

**V1 Implementation**: Full offer system with states/workflow

**Offer Flow:**
```
Buyer views vehicle
    ↓
Clicks "Make an Offer"
    ↓
Enters offered price
    ↓
Adds message (optional)
    ↓
Submits offer
    ↓
Seller receives notification
    ↓
Seller views offer
    ↓
Seller responds (Accept/Reject/Counter)
```

**Offer States:**
- Pending
- Accepted
- Rejected
- Countered
- Expired (optional)

**Offer Card:**
```
┌──────────────────────────────┐
│ Listed Price: ₹8,50,000      │
│ Your Offer:   ₹7,80,000      │
│                              │
│ Message:                     │
│ "Is the price negotiable?"   │
│                              │
│ [Send Offer]                 │
└──────────────────────────────┘
```

**Notifications:**
- Buyer: Offer status changes
- Seller: New offer received

---

### 🚗 Seller Dashboard (My Listings)

**Listing States:**
```
Draft
 ↓
Pending Review (optional moderation)
 ↓
Published
 ↓
Available
 ↓
Reserved
 ↓
Sold
 ↓
Archived
```

**Other States:**
- Rejected (if moderation enabled)
- Suspended (if reported)
- Expired (if time-limited)

**Dashboard Sections:**
```
My Listings

Available (Active)
[ Vehicle Card ] - Views: 142 | Saves: 8 | Offers: 2

Reserved
[ Vehicle Card ] - Reserved for: ABC

Sold
[ Vehicle Card ] - Sold on: Aug 5, 2026

Drafts
[ Vehicle Card ] - Incomplete
```

**Actions:**
- Edit listing
- Mark as Reserved
- Mark as Sold
- Delete/Archive
- Pause/Unpublish
- Republish

---

### ➕ Sell Vehicle Flow

**V1 Implementation**: 5-step flow, data collection, local storage

**Step 1: Vehicle Category & Type**
- Select category (Car, Bike, Truck, etc.)
- Sub-type selection if applicable

**Step 2: Basic Information**
- Brand
- Model
- Year
- Fuel type
- Transmission
- KM driven
- Registration number

**Step 3: Pricing & Location**
- Asking price
- City
- Locality/Area

**Step 4: Photos & Video**
- Upload photos (minimum 3)
- Add video (optional)
- Reorder photos
- Set primary photo

**Step 5: Additional Details**
- Variant
- Number of owners
- Insurance validity
- PUC status
- Condition
- Description
- Additional specs

**Preview & Publish**
- Review listing
- Edit any step
- Publish

**V1 Note**: All data stored locally, no real upload

---

### 🏢 Showroom System

**V1 Implementation**: Completely separate UI from individual sellers, fully implemented

**Showroom Profile:**
```
┌──────────────────────────────┐
│ ABC Motors                   │
│ ✓ Verified Showroom          │
│                              │
│ 📍 Pune, Maharashtra         │
│ 📞 +91 98765 43210           │
│ 🌐 www.abcmotors.com         │
│                              │
│ 126 Vehicles in Inventory    │
│                              │
│ [View All Vehicles]          │
│                              │
│ Categories                   │
│ Cars (80) | Bikes (30) |     │
│ SUVs (16)                    │
└──────────────────────────────┘

Available Vehicles
──────────────────
[Vehicle Card Grid]
```

**Showroom Management:**
```
My Showroom
 │
 ├── Showroom Profile
 │    ├── Business information
 │    ├── Contact details
 │    ├── Logo/banner
 │    └── Description
 │
 ├── Inventory
 │    ├── All Vehicles
 │    ├── Available
 │    ├── Reserved
 │    └── Sold
 │
 ├── Leads
 │    ├── Enquiries received
 │    ├── Offers received
 │    └── Messages
 │
 └── Verification
      ├── Business verification status
      └── Documents
```

**Showroom Features:**
- Multi-vehicle inventory
- Public showroom page
- Centralized lead management
- Verification badge
- Inventory analytics (post-V1)

---

### ✅ Verification System

**V1 Implementation**: Full verification UI flows, manual entry

**Verification Layers:**

#### 1. Account Verification
- 📱 Mobile OTP (UI + local validation)
- 📧 Email OTP (UI + local validation)

#### 2. Vehicle Verification
- 🚗 RC (Registration Certificate) verification
  - Manual entry in V1
  - Photo upload (local storage)
  - Data extraction (manual in V1, OCR later)

#### 3. Showroom Verification
- 🏢 Business verification
  - Business name
  - Business registration documents
  - GST number (optional)
  - Address proof

**Verification Badges:**
- ✓ Mobile Verified
- ✓ Email Verified
- ✓ RC Verified
- ✓ Verified Showroom

**Trust Indicators:**
Clearly display verification status on:
- User profiles
- Seller cards
- Showroom profiles
- Listing detail pages

---

### 📍 Location & Geography

**V1 Implementation**: City-level, GPS auto-detection

**Location Hierarchy** (Architecture):
```
India
 │
 ├── State
 │    ├── District
 │    │    ├── City
 │    │    │    ├── Locality/Area
 │    │    │    └── Pincode
```

**V1 Scope:**
- City-level location
- GPS auto-detection on app open
- Manual city selection
- City dropdown/search

**Location in Listings:**
- City (required)
- Locality/Area (optional)

**Search/Filter:**
- Filter by selected city
- "Near me" option (GPS-based)

---

### 📊 Vehicle Information

**Philosophy**: Collect vehicle information without dependency on unofficial APIs.

**V1 Data Sources:**
- Manual entry by seller
- User-provided photos/documents

**Architecture for Future:**
```
Vahan Seva Backend
       ↓
Official Vehicle Information Service
   (when available)
       ↓
Normalize Data
       ↓
Vehicle Profile
```

**Important**: Android app should NEVER directly depend on Parivahan or unofficial vehicle APIs.

---

### 🔔 Notifications

**V1 Scope:**

**Transactional:**
- New message received
- Offer received (seller)
- Offer responded (buyer)
- Vehicle verification status
- Listing status change
- Account/security alerts

**Future (Post-V1):**
- Price drop on saved vehicle
- New matching vehicle available
- Similar vehicle listed
- Showroom updates

**User Control:**
- Notification preferences (future)
- Do Not Disturb settings (future)

---

### 🚨 Reports & Safety

**V1 Scope**: Basic reporting UI

**Users Can Report:**

**Listings:**
- Fake listing
- Scam
- Incorrect information
- Duplicate listing
- Already sold
- Offensive content

**Users/Sellers:**
- Suspicious behavior
- Scam attempt
- Fake seller
- Harassment

**Showrooms:**
- Fake showroom
- Fraudulent business
- Misleading information

**Messages:**
- Spam
- Harassment
- Scam attempt
- Inappropriate content

**Report Flow:**
```
User clicks "Report"
    ↓
Selects reason
    ↓
Adds details (optional)
    ↓
Submits report
    ↓
[Admin reviews - Post-V1]
```

---

## What We Deliberately DON'T Build in V1

This is equally important as what we DO build:

❌ In-app payment system
❌ Escrow services
❌ Financing marketplace
❌ Insurance marketplace
❌ Vehicle inspection marketplace
❌ Complex dealer subscriptions
❌ Complex negotiation engine (simple offers only)
❌ AI recommendations
❌ 360° vehicle tours
❌ Ownership transfer automation
❌ Vehicle maintenance management
❌ Backend APIs (mock data only)
❌ Admin moderation panel (architecture ready)

**Rationale**: Focus on core marketplace discovery and connection. Additional services come later once we have traction.

---

## Technical Requirements (V1)

### Platform
- **Android Only** (V1)
- **Minimum SDK**: 24 (Android 7.0)
- **Target SDK**: 36 (Android 15)

### Technology Stack
- **Language**: Kotlin
- **UI**: Jetpack Compose
- **Architecture**: MVVM + Clean Architecture
- **DI**: Hilt
- **Database**: Room (SQLite)
- **Storage**: Local storage, SharedPreferences
- **Image Loading**: Coil
- **Navigation**: Jetpack Navigation Compose

### Data Strategy (V1)
- **Mock data** in JSON files
- **Repository pattern** (ready for API replacement)
- **Room database** for local caching
- **No backend APIs** in V1

### Authentication (V1)
- **Mock authentication**
- **Local session management**
- **UI fully implemented**

### Video (V1)
- YouTube links
- Local video selection (UI)
- Mock upload

### Location (V1)
- GPS permission
- City auto-detection
- Manual city selection
- Mock city database

---

## Production Considerations (Future)

While V1 is mock-based, architecture must support:

### Security
- Authentication & authorization
- Encryption (data at rest, in transit)
- Secure API access
- Rate limiting
- Abuse prevention
- Audit logs

### Reliability
- Backups
- Disaster recovery
- Monitoring
- Error tracking
- Health checks

### Performance
- Image optimization
- CDN for media
- Caching strategies
- Pagination
- Search indexing
- Lazy loading

### Scalability
- Stateless backend (future)
- Horizontal scaling
- Queue-based background work
- Search infrastructure
- Object storage for media

### Operations
- Logging
- Metrics
- Alerts
- CI/CD pipeline
- Database migrations
- Feature flags

---

## Analytics Foundation (V1)

Track key user actions (local analytics, ready for backend):

**User Journey:**
```
App opened
 ↓
City selected
 ↓
Category browsed
 ↓
Search performed
 ↓
Filters applied
 ↓
Listing viewed
 ↓
Saved
 ↓
Compared
 ↓
Chat started
 ↓
Call clicked
 ↓
WhatsApp clicked
 ↓
Offer made
```

**Key Metrics:**
- Which cities have demand?
- Which vehicles are most searched?
- Which categories are popular?
- Where do users drop off?
- Which filters are most used?
- Listing views vs. contacts ratio

**V1 Implementation**: Local logging, architecture ready for analytics service.

---

## Future Monetization (Post-V1)

Architecture should allow for:

### Dealer Subscriptions
- Featured showroom placement
- Enhanced visibility
- Analytics dashboard

### Featured Listings
- Promoted vehicles
- Top of search results
- Homepage placement

### Lead-Based Services
- Pay per qualified lead
- Verification as a service

### Transaction Services
- Commission on platform-facilitated transactions

### Ecosystem Services
```
Marketplace
    ↓
Inspection
    ↓
Financing
    ↓
Insurance
    ↓
Ownership Transfer
    ↓
Service & Maintenance
```

---

## Success Criteria (V1)

### Functional Success
- ✅ Guest users can browse and search
- ✅ Users can register and authenticate (mock)
- ✅ Sellers can create and manage listings
- ✅ Showrooms can manage inventory
- ✅ Users can save, compare, and share vehicles
- ✅ Users can chat and make offers
- ✅ Verification flows work end-to-end
- ✅ All navigation flows are complete
- ✅ App is stable and performant

### Technical Success
- ✅ MVVM architecture properly implemented
- ✅ Repository pattern separates UI from data
- ✅ Mock data easily replaceable with APIs
- ✅ Room database working correctly
- ✅ Navigation structure clean and maintainable
- ✅ Reusable components library built
- ✅ Code follows Android best practices

### Documentation Success
- ✅ All architecture decisions documented
- ✅ Complete API contracts defined (for future)
- ✅ Component library documented
- ✅ New developer/AI agent can understand project
- ✅ Implementation plan is clear

---

## Project Continuity

### AI Agent Independence
This project must work with ANY coding AI agent (Claude Code, Cursor, Gemini, Copilot, etc.).

**Requirements:**
- All context in version-controlled documentation
- No dependency on single AI agent
- New agent can read repo and continue

### Documentation Standards
All critical knowledge in repository:
- PRODUCT_REQUIREMENTS.md (this file)
- ANDROID_ARCHITECTURE.md
- DESIGN_SYSTEM.md
- DATA_MODEL.md
- NAVIGATION_MAP.md
- COMPONENT_INVENTORY.md
- MOCK_DATA_SPECIFICATION.md
- IMPLEMENTATION_PLAN.md
- AI_RULES.md
- DEVELOPMENT_STATUS.md

---

## Appendix: One-Page Summary

**Vahan Seva Auto-Mall**: Digital vehicle marketplace for India

**Problem**: Buyers waste time visiting dealers without knowing available inventory

**Solution**: Discover, compare, save, and connect before visiting

**Users**: Buyers, Individual Sellers, Showrooms

**Core Modules**:
- Vehicle Marketplace
- Vehicle Listing
- Showroom & Inventory
- Search & Discovery
- Comparison
- Favorites & Sharing
- Chat & Offers
- Verification
- Notifications

**Vehicle Types**: Cars, bikes, scooters, trucks, buses, tractors, commercial vehicles, more

**Geography**: India, Tier-3 first, nationwide scalability

**V1 Model**: Free marketplace

**Future**: Subscriptions, promoted listings, financing, insurance, inspection, services

**Core Promise**: "Know what's available before you visit."

---

**END OF PRODUCT REQUIREMENTS DOCUMENT**

*Last Updated: August 10, 2026*  
*Version: 1.0.0*  
*Status: V1 Scope Finalized*
