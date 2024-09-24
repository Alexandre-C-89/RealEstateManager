package com.example.realestatemanager.domain.repository

import com.example.realestatemanager.data.local.property.PropertyEntity
import com.example.realestatemanager.domain.model.FormData
import kotlinx.coroutines.flow.Flow

interface PropertyRepository {

    suspend fun insert(propertyEntity: PropertyEntity)

    suspend fun insertFake(propertyEntity: PropertyEntity)

    suspend fun delete(propertyEntity: PropertyEntity): Flow<PropertyEntity>

    suspend fun update(propertyEntity: PropertyEntity)

    fun getAllProperties(): Flow<List<PropertyEntity>>

    fun getPropertyById(propertyId: Int): Flow<PropertyEntity>

    fun searchProperties(formData: FormData): Flow<List<PropertyEntity>>

    fun getAllAddresses(): Flow<List<String>>

}