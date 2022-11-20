package com.aktepetugce.onedayofacat.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    background = Gray200,
    surface = Salmon700,
    onSurface = White,
    primary = DarkGray,
    onPrimary = White,
    secondary = Red200,
    onSecondary = White
)

private val LightColorPalette = lightColors(
    background = Gray,
    surface = Salmon200,
    onSurface = Black,
    primary = Salmon500,
    onPrimary = Black,
    secondary = Red500,
    onSecondary = White
)

@Composable
fun OneDayOfACatTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}