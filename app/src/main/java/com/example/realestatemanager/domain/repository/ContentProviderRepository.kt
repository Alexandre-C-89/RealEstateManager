package com.example.realestatemanager.domain.repository

import com.example.realestatemanager.data.local.property.PropertyEntity

interface ContentProviderRepository{

    fun getAllProperties(): List<PropertyEntity>

}