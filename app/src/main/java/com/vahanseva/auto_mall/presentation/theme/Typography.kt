package com.vahanseva.auto_mall.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.vahanseva.auto_mall.R

// Manrope font family (will need to add font files)
// For now, using system default with proper weights
val ManropeFamily = FontFamily.Default

// Custom typography definitions
val VahanSevaTypography = Typography(
    // Display Large - 32sp, Weight 800
    displayLarge = TextStyle(
        fontFamily = ManropeFamily,
        fontSize = 32.sp,
        fontWeight = FontWeight.ExtraBold,  // 800
        lineHeight = 40.sp,
        letterSpacing = (-0.02).sp
    ),

    // Headline Large - 24sp, Weight 700
    headlineLarge = TextStyle(
        fontFamily = ManropeFamily,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,  // 700
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),

    // Headline Medium - 20sp, Weight 700
    headlineMedium = TextStyle(
        fontFamily = ManropeFamily,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,  // 700
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),

    // Headline Small - 18sp, Weight 600
    headlineSmall = TextStyle(
        fontFamily = ManropeFamily,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,  // 600
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),

    // Body Large - 16sp, Weight 400
    bodyLarge = TextStyle(
        fontFamily = ManropeFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,  // 400
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),

    // Body Medium - 14sp, Weight 400
    bodyMedium = TextStyle(
        fontFamily = ManropeFamily,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,  // 400
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),

    // Body Small - 12sp, Weight 400
    bodySmall = TextStyle(
        fontFamily = ManropeFamily,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,  // 400
        lineHeight = 16.sp,
        letterSpacing = 0.sp
    ),

    // Label Large - 14sp, Weight 600
    labelLarge = TextStyle(
        fontFamily = ManropeFamily,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,  // 600
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),

    // Label Medium - 12sp, Weight 600
    labelMedium = TextStyle(
        fontFamily = ManropeFamily,
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,  // 600
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),

    // Label Small - 11sp, Weight 600
    labelSmall = TextStyle(
        fontFamily = ManropeFamily,
        fontSize = 11.sp,
        fontWeight = FontWeight.SemiBold,  // 600
        lineHeight = 14.sp,
        letterSpacing = 0.5.sp
    )
)

// Custom text style for price display (22sp, Weight 800)
val PriceDisplayStyle = TextStyle(
    fontFamily = ManropeFamily,
    fontSize = 22.sp,
    fontWeight = FontWeight.ExtraBold,  // 800
    lineHeight = 28.sp,
    letterSpacing = (-0.01).sp
)
