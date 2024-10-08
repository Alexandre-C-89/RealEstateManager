package com.example.realestatemanager.designsystem.ui.text

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.realestatemanager.designsystem.DarkBlue
import com.example.realestatemanager.designsystem.fonts

object Title {

    @Composable
    fun Big(text: String) = Text(
        modifier = Modifier.fillMaxWidth(),
        text = text,
        style = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = fonts,
            textAlign = TextAlign.Start,
            color = DarkBlue
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
            color = DarkBlue
        )
    )

    @Composable
    fun Default(text: String) = Text(
        modifier = Modifier.fillMaxWidth(),
        text = text,
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = fonts,
            textAlign = TextAlign.Start,
            color = DarkBlue
        )
    )

}