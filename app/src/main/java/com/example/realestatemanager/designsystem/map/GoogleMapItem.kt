package com.example.realestatemanager.designsystem.map

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun GoogleMapItem(
    cameraPositionState: CameraPositionState,
    onMapLoaded: () -> Unit,
){
    val leBourget = LatLng(48.936752, 2.425377)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(leBourget, 10f)
    }

    com.google.maps.android.compose.GoogleMap(
        modifier = Modifier
            .height(40.dp)
            .width(40.dp),
        mergeDescendants = false,
        cameraPositionState = cameraPositionState/*rememberCameraPositionState()*/,
        contentDescription = "map for localization property",
        onMapLoaded = onMapLoaded,
        contentPadding = PaddingValues(8.dp)
    )
}