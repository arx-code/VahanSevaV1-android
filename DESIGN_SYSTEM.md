# Design System - Vahan Seva Auto-Mall

**Version**: 1.0.0  
**Date**: August 10, 2026  
**Design Philosophy**: Corporate Modern with Utility Focus  
**Base Framework**: Material 3 (adapted)  
**Typography**: Manrope (exclusive)

---

## 🎨 Brand & Visual Identity

### Design Principles
- **Trust**: Deep, sophisticated color palette for high-value transactions
- **Efficiency**: Clear data hierarchy, optimized for technical specifications
- **Accessibility**: High contrast, readable at all sizes, inclusive design
- **Modern**: Clean-tech aesthetic, rounded shapes, soft shadows

### Emotional Tone
Professional yet approachable marketplace for buying/selling vehicles with confidence.

---

## 🎭 Color System

### Primary Colors

**Deep Teal (Primary Brand Color)**
- Hex: `#006875`
- RGB: (0, 104, 117)
- Usage: Primary buttons, active states, brand headers, prices
- Rationale: More sophisticated than standard blue, conveys trust for high-value transactions

**Teal Surface Tint** (Secondary)
- Hex: `#f3fbfc`
- RGB: (243, 251, 252)
- Usage: Main background color (instead of pure white)
- Benefit: Reduces eye strain, feels integrated

### Full Color Palette

```kotlin
// In Color.kt
val VahanSevaColors = colorScheme {
    // Surface System (Teal-Grey Base)
    surface = Color(0xfff7fafa)
    surfaceDim = Color(0xffd8dadb)
    surfaceBright = Color(0xfff7fafa)
    surfaceContainerLowest = Color(0xffffffff)      // Cards, Inputs
    surfaceContainerLow = Color(0xfff1f4f5)
    surfaceContainer = Color(0xffeceeef)
    surfaceContainerHigh = Color(0xffe6e9e9)
    surfaceContainerHighest = Color(0xffe0e3e4)
    onSurface = Color(0xff181c1d)
    onSurfaceVariant = Color(0xff3f484a)
    
    // Inverse (for dark overlays)
    inverseSurface = Color(0xff2d3132)
    inverseOnSurface = Color(0xff eef1f2)
    
    // Outline (borders, dividers)
    outline = Color(0xff6f797b)
    outlineVariant = Color(0xffbec8cb)
    
    // Primary (Teal)
    primary = Color(0xff004e58)
    onPrimary = Color(0xffffffff)
    primaryContainer = Color(0xff006875)
    onPrimaryContainer = Color(0xff97e4f3)
    inversePrimary = Color(0xff85d2e1)
    
    // Secondary (Electric Blue)
    secondary = Color(0xff3349dc)
    onSecondary = Color(0xffffffff)
    secondaryContainer = Color(0xff4f65f6)
    onSecondaryContainer = Color(0xfff ffbff)
    
    // Tertiary (Purple)
    tertiary = Color(0xff7100a5)
    onTertiary = Color(0xffffffff)
    tertiaryContainer = Color(0xff8e27c5)
    onTertiaryContainer = Color(0xfff0ccff)
    
    // Error (Red)
    error = Color(0xffba1a1a)
    onError = Color(0xffffffff)
    errorContainer = Color(0xffffdad6)
    onErrorContainer = Color(0xff93000a)
    
    // Background
    background = Color(0xfff7fafa)
    onBackground = Color(0xff181c1d)
    
    // Semantic Colors
    priceGreen = Color(0xff006875)      // Same as primary (consistency)
    favouriteIcon = Color(0xff6b7a7d)
    surfaceTeaL Tint = Color(0xfff3fbfc)
    onSurfaceMuted = Color(0xff3b494c)
}
```

### Color Usage Guidelines

| Element | Color | Usage |
|---------|-------|-------|
| **Primary Buttons** | Primary (#006875) | CTAs, main actions |
| **Active Tabs/Chips** | PrimaryContainer (#006875) | Selected states |
| **Inactive Tabs/Chips** | SurfaceContainerLowest + Outline | Default state |
| **Links/Active State** | Secondary (#3349dc) | Highlights, active items |
| **Cards & Inputs** | SurfaceContainerLowest (#ffffff) | Primary interactive elements |
| **Backgrounds** | Surface (#f3fbfc) | Page backgrounds |
| **Text - Primary** | OnSurface (#181c1d) | Body text, headlines |
| **Text - Secondary** | OnSurfaceVariant (#3f484a) | Meta info, captions |
| **Dividers** | OutlineVariant (#bec8cb) | Separators |
| **Borders** | Outline (#6f797b) | Input borders, active states |
| **Price Display** | Primary (#006875) | Price text |
| **Error Messages** | Error (#ba1a1a) | Error states |
| **Success** | Primary (#006875) | Success feedback |

---

## 📝 Typography System

### Font Family
**Manrope** (exclusive, all weights: 400, 600, 700, 800)
- Semi-geometric construction
- High legibility, ideal for data-heavy listings
- Modern, professional appearance

### Type Scales

```kotlin
// In Typography.kt
val VahanSevaTypography = typography {
    // Display Large - 32px, Weight 800
    displayLarge = TextStyle(
        fontFamily = manropeFamily,
        fontSize = 32.sp,
        fontWeight = FontWeight.ExtraBold,  // 800
        lineHeight = 40.sp,
        letterSpacing = -0.02.em
    )
    
    // Headline Large - 24px, Weight 700
    headlineLarge = TextStyle(
        fontFamily = manropeFamily,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,      // 700
        lineHeight = 32.sp,
        letterSpacing = 0.em
    )
    
    // Headline Medium - 20px, Weight 700
    headlineMedium = TextStyle(
        fontFamily = manropeFamily,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,      // 700
        lineHeight = 28.sp,
        letterSpacing = 0.em
    )
    
    // Headline Small - 18px, Weight 600
    headlineSmall = TextStyle(
        fontFamily = manropeFamily,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,  // 600
        lineHeight = 24.sp,
        letterSpacing = 0.em
    )
    
    // Price Display - 22px, Weight 800 (custom)
    // Custom: Can't use standard MaterialTheme, define separately
    
    // Body Large - 16px, Weight 400
    bodyLarge = TextStyle(
        fontFamily = manropeFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,    // 400
        lineHeight = 24.sp,
        letterSpacing = 0.em
    )
    
    // Body Medium - 14px, Weight 400
    bodyMedium = TextStyle(
        fontFamily = manropeFamily,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,    // 400
        lineHeight = 20.sp,
        letterSpacing = 0.em
    )
    
    // Label Large - 14px, Weight 600
    labelLarge = TextStyle(
        fontFamily = manropeFamily,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,  // 600
        lineHeight = 20.sp,
        letterSpacing = 0.1.em
    )
    
    // Label Medium - 12px, Weight 600
    labelMedium = TextStyle(
        fontFamily = manropeFamily,
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,  // 600
        lineHeight = 16.sp,
        letterSpacing = 0.5.em
    )
    
    // Custom: Price Display - 22px, Weight 800
    priceDisplay = TextStyle(
        fontFamily = manropeFamily,
        fontSize = 22.sp,
        fontWeight = FontWeight.ExtraBold,  // 800
        lineHeight = 28.sp,
        letterSpacing = -0.01.em
    )
}
```

### Typography Usage

| Style | Size | Weight | Usage |
|-------|------|--------|-------|
| **Display Large** | 32px | 800 | Brand headers, page titles (rare) |
| **Headline Large** | 24px | 700 | Section headers, major titles |
| **Headline Medium** | 20px | 700 | Subsection headers |
| **Headline Small** | 18px | 600 | Card titles, screen headers |
| **Price Display** | 22px | 800 | Vehicle prices (primary focal point) |
| **Body Large** | 16px | 400 | Main body text |
| **Body Medium** | 14px | 400 | Secondary body text, descriptions |
| **Label Large** | 14px | 600 | Button text, chip labels |
| **Label Medium** | 12px | 600 | Tags, badges, meta information |

---

## 📏 Spacing System

All spacing uses a **4px base unit** (Material 3 standard).

```kotlin
// In Spacing.kt
object Spacing {
    val xs = 4.dp       // Tight internal spacing
    val sm = 8.dp       // Small gaps
    val gutter = 12.dp  // Default internal padding
    val md = 16.dp      // Standard gap (MOST COMMON)
    val lg = 24.dp      // Large gap between sections
    val xl = 32.dp      // Extra large gap
    val edge_margin = 16.dp  // Screen edge margins
}
```

### Spacing Usage

| Spacing | Usage |
|---------|-------|
| **4px (xs)** | Icon-to-text padding, tight internal elements |
| **8px (sm)** | Small gaps within cards, between closely related items |
| **12px (gutter)** | Standard internal padding for cards/containers |
| **16px (md)** | **PRIMARY** - gaps between sections, margins, standard padding |
| **24px (lg)** | Gaps between major sections, section separators |
| **32px (xl)** | Large gaps, page-level spacing |
| **16px (edge_margin)** | Left/right margins on all screens |

### Layout Grid

- **Screen Margins**: 16px left/right (edge_margin)
- **Column Gap**: 12px (gutter)
- **Row Gap**: 16px (md, most common)
- **Card Padding**: 16px (md) internal, 12px (gutter) image padding
- **Section Gap**: 24px (lg)

---

## 🔘 Component Styles

### Buttons

#### Primary Button
- **Background**: Primary (#006875)
- **Text Color**: OnPrimary (white #ffffff)
- **Height**: 48px (minimum touch target)
- **Padding**: 12px (gutter) horizontal
- **Border Radius**: full (9999px - pill shape)
- **Text Style**: Label Large (14px, 600)
- **Shadow**: sm (subtle)

**States**:
- **Default**: Full opacity
- **Hover**: -10% brightness
- **Pressed**: -20% brightness
- **Disabled**: 38% opacity

#### Secondary Button
- **Background**: SurfaceContainerLowest (#ffffff)
- **Border**: 1px Outline (#6f797b)
- **Text Color**: Primary (#006875)
- **Otherwise**: Same as Primary Button

#### Icon Button
- **Shape**: Circular
- **Size**: 48x48dp (minimum touch target)
- **Background**: Transparent or soft surface-variant
- **Icon Size**: 24x24dp
- **Icon Color**: OnSurfaceVariant (#3f484a)

### Chips & Filters

#### Active Chip
- **Background**: PrimaryContainer (#006875)
- **Text Color**: OnPrimaryContainer (#97e4f3)
- **Padding**: 8px (sm) vertical, 12px (gutter) horizontal
- **Border Radius**: full (pill)
- **Height**: 32px

#### Inactive Chip
- **Background**: SurfaceContainerLowest (#ffffff)
- **Border**: 1px OutlineVariant (#bec8cb)
- **Text Color**: OnSurface (#181c1d)
- **Otherwise**: Same as Active

### Input Fields

#### Search Bar / Text Input
- **Background**: SurfaceContainerLowest (#ffffff)
- **Border**: 1px OutlineVariant (#bec8cb) default, Primary (#006875) on focus
- **Border Radius**: xl (24px)
- **Height**: 48px
- **Padding**: 12px (gutter) horizontal, leading icon included
- **Text Style**: Body Large (16px)
- **Shadow**: sm (subtle)
- **Leading Icon**: 24x24dp, color OnSurfaceVariant

### Cards

#### Vehicle Card
- **Background**: SurfaceContainerLowest (#ffffff)
- **Border Radius**: xl (24px)
- **Shadow**: sm (subtle ambient shadow)
- **Overflow**: Hidden (image fills)
- **Image**: Fixed aspect ratio 1:1, top-aligned
- **Content Padding**: 12px (gutter)
- **Price Text Style**: Price Display (22px, 800)
- **Price Color**: Primary (#006875)
- **Meta Text Style**: Body Medium (14px)
- **Meta Color**: OnSurfaceVariant (#3f484a)

#### Card Layout
```
┌─────────────────────┐
│   Image (1:1)       │
├─────────────────────┤
│ Brand Model         │  ← Headline Small (18px, 600)
│                     │
│ Year • Fuel • Trans │  ← Body Medium (14px), dots separator
│ 52,000 km           │  ← Body Medium (14px)
│                     │
│ ₹45,00,000          │  ← Price Display (22px, 800)
│ Location    ❤️      │  ← Body Medium + Icon
└─────────────────────┘
```

### Bottom Navigation

- **Background**: Surface (#f7fafa)
- **Height**: 64dp
- **Border**: 1px top OutlineVariant (#bec8cb)
- **Inactive Icon Color**: OnSurfaceVariant (#3f484a)
- **Active Icon Color**: Secondary (#3349dc)
- **Label Style**: Label Medium (12px, 600)
- **Label Color**: Active = Secondary, Inactive = OnSurfaceVariant
- **Scale Animation**: Active item scales to 90% for tactile feel (optional)

### Top App Bar

- **Background**: Surface (#f7fafa)
- **Height**: 56dp
- **Blur Effect**: backdrop-blur-md with 80% opacity (maintains context while scrolling)
- **Shadow**: sm (subtle)
- **Title Style**: Headline Small (18px, 600)
- **Leading/Trailing Icons**: 24x24dp

---

## 🎯 Elevation & Depth

### Shadow System

**Shadow Small (sm)**
- Blur: 4px
- Color: OnSurface (#181c1d)
- Opacity: 8%
- Usage: Cards, inputs, subtle elevation

**Shadow Medium (md)**
- Blur: 8px
- Color: OnSurface (#181c1d)
- Opacity: 12%
- Usage: Floating elements

**Shadow Large (lg)**
- Blur: 16px
- Color: OnSurface (#181c1d)
- Opacity: 15%
- Usage: FABs, modals, floating nav

### Elevation Levels

```
Level 0 (Base)
└─ Surface (#f3fbfc) - App background

Level 1 (Lifted)
├─ Cards (shadow-sm)
├─ Inputs (shadow-sm)
└─ Chips (no shadow)

Level 2 (Floating)
├─ Bottom Navigation (shadow-sm + border)
├─ Top App Bar (shadow-sm)
└─ Modals (shadow-lg)

Level 3 (Highest)
├─ FABs (shadow-lg)
├─ Dropdowns (shadow-lg)
└─ Tooltips (shadow-lg)
```

---

## 🔲 Radius System

```kotlin
// In Shapes.kt
val shapes = Shapes(
    small = RoundedCornerShape(0.25.rem),    // 4px (elements < 32px)
    medium = RoundedCornerShape(0.5.rem),    // 8px (default)
    large = RoundedCornerShape(1.rem),       // 16px (cards with content)
    extraLarge = RoundedCornerShape(1.5.rem) // 24px (large cards)
)
```

### Radius Usage

| Radius | Usage |
|--------|-------|
| **4px (sm)** | Small chips, badges |
| **8px (default)** | Standard elements |
| **16px (lg)** | Internal card elements, smaller containers |
| **24px (xl)** | Vehicle cards, search inputs, main containers |
| **Full (9999px)** | Buttons, pills, FABs |

---

## ✨ Motion & Animation

### Durations
- **Fast**: 150ms - Quick feedback (button press, toggle)
- **Standard**: 300ms - Normal transitions (fade in/out, slide)
- **Slow**: 500ms - Important transitions (modal open, navigation)

### Easing
- **Standard**: Cubic Bezier (0.4, 0.0, 0.2, 1.0) - Material standard
- **Decelerate**: Cubic Bezier (0.0, 0.0, 0.2, 1.0) - Entering
- **Accelerate**: Cubic Bezier (0.4, 0.0, 1.0, 1.0) - Exiting

---

## 🌙 Dark Mode (Future)

Current design is Light theme only (V1). Dark mode can use:
- **Background**: inverseSurface (#2d3132)
- **OnBackground**: inverseOnSurface (#eef1f2)
- **Surface colors**: Inverted tonal values
- **Primary**: Inverse Primary (#85d2e1)

---

## ♿ Accessibility

### Color Contrast
- **Primary Text** (OnSurface on Surface): 7:1 ratio ✅
- **Secondary Text** (OnSurfaceVariant on Surface): 4.5:1 ratio ✅
- **Button Text** (OnPrimary on Primary): 9:1 ratio ✅

### Touch Targets
- **Minimum**: 48x48dp for all interactive elements
- **Button Height**: 48dp
- **Icon Button**: 48x48dp
- **Chip Height**: 32dp minimum

### Typography
- **Minimum**: 12sp for body text
- **Line Height**: 1.5x font size minimum
- **Letter Spacing**: Used strategically (not excessive)

### Focus States
- All focusable elements have visible focus ring (2dp border, secondary color)
- Focus color: Secondary (#3349dc)

---

## 📱 Responsive Design

### Mobile-First Approach
- **Base**: Optimized for 375px (standard mobile)
- **Tablet**: 768px and above (single column to multi-column)
- **Desktop**: 1280px+ (optimal for web view sharing)

### Grid
- **Mobile**: Full width minus 16px margins on each side
- **Tablet**: 2-column grid, max width 768px
- **Desktop**: 3-column grid, max width 1200px

---

**END OF DESIGN SYSTEM**

*Last Updated: August 10, 2026*  
*Version: 1.0.0*  
*Status: Ready for Implementation*
