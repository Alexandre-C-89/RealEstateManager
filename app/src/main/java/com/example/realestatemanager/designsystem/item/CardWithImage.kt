package com.example.realestatemanager.designsystem.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.realestatemanager.R
import com.example.realestatemanager.ui.theme.RealEstateManagerTheme

@Composable
fun CardWithImage(
    type: String,
    location: String,
    price: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.width(50.dp),
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Image"
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = type)
            Text(text = location)
            Text(text = price)
        }
    }
}

@Preview
@Composable
fun CardWithImagePreview() {
    RealEstateManagerTheme {
        CardWithImage(
            type = "Duplex",
            location = "Brooklyn",
            price = "€41,480,000",
        )
    }
}