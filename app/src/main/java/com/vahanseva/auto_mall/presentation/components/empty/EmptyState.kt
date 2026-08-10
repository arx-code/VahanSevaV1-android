package com.vahanseva.auto_mall.presentation.components.empty

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vahanseva.auto_mall.presentation.components.buttons.PrimaryButton
import com.vahanseva.auto_mall.presentation.theme.Spacing

/**
 * Empty state display
 * - Icon at top
 * - Title (headline medium)
 * - Message (body medium)
 * - Optional action button
 * - Centered layout
 */
@Composable
fun EmptyState(
    icon: ImageVector,
    title: String,
    message: String,
    modifier: Modifier = Modifier,
    actionLabel: String? = null,
    onActionClick: (() -> Unit)? = null
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(Spacing.lg),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(64.dp),
            tint = MaterialTheme.colorScheme.outlineVariant
        )

        Spacer(modifier = Modifier.height(Spacing.lg))

        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(Spacing.md))

        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )

        if (actionLabel != null && onActionClick != null) {
            Spacer(modifier = Modifier.height(Spacing.lg))

            PrimaryButton(
                text = actionLabel,
                onClick = onActionClick,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

/**
 * No results state (specialized empty state for search)
 */
@Composable
fun NoResultsState(
    query: String,
    modifier: Modifier = Modifier,
    onClearSearch: (() -> Unit)? = null
) {
    EmptyState(
        icon = Icons.Default.Search,
        title = "No results found",
        message = "We couldn't find any vehicles matching \"$query\"",
        modifier = modifier,
        actionLabel = if (onClearSearch != null) "Clear search" else null,
        onActionClick = onClearSearch
    )
}

/**
 * Error state display
 */
@Composable
fun ErrorState(
    error: String,
    modifier: Modifier = Modifier,
    onRetry: (() -> Unit)? = null
) {
    EmptyState(
        icon = Icons.Default.Search,
        title = "Something went wrong",
        message = error,
        modifier = modifier,
        actionLabel = if (onRetry != null) "Try again" else null,
        onActionClick = onRetry
    )
}
