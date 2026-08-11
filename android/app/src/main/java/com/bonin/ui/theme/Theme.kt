package com.bonin.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = BoninPrimary,
    onPrimary = Color.White,
    primaryContainer = BoninAccent,
    onPrimaryContainer = BoninText,
    background = BoninBackground,
    onBackground = BoninText,
    surface = BoninSurface,
    onSurface = BoninText,
    surfaceVariant = BoninAccent,
    onSurfaceVariant = BoninTextSecondary,
    outline = BoninOutline
)

private val DarkColorScheme = darkColorScheme(
    primary = BoninDarkPrimary,
    onPrimary = Color.Black,
    primaryContainer = BoninDarkAccent,
    onPrimaryContainer = BoninDarkText,
    background = BoninDarkBackground,
    onBackground = BoninDarkText,
    surface = BoninDarkSurface,
    onSurface = BoninDarkText,
    surfaceVariant = BoninDarkAccent,
    onSurfaceVariant = BoninDarkTextSecondary,
    outline = BoninDarkAccent
)

@Composable
fun BoninTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) {
            DarkColorScheme
        } else {
            LightColorScheme
        },
        typography = Typography,
        content = content
    )
}