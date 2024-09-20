package com.example.realestatemanager.feature.map

import com.example.realestatemanager.domain.model.GeocodingResult
import com.example.realestatemanager.util.Resource
import kotlinx.coroutines.flow.Flow

sealed class MapState {
    object Loading : MapState()
    data class Success(val geocodingResult: Flow<Resource<GeocodingResult>>) : MapState()
    data class Error(val message: String) : MapState()
}