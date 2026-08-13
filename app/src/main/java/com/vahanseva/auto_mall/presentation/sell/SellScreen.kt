package com.vahanseva.auto_mall.presentation.sell

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.vahanseva.auto_mall.presentation.theme.Spacing

/**
 * Sell screen - Create and manage listings
 * V1: Basic scaffold, ready for implementation
 */
@Composable
fun SellScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(Spacing.md),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Sell Screen\nReady for implementation",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
