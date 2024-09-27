package com.example.realestatemanager.data.local.contentProvider

import android.content.Context
import androidx.room.Room
import com.example.realestatemanager.data.local.AppDatabase
import com.example.realestatemanager.data.local.property.PropertyDao

object ContentProviderHelper {
    private lateinit var appDatabase: AppDatabase

    fun init(context: Context) {
        appDatabase = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "your_database_name"
        ).build()
    }

    fun getPropertyDao(): PropertyDao {
        return appDatabase.propertyDao
    }
}