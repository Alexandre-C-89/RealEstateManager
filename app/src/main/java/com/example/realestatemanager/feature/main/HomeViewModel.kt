package com.example.realestatemanager.feature.main

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realestatemanager.DISPLAY_TYPE
import com.example.realestatemanager.data.local.property.PropertyEntity
import com.example.realestatemanager.domain.repository.ContentProviderRepository
import com.example.realestatemanager.domain.repository.PropertyRepository
import com.example.realestatemanager.util.NetworkUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val propertyRepository: PropertyRepository,
    private val contentProviderRepository: ContentProviderRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _properties = MutableStateFlow(UiState())
    val properties: StateFlow<UiState> = _properties

    private val _propertiesOffline = MutableLiveData<List<PropertyEntity>>()
    val propertiesOffline: LiveData<List<PropertyEntity>> = _propertiesOffline

    val uiState = mutableStateOf(UiState())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            insertFake() // Insert fake data
            getProperties() // Then load the properties
        }
    }

    private suspend fun getProperties() {
        if (NetworkUtil.isNetworkAvailable(context)) {
            Log.d("HOMEVIEWMODEL", "NETWORK IS AVAILABLE")
            when (uiState.value.displayType) {
                DISPLAY_TYPE.ALL -> {
                    propertyRepository.getAllProperties().collect { list ->
                        Log.d("HOMEVIEWMODEL", "$list")
                        withContext(Dispatchers.Main) {
                            _properties.value = properties.value.copy(
                                currentList = list
                            )
                        }
                    }
                }
            }
        } else {
            Log.d("HOMEVIEWMODEL", "NETWORK IS UNAVAILABLE")
            fetchProperties()
        }
    }

    private fun fetchProperties() {
        viewModelScope.launch {
            val result = contentProviderRepository.getAllProperties()
            _propertiesOffline.postValue(result)
        }
    }

    private suspend fun insertFake() {
        try {
            val fakeProperties = listOf(
                PropertyEntity(
                    id = 0,
                    type = "Lounge",
                    price = 410.045,
                    surface = 140,
                    room = 4,
                    image = "",
                    description = "A wonderful house type of Lounge with a good view on the city",
                    address = "9 Rue Marcellin Berthelot, Le Bourget",
                    interest = "",
                    status = "For sell",
                    dateOfCreation = 12022024,
                    dateOfSold = 14052024,
                    agent = "Axel",
                    latitude = null,
                    longitude = null
                ),
                PropertyEntity(
                    id = 1,
                    type = "House",
                    price = 456.012,
                    surface = 160,
                    room = 5,
                    image = "",
                    description = "A wonderful house with a good view on the city. possibility of changing the attic into a bedroom",
                    address = "10-16 Rue de l'Égalité, Le Bourget",
                    interest = "",
                    status = "Sold",
                    dateOfCreation = 12022024,
                    dateOfSold = 14052024,
                    agent = "Axel",
                    latitude = null,
                    longitude = null
                )
            )
            fakeProperties.forEach { property ->
                propertyRepository.insertFake(property)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}