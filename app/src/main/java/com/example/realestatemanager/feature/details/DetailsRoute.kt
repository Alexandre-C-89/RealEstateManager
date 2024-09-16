package com.example.realestatemanager.feature.details

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.realestatemanager.data.local.property.PropertyEntity
import com.example.realestatemanager.designsystem.AppScaffold
import com.example.realestatemanager.designsystem.Black
import com.example.realestatemanager.designsystem.Blue
import com.example.realestatemanager.designsystem.bar.TopBar
import com.example.realestatemanager.designsystem.fonts
import com.example.realestatemanager.designsystem.map.GoogleMapItem
import com.example.realestatemanager.designsystem.ui.Default
import com.example.realestatemanager.designsystem.ui.Spacer
import com.example.realestatemanager.designsystem.ui.Spacings
import com.example.realestatemanager.feature.details.model.LocationState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun DetailsRoute(
    onBackClick: () -> Unit,
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
            latitude = result.results.firstOrNull()?.geometry?.location?.lat ?: latitude
            longitude = result.results.firstOrNull()?.geometry?.location?.lng ?: longitude
        }

        is LocationState.Error -> {
            val message = state.message
        }
    }

    property?.let {
        DetailsScreen(
            propertyEntity = it,
            onBackClick = onBackClick,
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
    propertyEntity: PropertyEntity,
    onBackClick: () -> Unit,
    latitude: Double,
    longitude: Double
) {
    /*val images = listOf(
        R.drawable.lounge,
        R.drawable.tv_lounge,
        R.drawable.dining_room
    )*/
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(latitude, longitude), 17f)
    }
    AppScaffold(
        topBar = {
            TopBar(
                onBackClick = onBackClick
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(Spacings.Default)
            ) {
                Text(
                    text = "Description",
                    style = TextStyle(
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = fonts,
                        textAlign = TextAlign.Start,
                        color = Blue
                    )
                )
                Spacer.Vertical.Small()
                Text(
                    text = propertyEntity.description ?: "",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = fonts,
                        textAlign = TextAlign.Start,
                        color = Black
                    )
                )
                Spacer.Vertical.Large()
                Text(
                    text = "Surface - ${propertyEntity.surface} • piece • Room - ${propertyEntity.room}",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = fonts,
                        textAlign = TextAlign.Start,
                        color = Blue
                    )
                )
                Spacer.Vertical.Large()
                Text(text = "Location")
                Spacer.Vertical.Small()
                Text(
                    text = propertyEntity.address ?: "",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = fonts,
                        textAlign = TextAlign.Start,
                        color = Blue
                    )
                )
            }
            Spacer.Vertical.Large()
            GoogleMapItem(
                cameraPosition = cameraPositionState,
                state = MarkerState(position = LatLng(latitude, longitude))
            )
        }
    }
}