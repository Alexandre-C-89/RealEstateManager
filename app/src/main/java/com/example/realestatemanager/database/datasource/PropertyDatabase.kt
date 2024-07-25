package com.example.realestatemanager.database.datasource

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Property::class], version = 1)
abstract class PropertyDatabase : RoomDatabase() {

    abstract fun propertyDao(): PropertyDao

    /*companion object {
        @Volatile
        private var INSTANCE: com.example.realestatemanager.database.datasource.PropertyDatabase? = null

        fun getDatabase(context: Context): com.example.realestatemanager.database.datasource.PropertyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    com.example.realestatemanager.database.datasource.PropertyDatabase::class.java,
                    "property_database"
                ).addCallback(PrepopulateRoomCallBack(context))
                .build()
                INSTANCE = instance
                instance
            }
        }
    }*/

}