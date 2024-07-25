package com.example.realestatemanager.designsystem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.realestatemanager.designsystem.ui.text.Text

@Composable
fun ListCheckbox(

) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Text.Default(text = "Near by :")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            /*checkboxItem(checked = , onCheckedChange = , enabled = )
            checkboxItem(checked = , onCheckedChange = , enabled = )
            checkboxItem(checked = , onCheckedChange = , enabled = )
            checkboxItem(checked = , onCheckedChange = , enabled = )
            checkboxItem(checked = , onCheckedChange = , enabled = )*/
        }
    }
}

@Preview
@Composable
fun ListCheckboxPreview() {
    RealEstateManagerTheme {
        ListCheckbox(

        )
    }
}