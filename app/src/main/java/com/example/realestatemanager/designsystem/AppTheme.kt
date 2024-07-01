package com.example.realestatemanager.designsystem

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

@Composable
fun AppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorPalette = if (isDarkTheme) darkColorPalette else lightColorPalette

    val customTextSelectionColors = TextSelectionColors(
        handleColor = Blue,
        backgroundColor = Blue
    )

    CompositionLocalProvider(
        LocalColorPalette provides colorPalette,
        LocalTextSelectionColors provides customTextSelectionColors,
        content = content
    )
}

object Theme {

    val colorPalette: ColorPalette
        @Composable
        @ReadOnlyComposable
        get() = LocalColorPalette.current
}

val LocalColorPalette = staticCompositionLocalOf { lightColorPalette }