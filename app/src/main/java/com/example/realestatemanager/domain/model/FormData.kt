package com.example.realestatemanager.domain.model

data class FormData(
    val priceMin: String,
    val priceMax: String,
    val surfaceMin: String,
    val surfaceMax: String,
    val school: Boolean,
    val shops: Boolean,
    val sale: Boolean?,
    val minPhotos: String,
    val maxPhotos: String
)
