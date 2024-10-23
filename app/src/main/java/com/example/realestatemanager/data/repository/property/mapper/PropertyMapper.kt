package com.example.realestatemanager.data.repository.property.mapper

import android.content.ContentValues
import androidx.compose.ui.text.input.TextFieldValue
import com.example.realestatemanager.data.local.property.PropertyEntity
import com.example.realestatemanager.feature.modify.model.ModifyUiData

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
            school = property.school!!,
            shops = property.shops!!,
            sale = property.sale!!,
            dateOfCreation = TextFieldValue(property.dateOfCreation.toString()),
            dateOfSold = TextFieldValue(property.dateOfSold.toString()),
            agent = TextFieldValue(property.agent ?: "")
        )
    }

    fun toPropertyEntity(modifyUiData: ModifyUiData, propertyId: Int): PropertyEntity {
        return PropertyEntity(
            id = propertyId,
            type = modifyUiData.type.text.ifBlank { null },
            price = modifyUiData.price.text.toLongOrNull(),
            surface = modifyUiData.surface.text.toIntOrNull(),
            room = modifyUiData.room.text.toIntOrNull(),
            image = modifyUiData.image.split(",").map { it.trim() }.toString(),
            description = modifyUiData.description.text.ifBlank { null },
            address = modifyUiData.address.text.ifBlank { null },
            school = modifyUiData.school,
            shops = modifyUiData.shops,
            sale = modifyUiData.sale,
            dateOfCreation = modifyUiData.dateOfCreation.text.toLongOrNull(),
            dateOfSold = modifyUiData.dateOfSold.text.toLongOrNull(),
            agent = modifyUiData.agent.text.ifBlank { null },
            latitude = null,
            longitude = null
        )
    }

    fun fromContentValues(values: ContentValues): PropertyEntity {
        return PropertyEntity(
            id = values.getAsInteger("id") ?: 0,
            type = values.getAsString("type"),
            price = values.getAsLong("price"),
            surface = values.getAsInteger("surface"),
            room = values.getAsInteger("room"),
            image = values.getAsString("image")?.split(",")?.map { it.trim() }.toString(),
            description = values.getAsString("description"),
            address = values.getAsString("address"),
            school = values.getAsInteger("school") == 1,
            shops = values.getAsInteger("shops") == 1,
            sale = values.getAsInteger("sale") == 1,
            dateOfCreation = values.getAsLong("dateOfCreation"),
            dateOfSold = values.getAsLong("dateOfSold"),
            agent = values.getAsString("agent"),
            latitude = values.getAsDouble("latitude"),
            longitude = values.getAsDouble("longitude")
        )
    }

}