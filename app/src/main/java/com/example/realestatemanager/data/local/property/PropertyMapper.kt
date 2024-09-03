package com.example.realestatemanager.data.local.property

import com.example.realestatemanager.data.remote.dto.PropertyDto
import com.example.realestatemanager.domain.model.Property

fun PropertyDto.toPropertyEntity(
): PropertyEntity {
    return PropertyEntity(
        id = id,
        type = type,
        price = price,
        surface = surface,
        room = room,
        image = image,
        description = description,
        address = address,
        interest = interest,
        status = status,
        dateOfCreation = dateOfCreation,
        dateOfSold = dateOfSold,
        agent = agent,
        latitude = latitude,
        longitude = longitude
    )
}

fun PropertyEntity.toProperty(
): Property {
    return Property(
        id = id,
        type = type,
        price = price,
        surface = surface,
        room = room,
        image = image,
        description = description,
        address = address,
        interest = interest,
        status = status,
        dateOfCreation = dateOfCreation,
        dateOfSold = dateOfSold,
        agent = agent,
        latitude = latitude,
        longitude = longitude
    )
}