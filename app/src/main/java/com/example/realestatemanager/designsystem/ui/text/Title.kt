package com.example.realestatemanager.designsystem.ui.text

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.realestatemanager.designsystem.Grey
import com.example.realestatemanager.designsystem.Theme
import com.example.realestatemanager.designsystem.fonts

object Text {
    @Composable
    fun Big(text: String) = Text(
        modifier = Modifier.fillMaxWidth(),
        text = text,
        style = TextStyle(
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = fonts,
            textAlign = TextAlign.Start,
            color = Theme.colorPalette.textPrimary
        )
    )

    @Composable
    fun Large(text: String) = Text(
        modifier = Modifier.fillMaxWidth(),
        text = text,
        style = TextStyle(
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = fonts,
            textAlign = TextAlign.Start,
            color = Theme.colorPalette.textPrimary
        )
    )

    @Composable
    fun Medium(text: String) = Text(
        modifier = Modifier.fillMaxWidth(),
        text = text,
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = fonts,
            textAlign = TextAlign.Start,
            color = Theme.colorPalette.textPrimary
        )
    )

    @Composable
    fun Default(text: String) = Text(
        modifier = Modifier.fillMaxWidth(),
        text = text,
        style = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = fonts,
            textAlign = TextAlign.Start,
            color = Theme.colorPalette.textPrimary
        )
    )

    @Composable
    fun Small(text: String) = Text(
        modifier = Modifier.fillMaxWidth(),
        text = text,
        style = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = fonts,
            textAlign = TextAlign.Start,
            color = Grey
        )
    )

}