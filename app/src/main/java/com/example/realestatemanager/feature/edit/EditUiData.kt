package com.example.realestatemanager.feature.edit

import androidx.compose.ui.text.input.TextFieldValue

data class EditUiData(
    val type: TextFieldValue = TextFieldValue(),
    val price: TextFieldValue = TextFieldValue(),
    val surface: TextFieldValue = TextFieldValue(),
    val room: TextFieldValue = TextFieldValue(),
    val image: String = "",
    val description: TextFieldValue = TextFieldValue(),
    val address: TextFieldValue = TextFieldValue(),
    val interest: TextFieldValue = TextFieldValue(),
    val status: TextFieldValue = TextFieldValue(),
    val dateOfCreation: TextFieldValue = TextFieldValue(),
    val dateOfSold: TextFieldValue = TextFieldValue(),
    val agent: TextFieldValue = TextFieldValue(),
    val latitude: TextFieldValue = TextFieldValue(),
    val longitude: TextFieldValue = TextFieldValue(),
)