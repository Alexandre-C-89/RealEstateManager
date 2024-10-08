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
import com.example.realestatemanager.domain.repository.LocationRepository
import com.example.realestatemanager.domain.repository.PropertyRepository
import com.example.realestatemanager.feature.details.model.LocationState
import com.example.realestatemanager.util.NetworkUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
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

    private val uiState = mutableStateOf(UiState())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getProperties()
        }
    }

    private suspend fun getProperties() {
        if (NetworkUtil.isNetworkAvailable(context)) {
            when (uiState.value.displayType) {
                DISPLAY_TYPE.ALL -> {
                    propertyRepository.getAllProperties().collect { list ->
                        withContext(Dispatchers.Main) {
                            _properties.value = properties.value.copy(
                                currentList = list
                            )
                        }
                    }
                }
            }
        } else {
            fetchProperties()
        }
    }

    private fun fetchProperties() {
        viewModelScope.launch {
            val result = contentProviderRepository.getAllProperties()
            _propertiesOffline.postValue(result)
        }
    }

}