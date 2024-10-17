package com.example.realestatemanager.data.local.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.realestatemanager.data.local.AppDatabase
import com.example.realestatemanager.data.local.property.PropertyDao
import com.example.realestatemanager.data.remote.location.ILocationService
import com.example.realestatemanager.data.remote.location.LocationService
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideApplicationContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    @Singleton
    fun providePropertyDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "property.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(
        @ApplicationContext context: Context
    ): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(context)
    }

    @Singleton
    @Provides
    fun provideLocationClient(
        @ApplicationContext context: Context
    ): ILocationService = LocationService(
        context,
        LocationServices.getFusedLocationProviderClient(context)
    )

    @Provides
    fun providePropertyDao(appDatabase: AppDatabase): PropertyDao {
        return appDatabase.propertyDao
    }


}