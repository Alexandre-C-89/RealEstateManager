package com.example.realestatemanager.designsystem.bar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Surface
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
import com.example.realestatemanager.designsystem.button.EditIconButton
import com.example.realestatemanager.designsystem.button.HomeIconButton
import com.example.realestatemanager.designsystem.button.MapIconButton

@Composable
fun BottomBar(
    onMapClick: (() -> Unit)? = null,
    onHomeClick: (() -> Unit)? = null,
    onEditClick: (() -> Unit)? = null
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Surface {
        BottomAppBar(
            modifier = Modifier.height(100.dp),
            containerColor = DarkBlue,
            contentColor = White,
            actions = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    onMapClick?.let {
                        MapIconButton { onMapClick() }
                    }
                    onHomeClick?.let {
                        HomeIconButton { onHomeClick() }
                    }
                    onEditClick?.let {
                        EditIconButton { onEditClick() }
                    }
                }
            }
        )
    }
}

@Preview
@Composable
fun BottomBarPreview() {
    BottomBar()
}