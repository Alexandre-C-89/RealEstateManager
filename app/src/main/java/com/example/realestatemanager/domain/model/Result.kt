package com.example.realestatemanager.domain.model

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("formatted_address") val formattedAddress: String,
    val geometry: Geometry
)