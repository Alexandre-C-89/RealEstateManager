package com.example.realestatemanager.edit

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realestatemanager.database.datasource.Property
import com.example.realestatemanager.database.repository.PropertyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(
    val repository: PropertyRepository
): ViewModel() {

    private val _data = MutableStateFlow(EditUiData())
    val data: StateFlow<EditUiData> = _data

    fun onTypeChanged(value: TextFieldValue) {
        _data.value = data.value.copy(type = value)
    }

    fun onPriceChanged(value: TextFieldValue) {
        _data.value = _data.value.copy(price = value)
    }

    fun onSurfaceChanged(value: TextFieldValue) {
        _data.value = data.value.copy(surface = value)
    }

    fun onRoomChanged(value: TextFieldValue) {
        _data.value = data.value.copy(room = value)
    }

    fun onDescritpionChanged(value: TextFieldValue) {
        _data.value = data.value.copy(description = value)
    }

    fun onAddressChanged(value: TextFieldValue) {
        _data.value = data.value.copy(address = value)
    }

    fun onInterestChanged(value: TextFieldValue) {
        _data.value = data.value.copy(interest = value)
    }

    fun onStatusChanged(value: TextFieldValue) {
        _data.value = data.value.copy(status = value)
    }

    fun onDateOfCreationChanged(value: TextFieldValue) {
        _data.value = data.value.copy(dateOfCreation = value)
    }

    fun onDateOfSoldChanged(value: TextFieldValue) {
        _data.value = data.value.copy(dateOfSold = value)
    }

    fun onAgentChanged(value: TextFieldValue) {
        _data.value = data.value.copy(agent = value)
    }

    fun saveProperty(property: Property){
        viewModelScope.launch {
            repository.insert(property = property)
        }
    }

}