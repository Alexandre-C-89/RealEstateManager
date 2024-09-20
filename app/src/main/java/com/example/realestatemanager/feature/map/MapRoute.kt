package com.example.realestatemanager.feature.map

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.realestatemanager.designsystem.AppScaffold
import com.example.realestatemanager.designsystem.bar.BottomBar
import com.example.realestatemanager.designsystem.bar.TopBar
import com.example.realestatemanager.feature.details.model.LocationState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun MapRoute(
    viewModel: MapViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onMapClick: () -> Unit,
    onHomeClick: () -> Unit,
    onEditClick: () -> Unit
) {

    val locationState by viewModel.locationState.collectAsStateWithLifecycle()

    MapScreen(
        locationState = locationState,
        onBackClick = onBackClick,
        onMapClick = onMapClick,
        onHomeClick = onHomeClick,
        onEditClick = onEditClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
    locationState: LocationState,
    onBackClick: () -> Unit,
    onMapClick: () -> Unit,
    onHomeClick: () -> Unit,
    onEditClick: () -> Unit
) {
    AppScaffold(
        topBar = {
            TopBar(
                onBackClick = onBackClick
            )
        },
        bottomBar = {
            Surface {
                BottomBar(
                    onMapClick = onMapClick,
                    onHomeClick = onHomeClick,
                    onEditClick = onEditClick
                )
            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            if (locationState is LocationState.Success || locationState is LocationState.MultipleSuccess) {
                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    cameraPositionState = rememberCameraPositionState {
                        position = CameraPosition.fromLatLngZoom(LatLng(0.0, 0.0), 12f)
                    }
                ) {
                    when (locationState) {
                        is LocationState.Success -> {
                            val location = locationState.geocodingResult?.results?.firstOrNull()?.geometry?.location?.let {
                                LatLng(it.lat, it.lng)
                            }
                            location?.let {
                                Marker(
                                    state = rememberMarkerState(position = it),
                                    title = "Propriété"
                                )
                            }
                        }

                        is LocationState.MultipleSuccess -> {
                            val locations = locationState.geocodingResults ?: emptyList()
                            locations.forEach { geocodingResult ->
                                geocodingResult.results.firstOrNull()?.geometry?.location?.let { loc ->
                                    val position = LatLng(loc.lat, loc.lng)
                                    Marker(
                                        state = rememberMarkerState(position = position),
                                        title = "Propriété"
                                    )
                                }
                            }
                        }

                        else -> Unit
                    }
                }
            }

            if (locationState is LocationState.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }

            if (locationState is LocationState.Error) {
                Text(
                    text = locationState.message,
                    color = Color.Red,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
    }
}