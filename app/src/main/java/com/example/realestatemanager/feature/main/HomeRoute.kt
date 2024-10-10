package com.example.realestatemanager.feature.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.realestatemanager.data.local.property.PropertyEntity
import com.example.realestatemanager.designsystem.AppScaffold
import com.example.realestatemanager.designsystem.bar.BottomBar
import com.example.realestatemanager.designsystem.bar.TopBar
import com.example.realestatemanager.designsystem.card.CardWithInfo
import com.example.realestatemanager.feature.details.DetailsRoute
import com.google.android.gms.maps.MapsInitializer


@Composable
fun HomeRoute(
    isExpandedScreen: Boolean,
    viewModel: HomeViewModel = hiltViewModel(),
    onEditClick: () -> Unit,
    onPropertyClick: (Int) -> Unit,
    onHomeClick: () -> Unit,
    onMapClick: () -> Unit,
    onSearchClick: () -> Unit,
    onLendClick: () -> Unit,
    onModifyClick: (Int) -> Unit,
) {
    val uiState by viewModel.properties.collectAsStateWithLifecycle()

    HomeScreen(
        isExpandedScreen = isExpandedScreen,
        uiState = uiState,
        onEditClick = onEditClick,
        onSearchClick = onSearchClick,
        onPropertyClick = onPropertyClick,
        onHomeClick = onHomeClick,
        onMapClick = onMapClick,
        onLendClick = onLendClick,
        onModifyClick = onModifyClick
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3AdaptiveApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    isExpandedScreen: Boolean,
    uiState: UiState,
    onHomeClick: () -> Unit,
    onMapClick: () -> Unit,
    onEditClick: () -> Unit,
    onSearchClick: () -> Unit,
    onLendClick: () -> Unit,
    onPropertyClick: (Int) -> Unit,
    onModifyClick: (Int) -> Unit,
) {
    val navigator = rememberListDetailPaneScaffoldNavigator<Any>()
    AppScaffold(
        topBar = {
            if (!isExpandedScreen) {
                TopBar(
                    onLendClick = onLendClick,
                    onEditClick = onEditClick,
                    onSearchClick = onSearchClick,
                )
            }
        },
        bottomBar = {
            if (!isExpandedScreen) {
                BottomBar(
                    onMapClick = onMapClick,
                    onHomeClick = onHomeClick,
                    onEditClick = onEditClick
                )
            }
        }
    ) {
        if (!isExpandedScreen) {
            LazyColumn(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(uiState.currentList) { property ->
                    property.type?.let {
                        property.address?.let { it1 ->
                            CardWithInfo(
                                onClick = {
                                    onPropertyClick(property.id)
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
        } else {
            val context = LocalContext.current
            MapsInitializer.initialize(context)
            NavigableListDetailPaneScaffold(
                navigator = navigator,
                listPane = {
                    LazyColumn(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(uiState.currentList) { property ->
                            property.type?.let {
                                property.address?.let { it1 ->
                                    CardWithInfo(
                                        onClick = {
                                            navigator.navigateTo(
                                                pane = ListDetailPaneScaffoldRole.Detail,
                                                content = property
                                            )
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
                },
                detailPane = {
                    val selectedProperty = navigator.currentDestination?.content as? PropertyEntity
                    if (selectedProperty != null) {
                        DetailsRoute(
                            isExpandedScreen = isExpandedScreen,
                            onBackClick = {
                                navigator.navigateBack()
                            },
                            onModifyClick = { onModifyClick(selectedProperty.id) },
                            propertyId = selectedProperty.id
                        )
                    } else {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "Oh no something went wrong !")
                        }
                    }
                }
            )
        }
    }
}