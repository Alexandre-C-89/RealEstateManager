package com.example.realestatemanager.feature.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realestatemanager.data.local.property.PropertyEntity
import com.example.realestatemanager.domain.repository.PropertyRepository
import com.example.realestatemanager.domain.repository.LocationRepository
import com.example.realestatemanager.feature.details.model.LocationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: PropertyRepository,
    //private val locationRepository: LocationRepository
) : ViewModel() {

    private val _locationState = MutableStateFlow<LocationState>(LocationState.Loading)
    val locationState: StateFlow<LocationState> = _locationState

    fun getPropertyById(propertyId: Int): Flow<PropertyEntity?> {
        Log.d("DetailsViewModel", "Fetching property with ID: $propertyId")
        return repository.getPropertyById(propertyId)
    }

    /*fun fetchCoordinates(address: String) {
        viewModelScope.launch {
            locationRepository.getConvertAddress(address)
                .onStart {
                    _locationState.value = LocationState.Loading
                }
                .catch { e ->
                    _locationState.value = LocationState.Error(e.message ?: "An error occurred")
                }
                .collect { result ->
                    _locationState.value = LocationState.Success(result.data!!)
                }
        }
    }*/

}