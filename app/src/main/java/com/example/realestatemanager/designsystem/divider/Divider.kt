package com.example.realestatemanager.designsystem.divider

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.realestatemanager.designsystem.Grey
import com.example.realestatemanager.designsystem.LightGrey

@Composable
fun Divider(){
    androidx.compose.material3.Divider(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        thickness = 1.dp,
        color = LightGrey
    )
}