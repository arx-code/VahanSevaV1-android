package com.vahanseva.auto_mall.presentation.theme

import androidx.compose.ui.unit.dp

/**
 * Spacing system based on 4dp grid
 * Following Material Design 3 spacing guidelines
 */
object Spacing {
    val xs = 4.dp       // Tight internal spacing
    val sm = 8.dp       // Small gaps
    val gutter = 12.dp  // Default internal padding
    val md = 16.dp      // Standard gap (MOST COMMON)
    val lg = 24.dp      // Large gap between sections
    val xl = 32.dp      // Extra large gap
    val xxl = 48.dp     // Extra extra large gap

    // Specific use cases
    val edgeMargin = 16.dp     // Screen edge margins
    val cardPadding = 16.dp    // Internal card padding
    val sectionGap = 24.dp     // Gap between major sections
    val buttonHeight = 48.dp   // Minimum touch target
    val iconSize = 24.dp       // Standard icon size
    val iconSizeLarge = 32.dp  // Large icon size
    val iconSizeSmall = 16.dp  // Small icon size

    // Bottom navigation
    val bottomNavHeight = 64.dp

    // Top app bar
    val topAppBarHeight = 56.dp
}
