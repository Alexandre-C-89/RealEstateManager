package com.example.realestatemanager.feature.modify.model

import androidx.compose.ui.text.input.TextFieldValue

data class ModifyUiData(
    val type: TextFieldValue = TextFieldValue(),
    val price: TextFieldValue = TextFieldValue(),
    val surface: TextFieldValue = TextFieldValue(),
    val room: TextFieldValue = TextFieldValue(),
    val image: String = "",
    val description: TextFieldValue = TextFieldValue(),
    val address: TextFieldValue = TextFieldValue(),
    val school: Boolean = false,
    val shops: Boolean = false,
    val sale: Boolean = false,
    val dateOfCreation: TextFieldValue = TextFieldValue(),
    val dateOfSold: TextFieldValue = TextFieldValue(),
    val agent: TextFieldValue = TextFieldValue(),
    val latitude: TextFieldValue = TextFieldValue(),
    val longitude: TextFieldValue = TextFieldValue(),
)
