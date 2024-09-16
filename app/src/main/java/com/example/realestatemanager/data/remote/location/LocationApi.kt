package com.example.realestatemanager.data.remote.location

import com.example.realestatemanager.data.remote.di.RemoteModule
import com.example.realestatemanager.domain.model.GeocodingResult
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationApi {

    @GET("json")
    suspend fun getConvertAddress(
        @Query("address") address: String,
        @Query("key") apiKey: String = RemoteModule.API_KEY
    ) : GeocodingResult

}