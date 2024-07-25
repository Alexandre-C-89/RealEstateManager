package com.example.realestatemanager

import androidx.lifecycle.ViewModelProvider
import com.example.realestatemanager.database.datasource.PropertyDatabase
import com.example.realestatemanager.database.repository.PropertyRepository
import com.example.realestatemanager.main.HomeViewModel

class HomeViewModelFactory(private val propertyRepository: PropertyRepository, private val propertyDatabase: PropertyDatabase) : ViewModelProvider.Factory {
    fun <T : HomeViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(propertyRepository, propertyDatabase = propertyDatabase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}