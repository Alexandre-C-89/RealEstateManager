package com.example.realestatemanager.data.repository.di

import android.content.Context
import com.example.realestatemanager.data.local.AppDatabase
import com.example.realestatemanager.data.remote.PropertyApi
import com.example.realestatemanager.data.repository.PropertyRepositoryImpl
import com.example.realestatemanager.domain.repository.PropertyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesPropertyRepository(
        propertyApi: PropertyApi,
        appDatabase: AppDatabase,
        @ApplicationContext context: Context  // Inject context here
    ): PropertyRepository {
        val propertyDao = appDatabase.propertyDao  // Get PropertyDao from AppDatabase
        return PropertyRepositoryImpl(propertyApi, propertyDao, context)  // Pass PropertyDao
    }

}