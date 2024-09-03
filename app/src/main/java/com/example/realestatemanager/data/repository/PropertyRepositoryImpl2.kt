package com.example.realestatemanager.data.repository

import android.content.Context
import android.location.Geocoder
import com.example.realestatemanager.data.local.AppDatabase
import com.example.realestatemanager.data.remote.PropertyApi
import com.example.realestatemanager.domain.repository.PropertyRepository
import com.google.android.gms.maps.model.LatLng
import java.io.IOException
import java.util.Locale
import javax.inject.Inject

/*class PropertyRepositoryImpl2 @Inject constructor(
    private val propertyApi: PropertyApi,
    private val appDatabase: AppDatabase,
    //private val propertyDatabase: PropertyDatabase,
    private val context: Context
) : PropertyRepository {

    override suspend fun getCoordinates(address: String): LatLng? {
        try {
            val geocoder = Geocoder(context, Locale.getDefault())
            val addresses = geocoder.getFromLocationName(address, 1)
            if (addresses != null && addresses.isNotEmpty()) {
                val location = addresses[0]
                return LatLng(location.latitude, location.longitude)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

}*/