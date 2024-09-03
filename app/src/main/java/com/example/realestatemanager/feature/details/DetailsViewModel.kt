package com.example.realestatemanager.feature.details

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.realestatemanager.database.datasource.Property
import com.example.realestatemanager.database.repository.PropertyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: PropertyRepository
) : ViewModel() {

    fun getPropertyById(propertyId: Int): Flow<Property?> {
        Log.d("DetailsViewModel", "Fetching property with ID: $propertyId")
        return repository.getPropertyById(propertyId)
    }

}