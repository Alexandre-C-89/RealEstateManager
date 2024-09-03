package com.example.realestatemanager.data.repository

import com.example.realestatemanager.data.remote.LocationApi
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
                emit(Resource.Success(geocodingResult))
            } catch (e: Exception) {
                emit(Resource.Error(e.message ?: "An error occurred"))
            }
        }.flowOn(Dispatchers.IO)
    }

}