package com.example.realestatemanager.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realestatemanager.database.Property
import com.example.realestatemanager.database.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class HomeViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _properties = MutableStateFlow<List<Property>>(emptyList())
    val properties: StateFlow<List<Property>> = _properties

    init {
        viewModelScope.launch {
            repository.getAllProperties().collect {
                _properties.value = it
            }
        }
    }

    fun upsertProperty(property: Property) {
        viewModelScope.launch {
            repository.insert(property)
        }
    }

}