package com.example.realestatemanager.feature.details

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.Ro
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.realestatemanager.R
import com.example.realestatemanager.data.local.property.PropertyEntity
import com.example.realestatemanager.designsystem.AppScaffold
import com.example.realestatemanager.designsystem.Black
import com.example.realestatemanager.designsystem.Blue
import com.example.realestatemanager.designsystem.Grey
import com.example.realestatemanager.designsystem.LightGrey
import com.example.realestatemanager.designsystem.bar.TopBar
import com.example.realestatemanager.designsystem.card.CardImage
import com.example.realestatemanager.designsystem.card.CardWithIcon
import com.example.realestatemanager.designsystem.fonts
import com.example.realestatemanager.designsystem.map.GoogleMapItem
import com.example.realestatemanager.designsystem.ui.Default
import com.example.realestatemanager.designsystem.ui.Spacer
import com.example.realestatemanager.designsystem.ui.Spacings
import com.example.realestatemanager.feature.details.model.LocationState
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun DetailsRoute(
    isExpandedScreen: Boolean,
    onBackClick: () -> Unit,
    onModifyClick: (Int) -> Unit,
    propertyId: Int,
    viewModel: DetailsViewModel = hiltViewModel(),
) {
    val property by viewModel.getPropertyById(propertyId).collectAsState(initial = null)
    val locationState by viewModel.locationState.collectAsState(LocationState.Loading)

    var latitude: Double? = property?.latitude
    var longitude: Double? = property?.longitude

    LaunchedEffect(property) {
        property?.let {
            if (it.latitude == null || it.longitude == null) {
                it.address?.let { address ->
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

    property?.let {
        DetailsScreen(
            isExpandedScreen = isExpandedScreen,
            propertyEntity = it,
            onBackClick = onBackClick,
            onModifyClick = onModifyClick,
            onDeleteClick = { viewModel.deleteProperty(propertyId, onBackClick) },
            latitude = latitude ?: it.latitude ?: 0.0,
            longitude = longitude ?: it.longitude ?: 0.0
        )
    } ?: Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    isExpandedScreen: Boolean,
    propertyEntity: PropertyEntity,
    onBackClick: () -> Unit,
    onModifyClick: (Int) -> Unit,
    onDeleteClick: (Int) -> Unit,
    latitude: Double,
    longitude: Double
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(latitude, longitude), 15f)
    }
    LaunchedEffect(latitude, longitude) {
        cameraPositionState.move(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(latitude, longitude),
                15f
            )
        )
    }
    AppScaffold(
        topBar = {
            if (!isExpandedScreen) {
                TopBar(
                    onBackClick = onBackClick,
                    onModifyClick = { propertyEntity.id?.let { onModifyClick(it) } },
                    onDeleteClick = { propertyEntity.id?.let { onDeleteClick(it) } }
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier.padding(Spacings.Default)
            ) {
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
                Spacer.Vertical.Default()
                Text(
                    text = propertyEntity.description ?: "",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = fonts,
                        textAlign = TextAlign.Start,
                        color = Black
                    )
                )
                Spacer.Vertical.Large()
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CardWithIcon(
                        icon = R.drawable.ic_surface,
                        title = "Surface",
                        info = "${propertyEntity.surface} + m²"
                    )
                    Spacer.Horizontal.Large()
                    CardWithIcon(
                        icon = R.drawable.ic_bed,
                        title = "Room",
                        info = propertyEntity.room.toString()
                    )
                }
                Spacer.Vertical.Large()
                CardWithIcon(
                    icon = R.drawable.ic_location,
                    title = "Location",
                    info = propertyEntity.address.toString()
                )
                Spacer.Vertical.Large()
                CardImage(
                    imageUri = propertyEntity.image ?: ""
                )
            }
            Spacer.Vertical.Large()
            GoogleMapItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                cameraPosition = cameraPositionState,
                state = MarkerState(position = LatLng(latitude, longitude))
            )
        }
    }
}