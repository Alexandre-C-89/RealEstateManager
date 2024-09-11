package com.example.realestatemanager.feature.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realestatemanager.DISPLAY_TYPE
import com.example.realestatemanager.data.local.property.PropertyEntity
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

    private val _properties = MutableStateFlow<List<PropertyEntity>>(emptyList())
    val properties: StateFlow<List<PropertyEntity>> = _properties

    val uiState = mutableStateOf(UiState())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            insertFake() // Insert fake data
            getProperties() // Then load the properties
        }
    }

    private suspend fun getProperties() {
        when (uiState.value.displayType) {
            DISPLAY_TYPE.ALL -> {
                propertyRepository.getAllProperties().collect { list ->
                    // Use withContext(Dispatchers.Main) when modifying state used in Composable
                    withContext(Dispatchers.Main) {
                        uiState.value = uiState.value.copy(
                            currentList = list
                        )
                    }
                }
            }
        }
    }

    fun switchDisplayType(dt: DISPLAY_TYPE) {
        uiState.value = uiState.value.copy(
            displayType = dt
        )
        viewModelScope.launch {
            getProperties()
        }
    }

    fun insert(propertyEntity: PropertyEntity) {
        viewModelScope.launch {
            propertyRepository.insert(propertyEntity)
        }
    }

    private suspend fun insertFake() {
        try {
            val fakeProperties = listOf(
                PropertyEntity(
                    id = 0,
                    type = "Lounge",
                    price = 41001100,
                    surface = 140,
                    room = 4,
                    image = "",
                    description = "A wonderful house type of Lounge with a good view on the city",
                    address = "14 Garp Street",
                    interest = "",
                    status = "For sell",
                    dateOfCreation = 12022024,
                    dateOfSold = 14052024,
                    agent = "Axel"
                    /*latitude = 48.933331,
                    longitude = 2.41667*/
                ),
                PropertyEntity(
                    id = 1,
                    type = "House",
                    price = 45601200,
                    surface = 160,
                    room = 5,
                    image = "",
                    description = "A wonderful house with a good view on the city. possibility of changing the attic into a bedroom",
                    address = "140 Park Avenue",
                    interest = "",
                    status = "Sold",
                    dateOfCreation = 12022024,
                    dateOfSold = 14052024,
                    agent = "Axel"
                    /*latitude = 48.933331,
                    longitude = 2.41667*/
                )
            )
            fakeProperties.forEach { property ->
                propertyRepository.insertFake(property)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun delete(propertyEntity: PropertyEntity) {
        viewModelScope.launch {
            propertyRepository.delete(propertyEntity)
        }
    }

    fun update(propertyEntity: PropertyEntity) {
        viewModelScope.launch {
            propertyRepository.update(propertyEntity)
        }
    }

}