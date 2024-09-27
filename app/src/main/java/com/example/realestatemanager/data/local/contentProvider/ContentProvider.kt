package com.example.realestatemanager.data.local.contentProvider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.util.Log
import com.example.realestatemanager.data.local.AppDatabase
import com.example.realestatemanager.data.local.property.PropertyDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class ContentProvider : ContentProvider() {

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
            runBlocking(Dispatchers.IO) {
                val propertyDao = ContentProviderHelper.getPropertyDao()
                propertyDao.getAllPropertiesAsCursor()
            }
        } catch (e: Exception) {
            Log.e("ContentProvider", "Error querying properties: ${e.message}")
            null
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        // Implémentation pour insérer une nouvelle property
        return null
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?
    ): Int {
        // Implémentation pour mettre à jour une property
        return 0
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        // Implémentation pour supprimer une property
        return 0
    }

    override fun getType(uri: Uri): String? {
        return null
    }
}
