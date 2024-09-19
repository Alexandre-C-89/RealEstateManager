package com.example.realestatemanager.designsystem.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.realestatemanager.R
import com.example.realestatemanager.designsystem.ui.Spacer
import com.example.realestatemanager.designsystem.ui.text.Text

@Composable
fun ErrorCard(
    message: String
){
    Card(
        modifier = Modifier
            .width(250.dp)
            .height(150.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Image(
                painter = painterResource(id = R.drawable.ic_error),
                contentDescription = "image for error",
            )
            Spacer.Vertical.Tiny()
            Text(
                text = message,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun ErrorCardPreview(){
    ErrorCard(
        message = "Oh no something went wrong !"
    )
}