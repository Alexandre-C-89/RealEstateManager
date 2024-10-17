package com.example.realestatemanager.data.local.contentProvider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.util.Log
import com.example.realestatemanager.RealApp
import com.example.realestatemanager.data.local.property.PropertyDao
import com.example.realestatemanager.data.repository.property.mapper.PropertyMapper
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class PropertyContentProvider : ContentProvider() {

    @Inject
    lateinit var propertyDao: PropertyDao

    override fun onCreate(): Boolean {
        val context = context ?: return false
        val entryPoint = EntryPointAccessors.fromApplication(
            context.applicationContext,
            ContentProviderEntryPoint::class.java
        )
        propertyDao = entryPoint.propertyDao()
        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        return try {
            propertyDao.getAllPropertiesAsCursor()
        } catch (e: Exception) {
            Log.e("PropertyContentProvider", "Error querying properties: ${e.message}")
            null
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return try {
            values?.let {
                val property = PropertyMapper().fromContentValues(it)
                val newId = runBlocking { propertyDao.insert(property) }
                Uri.withAppendedPath(uri, newId.toString())
            }
        } catch (e: Exception) {
            Log.e("PropertyContentProvider", "Error inserting property: ${e.message}")
            null
        }
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun getType(uri: Uri): String? {
        return null
    }
}