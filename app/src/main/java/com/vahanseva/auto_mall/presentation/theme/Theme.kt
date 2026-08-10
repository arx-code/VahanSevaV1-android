package com.vahanseva.auto_mall.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    // Primary
    primary = Primary,
    onPrimary = OnPrimary,
    primaryContainer = PrimaryContainer,
    onPrimaryContainer = OnPrimaryContainer,
    inversePrimary = InversePrimary,

    // Secondary
    secondary = Secondary,
    onSecondary = OnSecondary,
    secondaryContainer = SecondaryContainer,
    onSecondaryContainer = OnSecondaryContainer,

    // Tertiary
    tertiary = Tertiary,
    onTertiary = OnTertiary,
    tertiaryContainer = TertiaryContainer,
    onTertiaryContainer = OnTertiaryContainer,

    // Error
    error = Error,
    onError = OnError,
    errorContainer = ErrorContainer,
    onErrorContainer = OnErrorContainer,

    // Surface
    surface = Surface,
    onSurface = OnSurface,
    surfaceVariant = SurfaceContainer,
    onSurfaceVariant = OnSurfaceVariant,

    // Background
    background = Background,
    onBackground = OnBackground,

    // Inverse
    inverseSurface = InverseSurface,
    inverseOnSurface = InverseOnSurface,

    // Outline
    outline = Outline,
    outlineVariant = OutlineVariant,

    // Surface Tint
    surfaceTint = SurfaceTint
)

// Dark theme (not used in V1, but prepared for future)
private val DarkColorScheme = darkColorScheme(
    primary = InversePrimary,
    onPrimary = Primary,
    primaryContainer = PrimaryContainer,
    onPrimaryContainer = OnPrimaryContainer,

    secondary = Secondary,
    onSecondary = OnSecondary,
    secondaryContainer = SecondaryContainer,
    onSecondaryContainer = OnSecondaryContainer,

    tertiary = Tertiary,
    onTertiary = OnTertiary,
    tertiaryContainer = TertiaryContainer,
    onTertiaryContainer = OnTertiaryContainer,

    error = Error,
    onError = OnError,
    errorContainer = ErrorContainer,
    onErrorContainer = OnErrorContainer,

    surface = InverseSurface,
    onSurface = InverseOnSurface,
    surfaceVariant = SurfaceContainerHigh,
    onSurfaceVariant = OnSurfaceVariant,

    background = InverseSurface,
    onBackground = InverseOnSurface,

    inverseSurface = Surface,
    inverseOnSurface = OnSurface,

    outline = Outline,
    outlineVariant = OutlineVariant,

    surfaceTint = SurfaceTint
)

@Composable
fun VahanSevaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // V1: Light theme only, darkTheme parameter prepared for future
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme  // Future dark theme support
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = VahanSevaTypography,
        shapes = VahanSevaShapes,
        content = content
    )
}
