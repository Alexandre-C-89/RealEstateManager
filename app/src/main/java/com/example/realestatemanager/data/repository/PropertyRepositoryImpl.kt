package com.example.realestatemanager.data.repository

import com.example.realestatemanager.data.local.AppDatabase
import com.example.realestatemanager.data.local.property.PropertyEntity
import com.example.realestatemanager.domain.model.FormData
import com.example.realestatemanager.domain.repository.PropertyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PropertyRepositoryImpl @Inject constructor(
    private val appDatabase: AppDatabase
) : PropertyRepository {

    override suspend fun insert(propertyEntity: PropertyEntity) {
        return appDatabase.propertyDao.insert(propertyEntity)
    }

    override suspend fun insertFake(propertyEntity: PropertyEntity) {
        return appDatabase.propertyDao.insertFake(propertyEntity)
    }

    override suspend fun delete(propertyEntity: PropertyEntity): Flow<PropertyEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun update(propertyEntity: PropertyEntity): Flow<PropertyEntity> {
        TODO("Not yet implemented")
    }

    override fun getAllProperties(): Flow<List<PropertyEntity>> {
        return appDatabase.propertyDao.getAllProperties()
    }

    override fun getPropertyById(propertyId: Int): Flow<PropertyEntity> {
        return appDatabase.propertyDao.getPropertyById(propertyId)
    }

    override fun searchProperties(formData: FormData): Flow<List<PropertyEntity>> {
        return appDatabase.propertyDao.searchProperties(
           priceMin =  formData.priceMin.takeIf{it.isNotBlank()}?.toDoubleOrNull(),
           priceMax =  formData.priceMax.takeIf{it.isNotBlank()}?.toDoubleOrNull(),
           surfaceMin =  formData.surfaceMin.takeIf{it.isNotBlank()}?.toDoubleOrNull(),
           surfaceMax =  formData.surfaceMax.takeIf{it.isNotBlank()}?.toDoubleOrNull(),
        )
    }

    override fun getAllAddresses(): Flow<List<String>> {
        return appDatabase.propertyDao.getAllAddresses()
    }

}