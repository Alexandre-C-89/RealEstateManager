package com.example.realestatemanager.data.repository

import com.example.realestatemanager.data.local.AppDatabase
import com.example.realestatemanager.data.local.property.PropertyEntity
import com.example.realestatemanager.domain.repository.PropertyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PropertyRepositoryImpl @Inject constructor(
    private val appDatabase: AppDatabase
) : PropertyRepository {

    /*override suspend fun getCoordinates(address: String): LatLng? {
        return try {
            val geocoder = Geocoder(context, Locale.getDefault())
            val addresses = geocoder.getFromLocationName(address, 1)  // Convert address to LatLng
            if (addresses != null && addresses.isNotEmpty()) {
                val location = addresses[0]
                LatLng(location.latitude, location.longitude)
            } else {
                null
            }
        } catch (e: IOException) {
            Log.e("PropertyRepositoryImpl", "Error fetching coordinates: ${e.message}")
            null
        }
    }*/

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

}