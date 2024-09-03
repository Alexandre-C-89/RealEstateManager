package com.example.realestatemanager.data.local.property

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "properties")
data class PropertyEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val type: String,
    val price: Int?,
    val surface: Int?,
    val room: Int?,
    val image: String?,
    val description: String?,
    val address: String,
    val interest: String?,
    val status: String,
    val dateOfCreation: Long,
    val dateOfSold: Long?,
    val agent: String,
    val latitude: Double?,
    val longitude: Double?
)