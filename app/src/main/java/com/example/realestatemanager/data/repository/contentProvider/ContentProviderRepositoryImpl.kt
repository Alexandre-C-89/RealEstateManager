package com.example.realestatemanager.data.repository.contentProvider

import android.content.Context
import android.net.Uri
import androidx.core.database.getDoubleOrNull
import androidx.core.database.getIntOrNull
import androidx.core.database.getLongOrNull
import androidx.core.database.getStringOrNull
import com.example.realestatemanager.data.local.property.PropertyEntity
import com.example.realestatemanager.domain.repository.ContentProviderRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ContentProviderRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
): ContentProviderRepository {
    private val contentUri: Uri =
        Uri.parse("content://com.example.realestatemanager.provider/properties")

    override fun getAllProperties(): List<PropertyEntity> {
        val properties = mutableListOf<PropertyEntity>()
        val cursor = context.contentResolver.query(contentUri, null, null, null, null)
        cursor?.use {
            if (cursor.moveToFirst()) {
                do {
                    val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                    val type = cursor.getStringOrNull(cursor.getColumnIndexOrThrow("type"))
                    val price = cursor.getLongOrNull(cursor.getColumnIndexOrThrow("price"))
                    val surface = cursor.getIntOrNull(cursor.getColumnIndexOrThrow("surface"))
                    val room = cursor.getIntOrNull(cursor.getColumnIndexOrThrow("room"))
                    val image = cursor.getStringOrNull(cursor.getColumnIndexOrThrow("image"))
                    val description = cursor.getStringOrNull(cursor.getColumnIndexOrThrow("description"))
                    val address = cursor.getStringOrNull(cursor.getColumnIndexOrThrow("address"))
                    val school = cursor.getIntOrNull(cursor.getColumnIndexOrThrow("school")) == 1
                    val shops = cursor.getIntOrNull(cursor.getColumnIndexOrThrow("shops")) == 1
                    val sale = cursor.getIntOrNull(cursor.getColumnIndexOrThrow("sale")) == 1
                    val dateOfCreation = cursor.getLongOrNull(cursor.getColumnIndexOrThrow("dateOfCreation"))
                    val dateOfSold = cursor.getLongOrNull(cursor.getColumnIndexOrThrow("dateOfSold"))
                    val agent = cursor.getStringOrNull(cursor.getColumnIndexOrThrow("agent"))
                    val latitude = cursor.getDoubleOrNull(cursor.getColumnIndexOrThrow("latitude"))
                    val longitude = cursor.getDoubleOrNull(cursor.getColumnIndexOrThrow("longitude"))
                    val property = PropertyEntity(
                        id,
                        type,
                        price,
                        surface,
                        room,
                        image,
                        description,
                        address,
                        school,
                        shops,
                        sale,
                        dateOfCreation,
                        dateOfSold,
                        agent,
                        latitude,
                        longitude,
                    )
                    properties.add(property)
                } while (cursor.moveToNext())
            }
        }
        return properties
    }
}