package com.vahanseva.auto_mall.presentation.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val VahanSevaShapes = Shapes(
    // Extra Small - 4dp (elements < 32px)
    extraSmall = RoundedCornerShape(4.dp),

    // Small - 8dp (small elements)
    small = RoundedCornerShape(8.dp),

    // Medium - 16dp (standard cards)
    medium = RoundedCornerShape(16.dp),

    // Large - 24dp (large cards, main containers)
    large = RoundedCornerShape(24.dp),

    // Extra Large - 28dp (modal sheets)
    extraLarge = RoundedCornerShape(28.dp)
)
