package com.example.realestatemanager.feature.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.realestatemanager.designsystem.AppScaffold
import com.example.realestatemanager.designsystem.bar.BottomBar
import com.example.realestatemanager.designsystem.bar.TopBar
import com.example.realestatemanager.designsystem.card.CardWithImage


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
    HomeScreen(
        viewModel = viewModel,
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
    viewModel: HomeViewModel,
    onMenuClick: () -> Unit,
    onHomeClick: () -> Unit,
    onMapClick: () -> Unit,
    onEditClick: () -> Unit,
    onSearchClick: () -> Unit,
    onLendClick: () -> Unit,
    onPropertyClick: (Int) -> Unit
) {
    val uiState = viewModel.uiState

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
            items(uiState.value.currentList) { property ->
                property.type?.let {
                    property.address?.let { it1 ->
                        CardWithImage(
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