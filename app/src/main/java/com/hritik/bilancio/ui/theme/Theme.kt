package com.hritik.bilancio.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = backgroundDark,
    primaryVariant = white,
    secondary = blue,
    background = backgroundDark,
    surface = colorPrimaryDark,
    onError = red,
    onSecondary = green,
    onSurface = hintColor,


)

private val LightColorPalette = lightColors(
    primary = background,
    primaryVariant = black,
    secondary = blue,
    background= background,
    surface = colorPrimary,
    onError = red,
    onSecondary = green,
    onSurface = hintColor


    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun BilancioTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
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