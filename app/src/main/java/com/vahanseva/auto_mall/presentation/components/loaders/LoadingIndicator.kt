package com.vahanseva.auto_mall.presentation.components.loaders

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Loading indicator following Vahan Seva design system
 * - Primary color spinner
 * - Centered in container
 * - 48dp size by default
 */
@Composable
fun LoadingIndicator(
    modifier: Modifier = Modifier,
    size: Float = 48f
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(size.dp),
            color = MaterialTheme.colorScheme.primary,
            strokeWidth = 3.dp
        )
    }
}
