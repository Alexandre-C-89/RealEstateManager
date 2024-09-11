package com.example.realestatemanager.designsystem.bar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.BottomAppBarDefaults.ContentPadding
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.realestatemanager.designsystem.DarkBlue
import com.example.realestatemanager.designsystem.White
import com.example.realestatemanager.designsystem.button.AddIconButton
import com.example.realestatemanager.designsystem.button.HomeIconButton
import com.example.realestatemanager.designsystem.button.MapIconButton

@Composable
fun BottomBar(
    onMapClick: (() -> Unit)? = null,
    onHomeClick: (() -> Unit)? = null
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        containerColor = DarkBlue,
        contentColor = contentColorFor(White),
        tonalElevation = 10.dp,
        actions = {
            Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.Center) {
                onMapClick?.let {
                    MapIconButton { onMapClick() }
                }
            }
            Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.Center) {
                onHomeClick?.let {
                    HomeIconButton { onHomeClick() }
                }
            }
        }
    )
}

@Preview
@Composable
fun BottomBarPreview() {
    BottomBar()
}