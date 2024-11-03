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
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
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
                        _locationState.value = LocationState.Error("Could not fetch coordinates")
                    }
                }
        }
    }

    fun formatCustomDate(dateLong: Long?): String {
        if (dateLong == null) return "Date not specified"

        val dateStr = dateLong.toString().padStart(8, '0') // Ensure it's 8 digits
        val day = dateStr.substring(0, 2)
        val month = dateStr.substring(2, 4)
        val year = dateStr.substring(4, 8)

        return "$day-$month-$year"
    }

    fun deleteProperty(propertyId: Int, onSuccess:() -> Unit){
        viewModelScope.launch {
            try {
                repository.delete(propertyId)
                onSuccess()
            } catch (e: Exception){
                Log.e("DetailsViewModel", "Error deleting property: ${e.message}")
            }
        }
    }

}