package com.example.realestatemanager.data.local.contentProvider

import com.example.realestatemanager.data.local.property.PropertyDao
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface ContentProviderEntryPoint {
    fun propertyDao(): PropertyDao
}