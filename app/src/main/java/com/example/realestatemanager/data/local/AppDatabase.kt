package com.example.realestatemanager.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.realestatemanager.data.local.location.LocationDao
import com.example.realestatemanager.data.local.location.LocationEntity

@Database(
    entities = [LocationEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract val locationDao: LocationDao
}