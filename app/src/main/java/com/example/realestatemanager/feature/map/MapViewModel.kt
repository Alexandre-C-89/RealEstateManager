package com.example.realestatemanager.feature.map

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realestatemanager.data.local.contentProvider.ContentProviderHelper.init
import com.example.realestatemanager.domain.GetLocationUseCase
import com.example.realestatemanager.domain.repository.LocationRepository
import com.example.realestatemanager.domain.repository.PropertyRepository
import com.example.realestatemanager.feature.details.model.LocationState
import com.example.realestatemanager.util.Resource
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
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
    private val getLocationUseCase: GetLocationUseCase,
) : ViewModel() {

    private val _locationState = MutableStateFlow<LocationState>(LocationState.Loading)
    val locationState: StateFlow<LocationState> = _locationState

    private val _userLocationState = MutableStateFlow<UserLocationState>(UserLocationState.Loading)
    val userLocationState: StateFlow<UserLocationState> = _userLocationState

    val isLocationPermissionGranted = MutableLiveData(false)

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

    @RequiresApi(Build.VERSION_CODES.S)
    fun handle(event: PermissionEvent) {
        when (event) {
            PermissionEvent.Granted -> {
                viewModelScope.launch {
                    getLocationUseCase.invoke().collect { location ->
                        _userLocationState.value = UserLocationState.Success(location)
                    }
                }
            }

            PermissionEvent.Revoked -> {
                _userLocationState.value = UserLocationState.RevokedPermissions
            }
        }
    }

}

sealed interface PermissionEvent {
    object Granted : PermissionEvent
    object Revoked : PermissionEvent
}