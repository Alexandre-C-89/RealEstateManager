package com.example.realestatemanager.designsystem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.realestatemanager.R
import com.example.realestatemanager.designsystem.card.CardWithIcon
import com.example.realestatemanager.designsystem.ui.Spacer

@Composable
fun ListWithColumn(

){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(230.dp)
            .padding(8.dp)
    ) {
        Column {
            CardWithIcon(icon = R.drawable.ic_surface, title = "Surface", number = "200.0m²")
            Spacer.Vertical.Small()
            CardWithIcon(icon = R.drawable.ic_home, title = "Number of rooms", number = "8")
            Spacer.Vertical.Small()
            CardWithIcon(icon = R.drawable.ic_surface, title = "Number of bathrooms", number = "2")
            Spacer.Vertical.Small()
            CardWithIcon(icon = R.drawable.ic_bed, title = "Number of bedrooms", number = "4")
        }
        Spacer.Horizontal.Default()
        Column {
            CardWithIcon(icon = R.drawable.ic_location, title = "Surface", number = "200.0m²")
            Spacer.Vertical.Small()
            CardWithIcon(icon = R.drawable.ic_surface, title = "Surface", number = "200.0m²")
            Spacer.Vertical.Small()
            CardWithIcon(icon = R.drawable.ic_surface, title = "Surface", number = "200.0m²")
        }
    }
}

@Preview
@Composable
fun ListWithColumnPreview(){
    RealEstateManagerTheme {
        ListWithColumn()
    }
}