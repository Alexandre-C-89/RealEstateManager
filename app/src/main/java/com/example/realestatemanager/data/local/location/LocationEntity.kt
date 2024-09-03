package com.example.realestatemanager.data.local.location

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "locations")
data class LocationEntity(
    @PrimaryKey val address: String,
    val latitude: Double,
    val longitude: Double
)