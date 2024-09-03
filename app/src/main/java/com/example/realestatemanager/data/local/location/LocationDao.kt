package com.example.realestatemanager.data.local.location

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LocationDao {
    @Query("SELECT * FROM locations WHERE address = :address LIMIT 1")
    suspend fun getCoordinatesByAddress(address: String): LocationEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoordinates(locationEntity: LocationEntity)
}