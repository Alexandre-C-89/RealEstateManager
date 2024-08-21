package com.example.realestatemanager.designsystem.map

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.realestatemanager.designsystem.DarkBlue
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun GoogleMapItem(
    cameraPositionState: CameraPositionState,
    //onMapLoaded: () -> Unit,
    content: @Composable () -> Unit
){
    com.google.maps.android.compose.GoogleMap(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
            .border(width = 5.dp, color = DarkBlue),
        cameraPositionState = cameraPositionState,
        //onMapLoaded = onMapLoaded,
    ) {
        content()
    }
}