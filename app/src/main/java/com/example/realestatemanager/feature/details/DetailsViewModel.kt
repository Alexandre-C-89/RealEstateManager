package com.example.realestatemanager.feature.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realestatemanager.data.local.property.PropertyEntity
import com.example.realestatemanager.domain.repository.LocationRepository
import com.example.realestatemanager.domain.repository.PropertyRepository
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
    private val locationRepository: LocationRepository
) : ViewModel() {

    private val _locationState = MutableStateFlow<LocationState>(LocationState.Loading)
    val locationState: StateFlow<LocationState> = _locationState

    fun getPropertyById(propertyId: Int): Flow<PropertyEntity> {
        return repository.getPropertyById(propertyId)
    }

    fun fetchCoordinates(address: String) {
        viewModelScope.launch {
            locationRepository.getConvertAddress(address)
                .onStart {
                    _locationState.value = LocationState.Loading
                }
                .catch { e ->
                    _locationState.value = LocationState.Error(e.message ?: "An error occurred")
                }
                .collect { result ->
                    if (result.data != null) {
                        _locationState.value = LocationState.Success(result.data)
                    } else {
                        // Handle the case where data is null, for example:
                        _locationState.value = LocationState.Error("Could not fetch coordinates")
                    }
                }
        }
    }

    fun deleteProperty(propertyId: Int, onSuccess:() -> Unit){
        viewModelScope.launch {
            try {
                repository.delete(propertyId)
                Log.d("DetailsViewModel", "Property deleted successfully.")
                onSuccess()
            } catch (e: Exception){
                Log.e("DetailsViewModel", "Error deleting property: ${e.message}")
            }
        }
    }

}