package com.example.realestatemanager.domain.model

data class GeocodingResult(
    val results: List<Result>,
    val status: String
)