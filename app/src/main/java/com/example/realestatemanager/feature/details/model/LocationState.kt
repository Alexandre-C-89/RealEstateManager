package com.example.realestatemanager.feature.details.model

import com.example.realestatemanager.domain.model.GeocodingResult

sealed class LocationState {
    object Loading : LocationState()
    data class Success(val geocodingResult: GeocodingResult?) : LocationState()
    data class MultipleSuccess(val geocodingResults: List<GeocodingResult>?) : LocationState()
    data class Error(val message: String) : LocationState()
}