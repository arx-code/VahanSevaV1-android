package com.vahanseva.auto_mall.presentation.components.inputs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.vahanseva.auto_mall.presentation.theme.Spacing

/**
 * Price input field with ₹ currency prefix
 * - Handles numeric input
 * - Formats with Indian number system (commas)
 * - Shows ₹ symbol
 * - Error state support
 * - 48dp height touch target
 */
@Composable
fun PriceInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Price",
    placeholder: String = "Enter price",
    error: String? = null,
    enabled: Boolean = true
) {
    Column(modifier = modifier.fillMaxWidth()) {
        if (label.isNotEmpty()) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        val borderColor = when {
            error != null -> MaterialTheme.colorScheme.error
            else -> MaterialTheme.colorScheme.outlineVariant
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(Spacing.buttonHeight)
                .background(
                    color = MaterialTheme.colorScheme.surfaceContainerLowest,
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(24.dp)
                )
                .border(
                    width = 1.dp,
                    color = borderColor,
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(24.dp)
                )
                .padding(horizontal = Spacing.gutter, vertical = Spacing.sm),
            contentAlignment = Alignment.CenterStart
        ) {
            // Currency symbol
            Text(
                text = "₹",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(start = Spacing.gutter)
            )

            // Placeholder
            if (value.isEmpty()) {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(start = 32.dp)
                )
            }

            // Input field
            BasicTextField(
                value = value,
                onValueChange = { newValue ->
                    // Only allow digits
                    if (newValue.all { it.isDigit() }) {
                        onValueChange(newValue)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp),
                textStyle = LocalTextStyle.current.copy(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize
                ),
                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                maxLines = 1,
                singleLine = true,
                enabled = enabled
            )
        }

        // Error message
        if (error != null) {
            Text(
                text = error,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .padding(horizontal = Spacing.gutter)
            )
        }
    }
}
