package com.example.realestatemanager.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PropertiesDao {

    @Insert
    suspend fun insert(property: Property)

    @Update
    suspend fun update(property: Property)

    @Delete
    suspend fun delete(property: Property)

    @Query("SELECT * from properties WHERE id = :id")
    fun getProperty(id: Int): Flow<Property>

    @Query("SELECT * from properties ORDER BY type ASC")
    fun getAllProperties(): Flow<List<Property>>

}