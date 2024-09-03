package com.example.realestatemanager.domain.repository

import com.example.realestatemanager.data.local.property.PropertyEntity
import com.example.realestatemanager.data.local.property.PropertyDao
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow

abstract class PropertyRepository(
    private val propertyDao: PropertyDao
) {

    suspend fun insert(propertyEntity: PropertyEntity) = propertyDao.insert(propertyEntity)

    suspend fun insertFake(propertyEntity: PropertyEntity) = propertyDao.insertFake(propertyEntity)

    suspend fun delete(propertyEntity: PropertyEntity) = propertyDao.delete(propertyEntity)

    suspend fun update(propertyEntity: PropertyEntity) = propertyDao.update(propertyEntity)

    fun getAllProperties() = propertyDao.getAllProperties()

    fun getPropertyById(propertyId: Int): Flow<PropertyEntity?> = propertyDao.getPropertyById(propertyId)

    abstract suspend fun getCoordinates(address: String): LatLng?

}