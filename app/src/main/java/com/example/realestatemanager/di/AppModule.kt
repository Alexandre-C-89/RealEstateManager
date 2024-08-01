package com.example.realestatemanager.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.realestatemanager.database.datasource.Property
import com.example.realestatemanager.database.datasource.PropertyDao
import com.example.realestatemanager.database.datasource.PropertyDatabase
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
        ).setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
        .build()
    }

    @Provides
    fun provideTaskDao(database: PropertyDatabase): PropertyDao = database.propertyDao()
}