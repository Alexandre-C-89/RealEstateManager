package com.example.realestatemanager.feature.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realestatemanager.domain.model.GeocodingResult
import com.example.realestatemanager.domain.model.Geometry
import com.example.realestatemanager.domain.model.Location
import com.example.realestatemanager.domain.model.Result
import com.example.realestatemanager.domain.repository.LocationRepository
import com.example.realestatemanager.domain.repository.PropertyRepository
import com.example.realestatemanager.feature.details.model.LocationState
import com.example.realestatemanager.util.Resource
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val locationRepository: LocationRepository,
    private val propertyRepository: PropertyRepository,
    private val locationProvider: FusedLocationProviderClient
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

    fun updateLocation() {
        // Méthode pour récupérer la localisation dès que la permission est accordée
        if (areLocationPermissionsGranted()) {
            locationProvider.lastLocation
                .addOnSuccessListener { location ->
                    if (location != null) {
                        // Créer un `GeocodingResult` ou un objet similaire à partir de la localisation
                        val geocodingResult = GeocodingResult(
                            results = listOf(
                                Result(
                                    formattedAddress = "Current Location",
                                    geometry = Geometry(
                                        location = Location(location.latitude, location.longitude)
                                    )
                                )
                            ),
                            status = "OK"
                        )

                        _locationState.value = LocationState.Success(geocodingResult)
                    } else {
                        _locationState.value = LocationState.Error("Unable to retrieve location")
                    }
                }
                .addOnFailureListener {
                    _locationState.value = LocationState.Error(it.message ?: "Error fetching location")
                }
        } else {
            _locationState.value = LocationState.Error("Permission not granted")
        }
    }

    private fun areLocationPermissionsGranted(): Boolean {
        // Implémentation de vérification des permissions
        return true // remplace par une vérification réelle des permissions
    }

}