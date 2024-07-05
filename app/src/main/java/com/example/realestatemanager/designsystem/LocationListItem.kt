package com.example.realestatemanager.designsystem

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
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
            Text.Medium(text = "Location")
            Spacer.Vertical.Small()
            Text.Medium(text = building)
            Spacer.Vertical.Small()
            Text.Medium(text = city)
            Spacer.Vertical.Small()
            Text.Medium(text = address)
            Spacer.Vertical.Small()
            Text.Medium(text = country)
            Spacer.Vertical.Small()
        }
    }
}