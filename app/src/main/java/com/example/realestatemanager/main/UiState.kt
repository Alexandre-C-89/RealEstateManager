package com.example.realestatemanager.main

import com.example.realestatemanager.DISPLAY_TYPE
import com.example.realestatemanager.database.datasource.Property

data class UiState(
    val displayType : DISPLAY_TYPE = DISPLAY_TYPE.ALL,
    val currentList : List<Property> = emptyList()
)
