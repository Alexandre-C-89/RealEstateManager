package com.example.realestatemanager.feature.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker

@Composable
fun MapRoute(
    onBackClick: () -> Unit
){
    MapScreen(
        onBackClick = onBackClick
    )
}

@Composable
fun MapScreen(
    onBackClick: () -> Unit
){
    GoogleMap(
        modifier = Modifier.fillMaxSize()
    ){
        Marker(

        )
    }
}