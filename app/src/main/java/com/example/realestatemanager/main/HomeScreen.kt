package com.example.realestatemanager.main

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.realestatemanager.R
import com.example.realestatemanager.database.Database
import com.example.realestatemanager.database.Repository
import com.example.realestatemanager.designsystem.AppScaffold
import com.example.realestatemanager.designsystem.bar.TopBar
import com.example.realestatemanager.designsystem.card.CardWithImage
import com.example.realestatemanager.designsystem.divider.Divider
import com.example.realestatemanager.designsystem.ui.Small
import com.example.realestatemanager.designsystem.ui.Spacer
import com.example.realestatemanager.designsystem.ui.Spacings
import com.example.realestatemanager.designsystem.ui.Tiny
import androidx.compose.foundation.lazy.items

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