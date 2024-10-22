package com.example.realestatemanager.data.local.property

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "properties")
data class PropertyEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val type: String?,
    val price: Long?,
    val surface: Int?,
    val room: Int?,
    val image: String?,
    val description: String?,
    val address: String?,
    val school: Boolean?,
    val shops: Boolean?,
    val sale: Boolean?,
    val dateOfCreation: Long?,
    val dateOfSold: Long?,
    val agent: String?,
    val latitude: Double?,
    val longitude: Double?
): Parcelable