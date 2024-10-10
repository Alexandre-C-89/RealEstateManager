package com.example.realestatemanager.feature.map

import android.Manifest
import android.content.Intent
import android.provider.Settings
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.realestatemanager.designsystem.AppScaffold
import com.example.realestatemanager.designsystem.alert.RationaleAlert
import com.example.realestatemanager.designsystem.bar.BottomBar
import com.example.realestatemanager.designsystem.bar.TopBar
import com.example.realestatemanager.extension.hasLocationPermission
import com.example.realestatemanager.feature.details.model.LocationState
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MapRoute(
    viewModel: MapViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onMapClick: () -> Unit,
    onHomeClick: () -> Unit,
    onEditClick: () -> Unit
) {
    val context = LocalContext.current
    val locationState by viewModel.locationState.collectAsStateWithLifecycle()
    val userLocationState by viewModel.userLocationState.collectAsStateWithLifecycle()
    val cameraPositionState = rememberCameraPositionState()
    val permissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

    LaunchedEffect(!context.hasLocationPermission()) {
        permissionState.launchMultiplePermissionRequest()
    }

    when {
        permissionState.allPermissionsGranted -> {
            Log.d("ALLPERMISSIONSGRANTED","$permissionState")
            LaunchedEffect(Unit) {
                viewModel.handle(PermissionEvent.Granted)
            }
        }

        permissionState.shouldShowRationale -> {
            Log.d("ALLPERMISSIONSRATIONALE","$permissionState")
            RationaleAlert(onDismiss = { }) {
                permissionState.launchMultiplePermissionRequest()
            }
        }

        !permissionState.allPermissionsGranted && !permissionState.shouldShowRationale -> {
            Log.d("ALLPERMISSIONSGRANTEDRATIONALE","$permissionState")
            LaunchedEffect(Unit) {
                viewModel.handle(PermissionEvent.Revoked)
            }
        }
    }

    with(userLocationState) {
        when (this) {
            UserLocationState.Loading -> {
                Log.d("USERLOCATIONSTATELOADING","$userLocationState")
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            UserLocationState.RevokedPermissions -> {
                Log.d("USERLOCATIONSTATEREVOKED","$userLocationState")
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("We need permissions to use this app")
                    Button(
                        onClick = {
                            viewModel.onOpenLocationSettings()
                        },
                        enabled = !context.hasLocationPermission()
                    ) {
                        if (context.hasLocationPermission()) CircularProgressIndicator(
                            modifier = Modifier.size(14.dp),
                            color = Color.White
                        )
                        else Text("Settings")
                    }
                }
            }

            is UserLocationState.Success -> {
                val currentLoc = this.location
                Log.d("USERLOCATIONSTATESUCCCESS","$currentLoc")
                currentLoc?.let {
                    LaunchedEffect(key1 = currentLoc) {
                        cameraPositionState.centerOnLocation(currentLoc)
                    }
                    MapScreen(
                        locationState = locationState,
                        currentLoc = currentLoc,
                        cameraPositionState = cameraPositionState,
                        onBackClick = onBackClick,
                        onMapClick = onMapClick,
                        onHomeClick = onHomeClick,
                        onEditClick = onEditClick
                    )

                } ?: run {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Unable to retrieve user location")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
    locationState: LocationState,
    currentLoc: LatLng?,
    cameraPositionState : CameraPositionState,
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
                    cameraPositionState = cameraPositionState
                ) {
                    when (locationState) {
                        is LocationState.Success -> {
                            val location =
                                locationState.geocodingResult?.results?.firstOrNull()?.geometry?.location?.let {
                                    LatLng(it.lat, it.lng)
                                }
                            location?.let {
                                Marker(
                                    state = rememberMarkerState(position = it),
                                    title = "Propriété"
                                )
                                LaunchedEffect(it) {
                                    cameraPositionState.animate(
                                        CameraUpdateFactory.newLatLngZoom(it, 15f)
                                    )
                                }
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

private suspend fun CameraPositionState.centerOnLocation(
    location: LatLng
) = animate(
    update = CameraUpdateFactory.newLatLngZoom(
        location,
        15f
    ),
    durationMs = 1500
)