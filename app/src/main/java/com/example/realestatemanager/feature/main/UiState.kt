package com.example.realestatemanager.feature.main

import com.example.realestatemanager.DISPLAY_TYPE
import com.example.realestatemanager.data.local.property.PropertyEntity

data class UiState(
    //val displayType : DISPLAY_TYPE = DISPLAY_TYPE.ALL,
    val currentList : List<PropertyEntity> = emptyList(),
    val selectedProperty : PropertyEntity? = null
)
