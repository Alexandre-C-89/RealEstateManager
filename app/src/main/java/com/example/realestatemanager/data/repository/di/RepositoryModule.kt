package com.example.realestatemanager.data.repository.di

import com.example.realestatemanager.data.local.AppDatabase
import com.example.realestatemanager.data.repository.PropertyRepositoryImpl
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

}