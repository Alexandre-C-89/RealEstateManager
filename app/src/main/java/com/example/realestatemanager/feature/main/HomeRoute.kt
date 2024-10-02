package com.example.realestatemanager.feature.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
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
import com.example.realestatemanager.designsystem.card.CardWithInfo
import com.example.realestatemanager.designsystem.fonts
import com.example.realestatemanager.designsystem.map.GoogleMapItem
import com.example.realestatemanager.designsystem.ui.Default
import com.example.realestatemanager.designsystem.ui.Spacer
import com.example.realestatemanager.designsystem.ui.Spacings
import com.example.realestatemanager.feature.details.model.LocationState
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
    onLendClick: () -> Unit
) {
    val uiState by viewModel.properties.collectAsStateWithLifecycle()
    val locationState by viewModel.locationState.collectAsStateWithLifecycle()

    var latitude: Double? = uiState.selectedProperty?.latitude
    var longitude: Double? = uiState.selectedProperty?.longitude

    LaunchedEffect(uiState.selectedProperty) {
        uiState.selectedProperty?.let { property ->
            if (property.latitude == null || property.longitude == null) {
                property.address?.let { address ->
                    viewModel.fetchCoordinates(address)
                }
            }
        }
    }

    when (val state = locationState) {
        is LocationState.Loading -> {
            CircularProgressIndicator()
        }

        is LocationState.Success -> {
            val result = state.geocodingResult
            if (result != null) {
                latitude = result.results.firstOrNull()?.geometry?.location?.lat ?: latitude
            }
            if (result != null) {
                longitude = result.results.firstOrNull()?.geometry?.location?.lng ?: longitude
            }
        }

        is LocationState.Error -> {
            val message = state.message
        }

        is LocationState.MultipleSuccess -> TODO()
    }

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
        onPropertyDetailsClick = {},
        latitude = latitude ?: 0.0,
        longitude = longitude ?: 0.0
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
    onPropertyDetailsClick: (PropertyEntity) -> Unit,
    latitude: Double,
    longitude: Double
) {
    /*val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(latitude, longitude), 15f)
    }
    LaunchedEffect(latitude, longitude) {
        cameraPositionState.move(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(latitude, longitude),
                15f
            )
        )
    }*/
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
            val navigator = rememberListDetailPaneScaffoldNavigator<Any>()
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
                                                ListDetailPaneScaffoldRole.Detail,
                                                property
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
                    selectedProperty?.let { property ->
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState())
                        ) {
                            Spacer.Vertical.Large()
                            Text(
                                text = "Description",
                                style = TextStyle(
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = fonts,
                                    textAlign = TextAlign.Start,
                                    color = Blue
                                )
                            )
                            Spacer.Vertical.Large()
                            Text(
                                text = property.description ?: "",
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Normal,
                                    fontFamily = fonts,
                                    textAlign = TextAlign.Start,
                                    color = Black
                                )
                            )
                            Spacer.Vertical.ExtraLarge()
                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                CardWithIcon(
                                    icon = R.drawable.ic_surface,
                                    title = "Surface",
                                    info = "${property.surface} + m²"
                                )
                                Spacer.Horizontal.Large()
                                CardWithIcon(
                                    icon = R.drawable.ic_bed,
                                    title = "Room",
                                    info = property.room.toString()
                                )
                            }
                            Spacer.Vertical.Large()
                            CardImage(
                                imageUri = property.image ?: ""
                            )
                            Spacer.Vertical.Large()
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                CardWithIcon(
                                    icon = R.drawable.ic_location,
                                    title = "Location",
                                    info = property.address.toString()
                                )
                                GoogleMapItem(
                                    modifier = Modifier
                                        .size(300.dp)
                                        .clip(CircleShape),
                                    cameraPosition = CameraPositionState(
                                        CameraPosition.fromLatLngZoom(LatLng(latitude, longitude), 15f)
                                    ),
                                    state = MarkerState(position = LatLng(latitude, longitude))
                                )
                            }
                        }
                    }
                }
            )
        }
    }
}