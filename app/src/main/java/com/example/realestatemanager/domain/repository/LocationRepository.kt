package com.example.realestatemanager.domain.repository

import com.example.realestatemanager.domain.model.GeocodingResult
import com.example.realestatemanager.util.Resource
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow

interface LocationRepository {

    suspend fun getConvertAddress(address: String): Flow<Resource<GeocodingResult>>

}