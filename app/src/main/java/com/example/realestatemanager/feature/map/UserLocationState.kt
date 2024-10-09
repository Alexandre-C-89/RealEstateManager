package com.example.realestatemanager.feature.map

import com.google.android.gms.maps.model.LatLng

sealed interface UserLocationState {
    object Loading : UserLocationState
    data class Success(val location: LatLng?) : UserLocationState
    object RevokedPermissions: UserLocationState
}