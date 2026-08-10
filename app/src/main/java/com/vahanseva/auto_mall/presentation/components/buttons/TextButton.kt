package com.vahanseva.auto_mall.presentation.components.buttons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vahanseva.auto_mall.presentation.theme.Spacing

/**
 * Text button (tertiary button style)
 * - Transparent background
 * - Primary color text
 * - No border
 * - 48dp height for consistency
 * - Loading state with spinner
 */
@Composable
fun VahanSevaTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false
) {
    TextButton(
        onClick = onClick,
        modifier = modifier.height(Spacing.buttonHeight),
        enabled = enabled && !loading,
        shape = RoundedCornerShape(percent = 50),
        contentPadding = PaddingValues(
            horizontal = Spacing.gutter,
            vertical = Spacing.sm
        )
    ) {
        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier.size(20.dp),
                color = MaterialTheme.colorScheme.primary,
                strokeWidth = 2.dp
            )
        } else {
            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
