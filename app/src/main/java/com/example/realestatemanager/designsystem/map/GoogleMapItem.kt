package com.example.realestatemanager.designsystem.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun GoogleMapItem(
    coordinates: LatLng
){

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(coordinates, 12f)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        uiSettings = MapUiSettings(zoomControlsEnabled = false)
    ) {
        coordinates?.let {
            Marker(
            state = MarkerState(position = it),
            title = "Property Location",
            snippet = "Property address"
            )
        }
    }

    /*com.google.maps.android.compose.GoogleMap(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
            .border(width = 5.dp, color = DarkBlue),
        cameraPositionState = cameraPositionState,
    ) {
        content()
    }*/
}