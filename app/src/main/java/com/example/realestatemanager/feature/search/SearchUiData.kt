package com.example.realestatemanager.feature.search

import androidx.compose.ui.text.input.TextFieldValue

data class SearchUiData(
    val priceMin: TextFieldValue = TextFieldValue(),
    val priceMax: TextFieldValue = TextFieldValue(),
    val surfaceMin: TextFieldValue = TextFieldValue(),
    val surfaceMax: TextFieldValue = TextFieldValue(),
    val school: Boolean,
    val shops: Boolean
)
