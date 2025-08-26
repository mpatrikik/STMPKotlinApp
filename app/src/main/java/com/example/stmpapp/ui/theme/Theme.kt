package com.example.stmpapp.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = MyPrimary,
    secondary = MySecondary,
    background = MyBackground,
    surface = MySurface,
    onPrimary = MyOnPrimary,
    onSecondary = MyOnSecondary,
    onBackground = MyOnBackground,
    onSurface = MyOnSurface,
)

private val DarkColors = darkColorScheme(
    primary = MyPrimary,
    secondary = MySecondary,
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    onPrimary = MyOnPrimary,
    onSecondary = MyOnSecondary,
    onBackground = Color(0xFFFFFFFF),
    onSurface = Color(0xFFFFFFFF),
)

@Composable
fun STMPAppTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        content = content
    )
}
