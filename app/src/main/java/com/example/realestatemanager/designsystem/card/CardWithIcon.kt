package com.example.realestatemanager.designsystem.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.realestatemanager.R
import com.example.realestatemanager.designsystem.RealEstateManagerTheme

@Composable
fun CardWithIcon(
    icon: Int? = null,
    title: String,
    info: String
    ){
    Row{
        if (icon != null){
            Image(
                modifier = Modifier.width(20.dp),
                painter = painterResource(id = icon),
                contentDescription = "Image"
            )
        } else {
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = title)
                Text(text = info)
            }
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = title)
            Text(text = info)
        }
    }
}

@Preview
@Composable
fun CardWithIconPreview(){
    RealEstateManagerTheme {
        CardWithIcon(
            icon = R.drawable.ic_home,
            title = "Number of rooms",
            info = "8"
        )
    }
}