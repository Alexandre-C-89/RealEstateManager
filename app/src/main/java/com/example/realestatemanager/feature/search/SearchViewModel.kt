package com.example.realestatemanager.feature.search

import android.util.Log
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realestatemanager.data.local.property.PropertyEntity
import com.example.realestatemanager.domain.model.FormData
import com.example.realestatemanager.domain.repository.PropertyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: PropertyRepository
): ViewModel(){

    private val _data = MutableStateFlow(SearchUiData())
    val data: StateFlow<SearchUiData> = _data

    private val _searchState = MutableStateFlow<SearchState>(SearchState.Loading)
    val searchState: StateFlow<SearchState> = _searchState

    private val _propertyList = MutableStateFlow<List<PropertyEntity>>(emptyList())
    val properties: StateFlow<List<PropertyEntity>> = _propertyList

    fun onPriceMinChanged(value: TextFieldValue) {
        _data.value = data.value.copy(priceMin = value)
    }

    fun onPriceMaxChanged(value: TextFieldValue) {
        _data.value = data.value.copy(priceMax = value)
    }

    fun onSurfaceMinChanged(value: TextFieldValue) {
        _data.value = data.value.copy(surfaceMin = value)
    }

    fun onSurfaceMaxChanged(value: TextFieldValue) {
        _data.value = data.value.copy(surfaceMax = value)
    }

    fun onSchoolChanged(value: Boolean) {
        _data.value = data.value.copy(school = value)
    }

    fun onShopsChanged(value: Boolean) {
        _data.value = data.value.copy(shops = value)
    }

    fun onSaleChanged(value: Boolean) {
        _data.value = data.value.copy(sale = value)
    }

    fun onMinPhotosChanged(value: TextFieldValue) {
        _data.value = data.value.copy(minPhotos = value)
    }

    fun onMaxPhotosChanged(value: TextFieldValue) {
        _data.value = data.value.copy(maxPhotos = value)
    }

    fun searchProperties(){
        viewModelScope.launch {
            try {
                val formData = FormData(
                    priceMin = _data.value.priceMin.text,
                    priceMax = _data.value.priceMax.text,
                    surfaceMin = _data.value.surfaceMin.text,
                    surfaceMax = _data.value.surfaceMax.text,
                    school = _data.value.school,
                    shops = _data.value.shops,
                    sale = _data.value.sale,
                    minPhotos = _data.value.minPhotos.text,
                    maxPhotos = _data.value.maxPhotos.text
                )
                repository.searchProperties(formData).collect { properties ->
                    val filteredProperties = if (_data.value.sale) {
                        properties.filter { it.sale == true }
                    } else {
                        properties.filter { it.sale == false }
                    }

                    if (filteredProperties.isEmpty()) {
                        _searchState.value = SearchState.Error("No properties found")
                    } else {
                        _propertyList.value = filteredProperties
                        _searchState.value = SearchState.Success(filteredProperties)
                    }
                }
            } catch (e: Exception){
                Log.e("SearchViewModel", "Error send form: ${e.message}")
            }
        }
    }

}