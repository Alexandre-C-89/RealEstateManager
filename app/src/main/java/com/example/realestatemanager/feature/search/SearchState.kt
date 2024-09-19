package com.example.realestatemanager.feature.search

import com.example.realestatemanager.data.local.property.PropertyEntity

sealed class SearchState {

    object Loading : SearchState()
    data class Success(val dataList: List<PropertyEntity>) : SearchState()
    data class Error(val message: String) : SearchState()

}