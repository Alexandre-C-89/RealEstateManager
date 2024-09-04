package com.example.realestatemanager.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.realestatemanager.data.local.property.PropertyDao
import com.example.realestatemanager.data.local.property.PropertyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private var INSTANCE: PropertyDatabase? = null

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): PropertyDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            PropertyDatabase::class.java,
            "Property.db"
        ).fallbackToDestructiveMigration()
            .setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
        .build()
    }

    @Provides
    fun provideTaskDao(database: PropertyDatabase): PropertyDao = database.propertyDao()
}