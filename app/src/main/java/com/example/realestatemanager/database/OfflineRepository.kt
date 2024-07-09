package com.example.realestatemanager.database

import kotlinx.coroutines.flow.Flow

class OfflineRepository(private val propertiesDao: PropertiesDao) : Repository {

    override fun getAllPropertiesStream(): Flow<List<Property>> = propertiesDao.getAllProperties()

    override fun getPropertyStream(id: Int): Flow<Property?> = propertiesDao.getProperty(id)

    override suspend fun insertProperty(property: Property) = propertiesDao.insert(property)

    override suspend fun deleteProperty(property: Property) = propertiesDao.delete(property)

    override suspend fun updateProperty(property: Property) = propertiesDao.update(property)

}