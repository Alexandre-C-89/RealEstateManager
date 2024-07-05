package com.example.realestatemanager.designsystem

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import java.io.File.separator

data class ColorPalette(
    val spinner: Color,
    val separator: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val textDisabled: Color,
    val surfacePrimary: Color,
    val surfaceSecondary: Color,
    val iconPrimary: Color,
    val iconSecondary: Color,
    val iconDisabled: Color
)

val darkColorPalette = ColorPalette(
    spinner = White,
    separator = Grey,
    textPrimary = DarkGrey,
    textSecondary = Grey,
    textDisabled = LightGrey,
    surfacePrimary = White,
    surfaceSecondary = DarkGrey,
    iconPrimary = White,
    iconSecondary = LightGrey,
    iconDisabled = LightGrey,
)

val lightColorPalette = ColorPalette(
    spinner = Blue,
    separator = Grey,
    textPrimary = White,
    textSecondary = LightGrey,
    textDisabled = LightGrey,
    surfacePrimary = White,
    surfaceSecondary = LightGrey,
    iconPrimary = DarkGrey,
    iconSecondary = Grey,
    iconDisabled = LightGrey,
)