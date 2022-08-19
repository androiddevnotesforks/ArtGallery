package com.nucu.dynamiclistcompose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = PrimaryDark,
    primaryVariant = TertiaryDark,
    secondary = SecondaryDark,
    surface = BackgroundDark,
    onSurface = BackgroundDark,
    onBackground = BackgroundDark,
    background = BackgroundDark,
    onPrimary = SkeletonDark
)

private val LightColorPalette = lightColors(
    primary = PrimaryLight,
    primaryVariant = TertiaryLight,
    secondary = SecondaryLight,
    background = BackgroundLight,
    surface = BackgroundLight,
    onSurface = BackgroundLight,
    onBackground = BackgroundLight,
    onPrimary = SkeletonLight
)

@Composable
fun DynamicListComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
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