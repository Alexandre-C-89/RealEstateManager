package com.example.realestatemanager.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.realestatemanager.database.Property
import com.example.realestatemanager.database.PropertyDao
import com.example.realestatemanager.database.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


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