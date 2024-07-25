package com.example.realestatemanager.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realestatemanager.DISPLAY_TYPE
import com.example.realestatemanager.database.datasource.Property
import com.example.realestatemanager.database.repository.PropertyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val propertyRepository: PropertyRepository
) : ViewModel() {

    private val _properties = MutableStateFlow<List<Property>>(emptyList())
    val properties: StateFlow<List<Property>> = _properties

    val uiState = mutableStateOf(UiState())

    init {
        /*viewModelScope.launch {
            propertyRepository.getAllProperties().collect {
                _properties.value = it
            }
        }*/
        getNotes()
    }

    private fun getNotes(){
        when (uiState.value.displayType){
            DISPLAY_TYPE.ALL ->{
                viewModelScope.launch {
                    propertyRepository.getAllProperties().collect{list->
                        uiState.value = uiState.value.copy(
                            currentList = list
                        )
                    }
                }
            }
            /*DISPLAY_TYPE.FINISHED->{
                viewModelScope.launch {
                    propertyRepository.getFiltered(true).collect{list->
                        uiState.value = uiState.value.copy(
                            currentList = list
                        )
                    }
                }
            }
            DISPLAY_TYPE.IN_PROGRESS->{
                viewModelScope.launch {
                    propertyRepository.getFiltered(false).collect{list->
                        uiState.value = uiState.value.copy(
                            currentList = list
                        )
                    }
                }
            }*/
        }
    }

    fun switchDisplayType(dt: DISPLAY_TYPE){
        uiState.value = uiState.value.copy(
            displayType = dt
        )
        getNotes()
    }

    fun insert(property: Property){
        viewModelScope.launch {
            propertyRepository.insert(property)
        }
    }

    fun delete(property: Property){
        viewModelScope.launch {
            propertyRepository.delete(property)
        }
    }

    fun update(property: Property){
        viewModelScope.launch {
            propertyRepository.update(property)
        }
    }

}