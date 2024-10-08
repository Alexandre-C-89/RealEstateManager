package com.example.realestatemanager.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.realestatemanager.data.local.property.PropertyDao
import com.example.realestatemanager.data.local.property.PropertyEntity
import kotlinx.coroutines.flow.Flow

@Database(
    entities = [PropertyEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    //abstract val locationDao: LocationDao
    abstract val propertyDao: PropertyDao
}