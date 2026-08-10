package com.vahanseva.auto_mall.presentation.components.buttons

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.vahanseva.auto_mall.presentation.theme.Spacing

/**
 * Icon button with proper touch target size
 * Used for navigation, actions, etc.
 * - 48x48dp size (minimum touch target)
 * - 24x24dp icon
 * - Transparent background
 */
@Composable
fun VahanSevaIconButton(
    icon: ImageVector,
    contentDescription: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    IconButton(
        onClick = onClick,
        modifier = modifier.size(Spacing.buttonHeight),
        enabled = enabled
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            modifier = Modifier.size(Spacing.iconSize),
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

/**
 * Back button specifically for navigation
 * Pre-configured with back arrow icon
 */
@Composable
fun BackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    VahanSevaIconButton(
        icon = Icons.AutoMirrored.Filled.ArrowBack,
        contentDescription = "Navigate back",
        onClick = onClick,
        modifier = modifier
    )
}
