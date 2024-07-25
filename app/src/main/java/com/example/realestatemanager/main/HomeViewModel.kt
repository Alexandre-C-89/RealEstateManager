package com.example.realestatemanager.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realestatemanager.database.datasource.PropertyDatabase
import com.example.realestatemanager.database.datasource.Property
import com.example.realestatemanager.database.repository.PropertyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class HomeViewModel(
    private val propertyRepository: PropertyRepository,
    private val propertyDatabase: PropertyDatabase
) : ViewModel() {

    private val _properties = MutableStateFlow<List<Property>>(emptyList())
    val properties: StateFlow<List<Property>> = _properties

    val uiState = mutableStateOf(UiState())

    var fakeProperty = propertyDatabase.propertyDao()

    init {
        viewModelScope.launch {
            propertyRepository.getAllProperties().collect {
                _properties.value = it
            }
        }

    }

    fun insertFake(property: Property){
        viewModelScope.launch {
            propertyRepository.insertFake(property)
        }
    }

    fun upsertProperty(property: Property) {
        viewModelScope.launch {
            propertyRepository.insert(property)
        }
    }

}