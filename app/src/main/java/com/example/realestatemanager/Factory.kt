package com.example.realestatemanager

import androidx.lifecycle.ViewModelProvider
import com.example.realestatemanager.database.Repository
import com.example.realestatemanager.main.HomeViewModel

class HomeViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    fun <T : HomeViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}