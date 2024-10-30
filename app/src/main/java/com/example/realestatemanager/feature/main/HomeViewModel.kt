package com.example.realestatemanager.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realestatemanager.domain.repository.PropertyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val propertyRepository: PropertyRepository
) : ViewModel() {

    private val _properties = MutableStateFlow(UiState())
    val properties: StateFlow<UiState> = _properties

    init {
        getProperties()
    }

    private fun getProperties() {
        viewModelScope.launch(Dispatchers.IO) {
            propertyRepository.getAllProperties().collect { list ->
                withContext(Dispatchers.Main) {
                    _properties.value = properties.value.copy(
                        currentList = list
                    )
                }
            }
        }
    }
}