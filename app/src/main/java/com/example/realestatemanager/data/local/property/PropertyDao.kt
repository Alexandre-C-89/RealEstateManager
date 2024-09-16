package com.example.realestatemanager.data.local.property

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface PropertyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(propertyEntity: PropertyEntity)

    @Upsert
    suspend fun insertFake(propertyEntity: PropertyEntity)

    @Update
    suspend fun update(propertyEntity: PropertyEntity)

    @Delete
    suspend fun delete(propertyEntity: PropertyEntity)

    /*@Query("SELECT * FROM properties WHERE isSell = :isSell")
    fun getFiltered(isSell : Boolean) : Flow<List<Property>>*/

    @Query("SELECT * from properties ORDER BY type ASC")
    fun getAllProperties(): Flow<List<PropertyEntity>>

    @Query("SELECT * FROM properties WHERE id = :propertyId")
    fun getPropertyById(propertyId: Int): Flow<PropertyEntity>

}