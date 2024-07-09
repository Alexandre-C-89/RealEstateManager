package com.example.realestatemanager.database

import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getAllPropertiesStream(): Flow<List<Property>>

    fun getPropertyStream(id: Int): Flow<Property?>

    suspend fun insertProperty(item: Property)

    suspend fun deleteProperty(item: Property)

    suspend fun updateProperty(property: Property)

}