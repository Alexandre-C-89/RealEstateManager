package com.example.realestatemanager.feature.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.realestatemanager.designsystem.AppScaffold
import com.example.realestatemanager.designsystem.bar.BottomBar
import com.example.realestatemanager.designsystem.bar.TopBar
import com.example.realestatemanager.designsystem.card.CardWithInfo


@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    onEditClick: () -> Unit,
    onPropertyClick: (Int) -> Unit,
    onHomeClick: () -> Unit,
    onMapClick: () -> Unit,
    onSearchClick: () -> Unit,
    onLendClick: () -> Unit
) {
    val uiState by viewModel.properties.collectAsStateWithLifecycle()
    Log.d("HOMEROUTE", "${uiState.currentList}")
    HomeScreen(
        uiState = uiState,
        onMenuClick = {},
        onEditClick = onEditClick,
        onSearchClick = onSearchClick,
        onPropertyClick = onPropertyClick,
        onHomeClick = onHomeClick,
        onMapClick = onMapClick,
        onLendClick = onLendClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    uiState: UiState,
    onMenuClick: () -> Unit,
    onHomeClick: () -> Unit,
    onMapClick: () -> Unit,
    onEditClick: () -> Unit,
    onSearchClick: () -> Unit,
    onLendClick: () -> Unit,
    onPropertyClick: (Int) -> Unit
) {

    AppScaffold(
        topBar = {
            TopBar(
                onNavigationClick = onMenuClick,
                onLendClick = onLendClick,
                onEditClick = onEditClick,
                onSearchClick = onSearchClick,
            )
        },
        bottomBar = {
            BottomBar(
                onMapClick =  onMapClick,
                onHomeClick = onHomeClick,
                onEditClick = onEditClick
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(uiState.currentList) { property ->
                property.type?.let {
                    property.address?.let { it1 ->
                        CardWithInfo(
                            onClick = {
                                property.id?.let { onPropertyClick(it) }
                            },
                            type = it,
                            location = it1,
                            price = "€ ${property.price.toString()}",
                            imageUri = property.image
                        )
                    }
                }
            }
        }
    }
}