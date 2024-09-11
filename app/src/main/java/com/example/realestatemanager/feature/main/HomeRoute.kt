package com.example.realestatemanager.feature.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.realestatemanager.designsystem.AppScaffold
import com.example.realestatemanager.designsystem.bar.TopBar
import com.example.realestatemanager.designsystem.card.CardWithImage
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.realestatemanager.designsystem.bar.BottomBar
import com.example.realestatemanager.designsystem.button.MapIconButton


@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    onEditClick: () -> Unit,
    onPropertyClick: (Int) -> Unit,
    onHomeClick: () -> Unit,
    onMapClick: () -> Unit,
) {
    HomeScreen(
        viewModel = viewModel,
        onMenuClick = {},
        onAddClick = {},
        onEditClick = onEditClick,
        onSearchClick = {},
        onPropertyClick = onPropertyClick,
        onHomeClick = onHomeClick,
        onMapClick = onMapClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onMenuClick: () -> Unit,
    onHomeClick: () -> Unit,
    onMapClick: () -> Unit,
    onAddClick: () -> Unit,
    onEditClick: () -> Unit,
    onSearchClick: () -> Unit,
    onPropertyClick: (Int) -> Unit
) {
    val uiState = viewModel.uiState

    AppScaffold(
        topBar = {
            TopBar(
                onNavigationClick = onMenuClick,
                onAddClick = onAddClick,
                onEditClick = onEditClick,
                onSearchClick = onSearchClick,
            )
        },
        bottomBar = {
            BottomBar(
                onHomeClick = onHomeClick,
                onMapClick = onMapClick
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(uiState.value.currentList) { property ->
                property.type?.let {
                    property.address?.let { it1 ->
                        CardWithImage(
                            onClick = {
                                Log.d("HOMESCREEN", "${property.id}")
                                property.id?.let { onPropertyClick(it) }
                            },
                            type = it,
                            location = it1,
                            price = property.price.toString()
                        )
                    }
                }
            }
        }
    }
}