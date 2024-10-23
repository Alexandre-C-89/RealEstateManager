package com.example.realestatemanager.designsystem.card

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.realestatemanager.designsystem.Blue
import com.example.realestatemanager.designsystem.Grey

@Composable
fun CardImage(
    imageUri: String? = null,
) {
    Card(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp),
    ) {
        AsyncImage(
            contentScale = ContentScale.Crop,
            model = imageUri,
            contentDescription = "Image"
        )
    }
}