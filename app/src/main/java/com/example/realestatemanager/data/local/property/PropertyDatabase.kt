package com.example.realestatemanager.data.local.property

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PropertyEntity::class], version = 1)
abstract class PropertyDatabase : RoomDatabase() {

    abstract fun propertyDao(): PropertyDao

}