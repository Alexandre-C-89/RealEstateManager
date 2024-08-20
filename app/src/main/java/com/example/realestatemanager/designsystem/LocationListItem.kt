package com.example.realestatemanager.designsystem

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.realestatemanager.R
import com.example.realestatemanager.designsystem.ui.Spacer
import com.example.realestatemanager.designsystem.ui.text.Text

@Composable
fun LocationListItem(
    building: String,
    city: String,
    address: String,
    country: String
) {
    Row(
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_location),
            contentDescription = "icon location"
        )
        Column {
            //Text.Medium(text = "Location")
            Text(
                text = "Location",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = fonts,
                    textAlign = TextAlign.Start,
                    color = Blue
                )
            )
            Spacer.Vertical.Small()
            //Text.Medium(text = building)
            Text(
                text = building,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = fonts,
                    textAlign = TextAlign.Start,
                    color = Blue
                )
            )
            Spacer.Vertical.Small()
            //Text.Medium(text = city)
            Text(
                text = city,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = fonts,
                    textAlign = TextAlign.Start,
                    color = Blue
                )
            )
            Spacer.Vertical.Small()
            //Text.Medium(text = address)
            Text(
                text = address,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = fonts,
                    textAlign = TextAlign.Start,
                    color = Blue
                )
            )
            Spacer.Vertical.Small()
            //Text.Medium(text = country)
            Text(
                text = country,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = fonts,
                    textAlign = TextAlign.Start,
                    color = Blue
                )
            )
            Spacer.Vertical.Small()
        }
    }
}