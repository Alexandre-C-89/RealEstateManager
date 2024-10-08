package com.example.realestatemanager.data.repository.di

import com.example.realestatemanager.data.local.AppDatabase
import com.example.realestatemanager.data.remote.location.LocationApi
import com.example.realestatemanager.data.repository.contentProvider.ContentProviderRepositoryImpl
import com.example.realestatemanager.data.repository.location.LocationRepositoryImpl
import com.example.realestatemanager.data.repository.property.PropertyRepositoryImpl
import com.example.realestatemanager.domain.repository.ContentProviderRepository
import com.example.realestatemanager.domain.repository.LocationRepository
import com.example.realestatemanager.domain.repository.PropertyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesPropertyRepository(
        appDatabase: AppDatabase
    ): PropertyRepository {
        return PropertyRepositoryImpl(appDatabase)
    }

    @Provides
    @Singleton
    fun providesLocationRepository(
        locationApi: LocationApi
    ): LocationRepository {
        return LocationRepositoryImpl(locationApi)
    }

    @Provides
    @Singleton
    fun providesContentProviderRepository(
        impl: ContentProviderRepositoryImpl
    ): ContentProviderRepository = impl

}