package com.example.realestatemanager.database.repository

import android.util.Log
import com.example.realestatemanager.database.datasource.Property
import com.example.realestatemanager.database.datasource.PropertyDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PropertyRepository @Inject constructor(
    private val propertyDao: PropertyDao
) {

    suspend fun insert(property: Property) = propertyDao.insert(property)

    suspend fun insertFake(property: Property) = propertyDao.insertFake(property)

    suspend fun delete(property: Property) = propertyDao.delete(property)

    suspend fun update(property: Property) = propertyDao.update(property)

    fun getAllProperties() = propertyDao.getAllProperties()

    fun getPropertyById(propertyId: Int): Flow<Property?> = propertyDao.getPropertyById(propertyId)

    //fun getFiltered(isSell :Boolean) = propertyDao.getFiltered(isSell)

}