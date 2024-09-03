package com.example.realestatemanager.feature.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realestatemanager.data.local.property.PropertyEntity
import com.example.realestatemanager.domain.model.Location
import com.example.realestatemanager.domain.repository.PropertyRepository
import com.example.realestatemanager.feature.details.model.ResponseState
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: PropertyRepository
) : ViewModel() {

    private val _coordinates = MutableStateFlow<Location?>(null)
    val coordinates: StateFlow<Location?> = _coordinates

    private val _propertyState = MutableStateFlow<ResponseState<PropertyEntity>>(ResponseState.Idle)
    val propertyState: StateFlow<ResponseState<PropertyEntity>> = _propertyState

    private val _coordinatesState = MutableStateFlow<ResponseState<LatLng>>(ResponseState.Idle)
    val coordinatesState: StateFlow<ResponseState<LatLng>> = _coordinatesState

    fun getPropertyById(propertyId: Int): Flow<PropertyEntity?> {
        return repository.getPropertyById(propertyId)
    }

    fun fetchCoordinatesForAddress(address: String) {
        viewModelScope.launch {
            _coordinatesState.value = ResponseState.Loading
            try {
                val result = repository.getCoordinates(address)
                if (result != null) {
                    _coordinatesState.value = ResponseState.Success(result)
                } else {
                    _coordinatesState.value = ResponseState.Error(Exception("Coordinates not found"))
                }
            } catch (e: Exception) {
                _coordinatesState.value = ResponseState.Error(e)
            }
        }
    }

}