package com.example.realestatemanager.database.datasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface PropertyDao {

    @Upsert
    suspend fun insert(property: Property)

    @Upsert
    fun insertFake(property: Property)

    @Update
    suspend fun update(property: Property)

    @Delete
    suspend fun delete(property: Property)

    /*@Query("SELECT * FROM properties WHERE isSell = :isSell")
    fun getFiltered(isSell : Boolean) : Flow<List<Property>>*/

    @Query("SELECT * from properties ORDER BY type ASC")
    fun getAllProperties(): Flow<List<Property>>

}