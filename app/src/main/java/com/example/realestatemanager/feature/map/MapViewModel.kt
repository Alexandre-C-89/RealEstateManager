package com.example.realestatemanager.feature.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realestatemanager.domain.repository.LocationRepository
import com.example.realestatemanager.domain.repository.PropertyRepository
import com.example.realestatemanager.feature.details.model.LocationState
import com.example.realestatemanager.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val locationRepository: LocationRepository,
    private val propertyRepository: PropertyRepository
) : ViewModel() {

    private val _locationState = MutableStateFlow<LocationState>(LocationState.Loading)
    val locationState: StateFlow<LocationState> = _locationState

    init {
        getAllPropertiesMarker()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getAllPropertiesMarker() {
        viewModelScope.launch {
            try {
                propertyRepository.getAllAddresses()
                    .flatMapConcat { addresses ->
                        locationRepository.getConvertAddresses(addresses)
                    }
                    .collect { resource ->
                        when (resource) {
                            is Resource.Success -> {
                                _locationState.value = LocationState.MultipleSuccess(resource.data)
                            }
                            is Resource.Error -> {
                                _locationState.value = LocationState.Error(resource.message ?: "An error occurred")
                            }
                            is Resource.Loading -> {
                                _locationState.value = LocationState.Loading
                            }
                        }
                    }
            } catch (e: Exception) {
                _locationState.value = LocationState.Error(e.message ?: "An error occurred")
            }
        }
    }

}