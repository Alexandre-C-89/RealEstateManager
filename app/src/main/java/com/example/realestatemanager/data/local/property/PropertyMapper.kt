package com.example.realestatemanager.data.local.property

import androidx.compose.ui.text.input.TextFieldValue
import com.example.realestatemanager.feature.modify.model.ModifyUiData
import kotlinx.coroutines.flow.Flow

class PropertyMapper {

    fun toModifyUiData(property: PropertyEntity): ModifyUiData {
        return ModifyUiData(
            type = TextFieldValue(property.type ?: ""),
            price = TextFieldValue(property.price.toString()),
            surface = TextFieldValue(property.surface.toString()),
            room = TextFieldValue(property.room.toString()),
            image = property.image ?: "",
            description = TextFieldValue(property.description ?: ""),
            address = TextFieldValue(property.address ?: ""),
            interest = TextFieldValue(property.interest ?: ""),
            status = TextFieldValue(property.status ?: ""),
            dateOfCreation = TextFieldValue(property.dateOfCreation.toString()),
            dateOfSold = TextFieldValue(property.dateOfSold.toString()),
            agent = TextFieldValue(property.agent ?: "")
        )
    }

    fun toPropertyEntity(modifyUiData: ModifyUiData, propertyId: Int): PropertyEntity {
        return PropertyEntity(
            id = propertyId,
            type = modifyUiData.type.text,
            price = modifyUiData.price.text.toDouble(),
            surface = modifyUiData.surface.text.toInt(),
            room = modifyUiData.room.text.toInt(),
            image = modifyUiData.image,
            description = modifyUiData.description.text,
            address = modifyUiData.address.text,
            interest = modifyUiData.interest.text,
            status = modifyUiData.status.text,
            dateOfCreation = modifyUiData.dateOfCreation.text.toLong(),
            dateOfSold = modifyUiData.dateOfSold.text.toLong(),
            agent = modifyUiData.agent.text,
            latitude = null,
            longitude = null
        )
    }
}