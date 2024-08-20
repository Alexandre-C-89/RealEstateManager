package com.example.realestatemanager.designsystem.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.realestatemanager.R
import com.example.realestatemanager.designsystem.Black
import com.example.realestatemanager.designsystem.LightBlue
import com.example.realestatemanager.designsystem.LightGrey
import com.example.realestatemanager.designsystem.RealEstateManagerTheme
import com.example.realestatemanager.designsystem.White
import com.example.realestatemanager.designsystem.fonts
import com.example.realestatemanager.designsystem.ui.Spacer
import com.example.realestatemanager.designsystem.ui.text.Text

@Composable
fun CardWithImage(
    onClick: () -> Unit,
    type: String,
    location: String,
    price: String
) {
    Card(
        modifier = Modifier
            .height(120.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(6.dp),
        colors = CardDefaults.cardColors(LightBlue)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.width(100.dp),
                painter = painterResource(id = R.drawable.tv_lounge),
                contentDescription = "Image",
                contentScale = ContentScale.Crop
            )
            Spacer.Horizontal.Default()
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text.Large(text = type)
                Spacer.Vertical.Small()
                Text(
                    text = location,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Light,
                        fontFamily = fonts,
                        textAlign = TextAlign.Start,
                        color = Black
                    )
                )
                Spacer.Vertical.Small()
                Text(
                    text = price,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = fonts,
                        textAlign = TextAlign.Start,
                        color = Black
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun CardWithImagePreview() {
    RealEstateManagerTheme {
        CardWithImage(
            onClick = {},
            type = "Duplex",
            location = "Brooklyn",
            price = "€41,480,000"
        )
    }
}