package com.example.realestatemanager.data.repository

import android.content.Context
import android.location.Geocoder
import android.util.Log
import com.example.realestatemanager.data.local.AppDatabase
import com.example.realestatemanager.data.local.property.PropertyDao
import com.example.realestatemanager.data.remote.PropertyApi
import com.example.realestatemanager.domain.repository.PropertyRepository
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import java.util.Locale
import javax.inject.Inject

class PropertyRepositoryImpl @Inject constructor(
    private val propertyApi: PropertyApi,
    private val propertyDao: PropertyDao,
    @ApplicationContext private val context: Context  // Inject context here
) : PropertyRepository(propertyDao) {

    override suspend fun getCoordinates(address: String): LatLng? {
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
    }
}