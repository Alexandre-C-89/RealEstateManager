package com.example.realestatemanager.database.repository

import com.example.realestatemanager.database.datasource.Property
import com.example.realestatemanager.database.datasource.PropertyDao
import javax.inject.Inject

class PropertyRepository @Inject constructor(
    private val propertyDao: PropertyDao
) {

    suspend fun insert(property: Property) = propertyDao.insert(property)

    fun insertFake(property: Property) = propertyDao.insertFake(property)

    suspend fun delete(property: Property) = propertyDao.delete(property)

    suspend fun update(property: Property) = propertyDao.update(property)

    fun getAllProperties() = propertyDao.getAllProperties()

    //fun getFiltered(isSell :Boolean) = propertyDao.getFiltered(isSell)

}