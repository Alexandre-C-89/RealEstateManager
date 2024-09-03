package com.example.realestatemanager.data.remote

import com.example.realestatemanager.data.remote.di.RemoteModule
import com.example.realestatemanager.domain.model.Location
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PropertyApi {
    @GET("/json")
    suspend fun getCoordinatesFromAddress(
        @Query("address") address : String,
        @Query("key") apiKey : String = RemoteModule.API_KEY
    ): List<Location>
}