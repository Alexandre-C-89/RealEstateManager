package com.example.realestatemanager.designsystem.card

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.realestatemanager.designsystem.White
import com.example.realestatemanager.designsystem.fonts

@Composable
fun CardApp(
    text: String,
    color: Color
){
    androidx.compose.material3.Card(
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(
            contentColor = White,
            containerColor = color
        )
    ){
        Text(
            modifier = Modifier.padding(8.dp),
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = fonts,
            textAlign = TextAlign.Start,
            color = White
        )
    }
}

@Preview
@Composable
fun CardAppPreview(){
    CardApp(
        text = "31-11-2024",
        color = Red
    )
}