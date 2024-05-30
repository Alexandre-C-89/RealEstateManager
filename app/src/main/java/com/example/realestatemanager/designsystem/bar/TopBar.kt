package com.example.realestatemanager.designsystem.bar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    shadowElevation: Dp = 0.dp,
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    Surface(shadowElevation = shadowElevation) {
        TopAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            title = { Text(text = "RealEstateManager") },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Blue),
            navigationIcon = { },
            actions = {},
            scrollBehavior = scrollBehavior
        )
    }
}