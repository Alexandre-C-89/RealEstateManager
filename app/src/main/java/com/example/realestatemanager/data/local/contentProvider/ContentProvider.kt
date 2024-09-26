package com.example.realestatemanager.data.local.contentProvider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import com.example.realestatemanager.data.local.AppDatabase
import com.example.realestatemanager.data.local.property.PropertyDao

class ContentProvider : ContentProvider() {

    private lateinit var propertyDao: PropertyDao

    override fun onCreate(): Boolean {
        // Initialisation du DAO ou de la base de données Room
        val context = context ?: return false
        //val db = AppDatabase.getInstance(context)
        //propertyDao = db.propertyDao()
        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        return propertyDao.getAllPropertiesAsCursor()
    }

    // Méthodes pour insert, update et delete si besoin
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
        // Implémentation pour retourner le type MIME des données exposées
        return null
    }
}
