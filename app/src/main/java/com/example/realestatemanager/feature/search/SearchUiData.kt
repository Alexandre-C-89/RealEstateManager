package com.example.realestatemanager.feature.search

import androidx.compose.ui.text.input.TextFieldValue

data class SearchUiData(
    val priceMin: TextFieldValue = TextFieldValue(),
    val priceMax: TextFieldValue = TextFieldValue(),
    val surfaceMin: TextFieldValue = TextFieldValue(),
    val surfaceMax: TextFieldValue = TextFieldValue(),
    val school: Boolean = false,
    val shops: Boolean = false,
    val sale: Boolean = false,
    val minPhotos: TextFieldValue = TextFieldValue(),
    val maxPhotos: TextFieldValue = TextFieldValue(),
)
