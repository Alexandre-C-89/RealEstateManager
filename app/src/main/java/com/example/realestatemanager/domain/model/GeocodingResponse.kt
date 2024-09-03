package com.example.realestatemanager.domain.model

data class GeocodingResponse(
    val results: List<Result>,
    val status: String
)