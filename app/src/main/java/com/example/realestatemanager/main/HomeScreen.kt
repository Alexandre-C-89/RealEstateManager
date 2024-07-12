package com.example.realestatemanager.main

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.realestatemanager.designsystem.AppScaffold
import com.example.realestatemanager.designsystem.bar.TopBar
import com.example.realestatemanager.designsystem.card.CardWithImage

@Composable
fun HomeRoute(
    viewModel: HomeViewModel
) {
    HomeScreen(
        viewModel = viewModel,
        onMenuClick = {},
        onAddClick = {},
        onEditClick = {},
        onSearchClick = {},
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onMenuClick: () -> Unit,
    onAddClick: () -> Unit,
    onEditClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    val properties by viewModel.properties.collectAsState(initial = emptyList())

    AppScaffold(
        topBar = {
            TopBar(
                onNavigationClick = onMenuClick,
                onAddClick = onAddClick,
                onEditClick = onEditClick,
                onSearchClick = onSearchClick,
            )
        }
    ) {
        LazyColumn {
            items(properties) { property ->
                CardWithImage(
                    type = property.type,
                    location = property.address,
                    price = property.price.toString()
                )
            }
        }
    }
}