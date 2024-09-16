package com.example.realestatemanager.feature.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.example.realestatemanager.designsystem.AppScaffold
import com.example.realestatemanager.designsystem.bar.BottomBar
import com.example.realestatemanager.designsystem.bar.TopBar
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapRoute(
    //viewModel: MapViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onMapClick: () -> Unit,
    onHomeClick: () -> Unit,
    onEditClick: () -> Unit,
    //propertyAddress: String
) {

    //val locationState by viewModel.locationState.collectAsStateWithLifecycle()

    /*LaunchedEffect(propertyAddress) {
        // Convertir l'adresse en coordonnées géographiques lorsque la carte est affichée
        //viewModel.convertAddressToLocation(propertyAddress)
    }*/

    MapScreen(
        //locationState = locationState,
        onBackClick = onBackClick,
        onMapClick = onMapClick,
        onHomeClick = onHomeClick,
        onEditClick = onEditClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
    //locationState: LocationState,
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
        /*when (locationState) {
            is LocationState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            }

            is LocationState.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(text = (locationState as LocationState.Error).message)
                }
            }

            is LocationState.Loaded -> {
                val location = (locationState as LocationState.Loaded).location*/
                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    cameraPositionState = rememberCameraPositionState {
                        position = CameraPosition.fromLatLngZoom(LatLng(0.0, 0.0), 12f)
                    }
                ) {
                    Marker(
                        zIndex = 12F,
                        title = "Propriété"
                    )
                }
            /*}
        }*/
    }
}