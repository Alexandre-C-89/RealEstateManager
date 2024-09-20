package com.example.realestatemanager.data.repository

import android.util.Log
import com.example.realestatemanager.data.remote.location.LocationApi
import com.example.realestatemanager.domain.model.GeocodingResult
import com.example.realestatemanager.domain.repository.LocationRepository
import com.example.realestatemanager.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val locationApi: LocationApi
): LocationRepository {

    override suspend fun getConvertAddress(address: String): Flow<Resource<GeocodingResult>> {
        return flow {
            emit(Resource.Loading())
            try {
                val geocodingResult = locationApi.getConvertAddress(address)
                Log.d("LOCATIONREPOSITORYIMPL", "error + ${geocodingResult}")
                emit(Resource.Success(geocodingResult))
            } catch (e: Exception) {
                Log.d("LOCATIONREPOSITORYIMPL", "error + ${e.message}")
                emit(Resource.Error(e.message ?: "An error occurred"))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getConvertAddresses(addresses: List<String>): Flow<Resource<List<GeocodingResult>>> {
        return flow {
            emit(Resource.Loading())
            try {
                val geocodingResults = addresses.map { address ->
                    locationApi.getConvertAddress(address)
                }
                emit(Resource.Success(geocodingResults))
            } catch (e: Exception) {
                emit(Resource.Error(e.message ?: "An error occurred"))
            }
        }.flowOn(Dispatchers.IO)
    }

}