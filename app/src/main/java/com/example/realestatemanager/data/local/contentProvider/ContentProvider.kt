package com.example.realestatemanager.data.local.contentProvider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.util.Log
import com.example.realestatemanager.data.local.property.PropertyEntity
import com.example.realestatemanager.data.repository.property.mapper.PropertyMapper
import com.example.realestatemanager.domain.repository.PropertyRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class ContentProvider : ContentProvider() {

    @Inject
    lateinit var propertyRepository: PropertyRepository

    override fun onCreate(): Boolean {
        val context = context ?: return false
        ContentProviderHelper.init(context)
        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        return try {
            propertyRepository.getAllPropertiesAsCursor()
        } catch (e: Exception) {
            Log.e("ContentProvider", "Error querying properties: ${e.message}")
            null
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return try {
            values?.let {
                val property = PropertyMapper().fromContentValues(it) // Use PropertyMapper
                val newId = runBlocking {
                    val propertyDao = ContentProviderHelper.getPropertyDao()
                    propertyDao.insert(property)
                }
                Uri.withAppendedPath(uri, newId.toString())
            }
        } catch (e: Exception) {
            Log.e("ContentProvider", "Error inserting property: ${e.message}")
            null
        }
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?
    ): Int {
        return try {
            values?.let {
                val id = uri.lastPathSegment?.toIntOrNull() // Assurez-vous que cela retourne un Int
                if (id != null) {
                    val propertyDao = ContentProviderHelper.getPropertyDao()
                    val existingPropertyFlow = propertyDao.getPropertyById(id)

                    var existingProperty: PropertyEntity? = null
                    runBlocking {
                        existingPropertyFlow.collect { property ->
                            existingProperty = property
                        }
                    }

                    existingProperty?.let { property ->
                        val updatedProperty = property.copy(
                            type = values.getAsString("type"),
                            price = values.getAsDouble("price"),
                            surface = values.getAsInteger("surface"),
                            room = values.getAsInteger("room"),
                            image = values.getAsString("image"),
                            description = values.getAsString("description"),
                            address = values.getAsString("address"),
                            interest = values.getAsString("interest"),
                            status = values.getAsString("status"),
                            dateOfCreation = values.getAsLong("dateOfCreation"),
                            dateOfSold = values.getAsLong("dateOfSold"),
                            agent = values.getAsString("agent"),
                            latitude = values.getAsDouble("latitude"),
                            longitude = values.getAsDouble("longitude")
                        )

                        // Effectuer la mise à jour dans la base de données
                        CoroutineScope(Dispatchers.IO).launch {
                            propertyDao.update(updatedProperty)
                        }

                        1 // 1 If operation right
                    } ?: run {
                        Log.e("ContentProvider", "Property not found with ID: $id")
                        0 // If property isn't find
                    }
                } else {
                    Log.e("ContentProvider", "Invalid ID retrieved from URI")
                    0 // ID invalid
                }
            } ?: run {
                Log.e("ContentProvider", "ContentValues is null")
                0
            }
        } catch (e: Exception) {
            Log.e("ContentProvider", "Error updating property: ${e.message}")
            0
        }
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return try {
            val id = uri.lastPathSegment?.toLongOrNull()
            if (id != null) {
                runBlocking {
                    val propertyDao = ContentProviderHelper.getPropertyDao()
                    propertyDao.delete(id.toInt())
                }
                1 // 1 if is success
            } else {
                0
            }
        } catch (e: Exception) {
            Log.e("ContentProvider", "Error deleting property: ${e.message}")
            0
        }
    }

    override fun getType(uri: Uri): String? {
        return null
    }
}
