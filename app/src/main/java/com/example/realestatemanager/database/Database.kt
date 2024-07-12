package com.example.realestatemanager.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Property::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {

    abstract fun propertyDao(): PropertyDao

    companion object {
        @Volatile
        private var INSTANCE: com.example.realestatemanager.database.Database? = null

        fun getDatabase(context: Context): com.example.realestatemanager.database.Database {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    com.example.realestatemanager.database.Database::class.java,
                    "property_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}