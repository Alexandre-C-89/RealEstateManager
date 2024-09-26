package com.example.realestatemanager.data.local.property

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.realestatemanager.domain.model.FormData
import kotlinx.coroutines.flow.Flow

@Dao
interface PropertyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(propertyEntity: PropertyEntity)

    @Upsert
    suspend fun insertFake(propertyEntity: PropertyEntity)

    @Update
    suspend fun update(propertyEntity: PropertyEntity)

    @Query("DELETE FROM properties WHERE id = :propertyId")
    suspend fun delete(propertyId: Int)

    @Query("SELECT * from properties ORDER BY type ASC")
    fun getAllProperties(): Flow<List<PropertyEntity>>

    @Query("SELECT * FROM properties WHERE id = :propertyId")
    fun getPropertyById(propertyId: Int): Flow<PropertyEntity>

    @Query("SELECT * FROM properties WHERE (:priceMin IS NULL OR price >= :priceMin) AND (:priceMax IS NULL OR price <= :priceMax) AND (:surfaceMin IS NULL OR surface >= :surfaceMin) AND (:surfaceMax IS NULL OR surface <= :surfaceMax)")
    fun searchProperties(
        priceMin: Double?,
        priceMax: Double?,
        surfaceMin: Double?,
        surfaceMax: Double?
    ): Flow<List<PropertyEntity>>

    @Query("SELECT address FROM properties")
    fun getAllAddresses(): Flow<List<String>>

    @Query("SELECT * FROM properties")
    fun getAllPropertiesAsCursor(): Cursor

}