package com.example.realestatemanager.feature.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.realestatemanager.R
import com.example.realestatemanager.data.local.property.PropertyEntity
import com.example.realestatemanager.designsystem.AppScaffold
import com.example.realestatemanager.designsystem.Black
import com.example.realestatemanager.designsystem.Blue
import com.example.realestatemanager.designsystem.bar.BottomBar
import com.example.realestatemanager.designsystem.bar.TopBar
import com.example.realestatemanager.designsystem.card.CardImage
import com.example.realestatemanager.designsystem.card.CardWithIcon
import com.example.realestatemanager.designsystem.card.CardWithIconExpandedScreen
import com.example.realestatemanager.designsystem.card.CardWithInfo
import com.example.realestatemanager.designsystem.fonts
import com.example.realestatemanager.designsystem.map.GoogleMapItem
import com.example.realestatemanager.designsystem.ui.Default
import com.example.realestatemanager.designsystem.ui.Spacer
import com.example.realestatemanager.designsystem.ui.Spacings
import com.example.realestatemanager.feature.details.DetailsRoute
import com.example.realestatemanager.feature.details.model.LocationState
import com.example.realestatemanager.feature.modify.ModifyRoute
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState


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
        onMenuClick = {},
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
    onMenuClick: () -> Unit,
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
                    onNavigationClick = onMenuClick,
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
        } else {
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