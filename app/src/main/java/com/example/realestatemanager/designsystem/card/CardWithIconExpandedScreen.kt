package com.example.realestatemanager.designsystem.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.realestatemanager.R
import com.example.realestatemanager.designsystem.ui.Spacer

@Composable
fun CardWithIconExpandedScreen(
    icon: Int,
    title: String,
    info: String
) {
    Row(
        modifier = Modifier
            .widthIn(min = 150.dp, max = 300.dp)
            .heightIn(min = 50.dp, max = 100.dp)
    ){
        Image(
            modifier = Modifier.width(40.dp),
            painter = painterResource(icon),
            contentDescription = "Image"
        )
        Spacer.Horizontal.Default()
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = info,
                fontSize = 14.sp
            )
        }
    }
}