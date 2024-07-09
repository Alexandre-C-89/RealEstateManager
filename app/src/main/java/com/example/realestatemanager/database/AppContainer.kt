package com.example.realestatemanager.database

import android.content.Context

interface AppContainer {
    val repository: Repository
}

/**
 * [AppContainer] implementation that provides instance of [OfflineItemsRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [ItemsRepository]
     */
    override val repository: Repository by lazy {
        OfflineRepository(InventoryDatabase.getDatabase(context).propertyDao())
    }

}