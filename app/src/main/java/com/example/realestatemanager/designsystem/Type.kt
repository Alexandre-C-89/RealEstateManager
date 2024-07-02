package com.example.realestatemanager.designsystem

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.realestatemanager.R

val fonts = FontFamily(
    Font(R.font.roboto_regular),
    Font(R.font.roboto_condensed_regular, style = FontStyle.Italic),

    Font(R.font.roboto_light, weight = FontWeight.Light),
    Font(R.font.roboto_condensed_light, weight = FontWeight.Light, style = FontStyle.Italic),

    Font(R.font.roboto_medium, weight = FontWeight.Medium),
    Font(R.font.roboto_medium, weight = FontWeight.Medium, style = FontStyle.Italic),

    Font(R.font.roboto_bold, weight = FontWeight.Bold),
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 72.sp
    ),
    displayMedium = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 56.sp
    ),
    displaySmall = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 48.sp,
    ),
    headlineLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    ),
    headlineMedium = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),

    titleLarge = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    titleMedium = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    titleSmall = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    bodySmall = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp
    ),
    labelLarge = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    labelMedium = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    ),
    labelSmall = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp
    ),
)