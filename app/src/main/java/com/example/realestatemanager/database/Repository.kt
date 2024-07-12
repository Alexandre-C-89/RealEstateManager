package com.example.realestatemanager.database

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(private val propertyDao: PropertyDao) {

    fun getAllProperties(): Flow<List<Property>> {
        return propertyDao.getAllProperties()
    }

    suspend fun insert(property: Property) {
        propertyDao.insert(property)
    }

    suspend fun deleteProperty(property: Property) {
        propertyDao.delete(property)
    }

    suspend fun updateProperty(property: Property) {
        propertyDao.update(property)
    }

}