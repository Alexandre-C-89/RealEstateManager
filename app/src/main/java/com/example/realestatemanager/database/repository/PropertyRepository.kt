package com.example.realestatemanager.database.repository

import com.example.realestatemanager.data.local.property.PropertyEntity
import com.example.realestatemanager.data.local.property.PropertyDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PropertyRepository @Inject constructor(
    private val propertyDao: PropertyDao
) {

    suspend fun insert(propertyEntity: PropertyEntity) = propertyDao.insert(propertyEntity)

    suspend fun insertFake(propertyEntity: PropertyEntity) = propertyDao.insertFake(propertyEntity)

    suspend fun delete(propertyEntity: PropertyEntity) = propertyDao.delete(propertyEntity)

    suspend fun update(propertyEntity: PropertyEntity) = propertyDao.update(propertyEntity)

    fun getAllProperties() = propertyDao.getAllProperties()

    fun getPropertyById(propertyId: Int): Flow<PropertyEntity?> = propertyDao.getPropertyById(propertyId)

    //fun getFiltered(isSell :Boolean) = propertyDao.getFiltered(isSell)

}