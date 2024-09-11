package com.example.realestatemanager.feature.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.realestatemanager.designsystem.AppScaffold
import com.example.realestatemanager.designsystem.bar.BottomBar
import com.example.realestatemanager.designsystem.bar.TopBar
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker

@Composable
fun MapRoute(
    onBackClick: () -> Unit,
    onMapClick: () -> Unit,
    onHomeClick: () -> Unit,
    onEditClick: () -> Unit
) {
    MapScreen(
        onBackClick = onBackClick,
        onMapClick = onMapClick,
        onHomeClick = onHomeClick,
        onEditClick = onEditClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
    onBackClick: () -> Unit,
    onMapClick: () -> Unit,
    onHomeClick: () -> Unit,
    onEditClick: () -> Unit
) {
    AppScaffold(
        topBar = {
            TopBar(
                onBackClick = onBackClick
            )
        },
        bottomBar = {
            Surface {
                BottomBar(
                    onMapClick =  onMapClick,
                    onHomeClick = onHomeClick,
                    onEditClick = onEditClick
                )
            }
        }
    ) {
        GoogleMap(
            modifier = Modifier.fillMaxSize()
        ) {
            Marker(

            )
        }
    }
}